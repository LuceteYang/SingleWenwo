package com.wenwoandroidnew.me;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wenwoandroidnew.ChargeSeedActivity;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.contents.MagazineAdapter;
import com.wenwoandroidnew.contents.Magazineitem;
import com.wenwoandroidnew.newsfeed.ZoomPictureActivity;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.common.CallResultOnemore;
import com.wenwoandroidnew.system.model.ModelMagazineList;
import com.wenwoandroidnew.system.model.ModelMyInfo;
import com.wenwoandroidnew.system.model.query.ModelMagazineQuery;
import com.wenwoandroidnew.system.module.ModelPicture;
import com.wenwoandroidnew.system.module.ModuleMagazineList;
import com.wenwoandroidnew.system.module.ModuleMyInfo;
import com.wenwoandroidnew.system.util.AppSetting;
import com.wenwoandroidnew.system.util.UtilAPIControll;
import com.wenwoandroidnew.system.util.UtilUi;
import com.wenwoandroidnew.writer.WriterMagazineFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment implements CallResult<ModelMagazineList>, CallResultOnemore<ModelMyInfo> {

    PullToRefreshListView listView;
    MagazineAdapter mAdapter;
    meHeaderView header;
    private Dialog dialog;

    public MeFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_me, container, false);


        dialog = UtilUi.showWaitDialog(getContext(), "Me 조회중..."); // 다이아로그 띄우기
        listView = (PullToRefreshListView)view.findViewById(R.id.me_listView);
        mAdapter = new MagazineAdapter();
        SetHeader();
        listView.setAdapter(mAdapter);
        SetModule();

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ModelMagazineQuery query = new ModelMagazineQuery();
                query.call_type = AppSetting.MAGAZINE_CALL_TYPE.QUESTIONER;
                query.aemail = AppGlobalSetting.WRITER_EMAIL;
                query.isFirstStart = false;
                Log.d("writermagazine", query.toString());
                if (dialog != null) {
                    UtilUi.hideWaitDialog(dialog);
                }
                dialog = UtilUi.showWaitDialog(getContext(), "Me reflesh 조회중..."); // 다이아로그 띄우기
                ModuleMagazineList.getMagazineList(MeFragment.this, query);
            }
        });

        listView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
            }
        });

        return view;
    }

    public void SetModule(){
        ModelMagazineQuery query = new ModelMagazineQuery();
        query.call_type = AppSetting.MAGAZINE_CALL_TYPE.QUESTIONER;
        query.qemail=AppGlobalSetting.getLocalLoginUser().getEmail();
        query.isFirstStart = true;
        ModuleMagazineList.getMagazineList(this,query);
    }

    public void SetHeader(){
        ModelMagazineQuery query = new ModelMagazineQuery();
        query.call_type = AppSetting.MAGAZINE_CALL_TYPE.QUESTIONER;
        query.qemail=AppGlobalSetting.getLocalLoginUser().getEmail();
        ModuleMyInfo.getMyInfo(this, query);
    }

    @Override
    public void CallResultOnemore(ModelMyInfo modelMyInfo) {
        UtilUi.hideWaitDialog(dialog);
        if( modelMyInfo.getData() == null){
            Toast.makeText(getActivity(), "더이상 불러올 데이터가 없습니다!", Toast.LENGTH_SHORT).show();
            return;
        }else {
            //해더뷰 설정
            ModelMyInfo.Data md = modelMyInfo.getData();
            meHeaderItem item = new meHeaderItem();
            item.setMyName(md.getNickname());
            item.setMyQuestion(String.valueOf(md.getSeed()));
            item.setQuestionNumber(String.valueOf(md.getQuestionCount()));

            header = new meHeaderView(getActivity().getApplicationContext());
            header.setMeHeaderInfo(item);
            listView.getRefreshableView().addHeaderView(header);
            header.MyProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity(), MeSettingActivity.class);
//                    startActivity(intent);
                    Intent intent = new Intent( v.getContext(), ZoomPictureActivity.class);
                    intent.putExtra("URL","me");
                    getActivity().startActivity(intent);
                }
            });
        }
        }

    @Override
    public void callResult(ModelMagazineList modelMagazineList) {
        if (modelMagazineList.getData() == null) {
            Toast.makeText(getActivity(),"더이상 불러올 데이터가 없습니다!", Toast.LENGTH_SHORT).show();
            UtilUi.hideWaitDialog(dialog);
            return;
        } else {
            for (int i = 0; i < modelMagazineList.getData().size(); i++) {
                ModelMagazineList.MagazineData mq = modelMagazineList.getData().get(i);
                Magazineitem e = new Magazineitem(Parcel.obtain());
                e.setContent(mq.getText());
                e.setDirectoy(mq.getCategory());
                e.setLike(String.valueOf(mq.getLike()));
                e.setSave(String.valueOf(mq.getSavedCount()));
                e.setWriter(mq.getAemail());
                e.setIsSave("false");
                e.setIsLike("false");
                e.setWrittenTime(mq.getWrittenTime());
                e.setNickname(mq.getNickname());

                List<ModelPicture> profileImageList =  new ArrayList<>();
                ModelPicture tempPicture = null;
                for ( int j=0 ; j < mq.getProfileimage().size(); j++){
                    tempPicture = new ModelPicture(
                            mq.getProfileimage().get(j).getOriginalPath() ,
                            mq.getProfileimage().get(j).getTh_path());
                    profileImageList.add(tempPicture); // 썸네일 이미지로 리스트 보여주기
                }
                // 이미지 세팅
                if( profileImageList.size() >0){
                    e.setProfileImageList(profileImageList);
                }

                // 이미지 경로를 가져옴( 0 ~ 2개)
                List<ModelPicture> imageList = new ArrayList<>();
                for (int j = 0; j < mq.getImage().size(); j++) {
                    tempPicture = new ModelPicture(
                            mq.getImage().get(j).getOriginalPath(),
                            mq.getImage().get(j).getTh_path());
                    imageList.add(tempPicture); // 썸네일 이미지로 리스트 보여주기
                }

                // 이미지 세팅
                if (imageList.size() > 0) {
                    e.setMagazineImageList(imageList);
                }


                mAdapter.add(e);
            }
        }
        UtilUi.hideWaitDialog(dialog);
    }

}
