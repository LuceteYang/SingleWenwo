package com.wenwoandroidnew.contents;


import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wenwoandroidnew.ImageCache;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.model.ModelMagazineList;
import com.wenwoandroidnew.system.model.query.ModelMagazineQuery;
import com.wenwoandroidnew.system.module.ModelPicture;
import com.wenwoandroidnew.system.module.ModuleMagazineList;
import com.wenwoandroidnew.system.util.AppSetting;
import com.wenwoandroidnew.system.util.UtilUi;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentsFragment extends Fragment implements CallResult<ModelMagazineList>{
    PullToRefreshListView listView;
    MagazineAdapter mAdapter;
    private Dialog dialog;

    public ContentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

            View view = inflater.inflate(R.layout.fragment_contents, container, false);

            listView = (PullToRefreshListView) view.findViewById(R.id.contents_listView);
            mAdapter = new MagazineAdapter();
            listView.setAdapter(mAdapter);
            ModelMagazineQuery query = new ModelMagazineQuery();
            query.call_type = AppSetting.MAGAZINE_CALL_TYPE.ALL;
            query.isFirstStart = true;
            dialog = UtilUi.showWaitDialog(getContext(), "Magazine 조회중..."); // 다이아로그 띄우기
            ModuleMagazineList.getMagazineList(this, query);

            listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    ModelMagazineQuery query = new ModelMagazineQuery();
                    query.call_type = AppSetting.MAGAZINE_CALL_TYPE.ALL;
                    query.aemail = AppGlobalSetting.WRITER_EMAIL;
                    query.isFirstStart = false;
                    if (dialog != null) {
                        UtilUi.hideWaitDialog(dialog);
                    }

                    dialog = UtilUi.showWaitDialog(getContext(), "Magazine refleshing조회중..."); // 다이아로그 띄우기
                    ModuleMagazineList.getMagazineList(ContentsFragment.this, query);
                }
            });



        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ImageCache.getInstance().flush();
        ImageCache.getInstance().close();
    }

    @Override
    public void callResult(ModelMagazineList modelMagazineList) {
        if( modelMagazineList.getData() == null){
            Toast.makeText(getActivity(), "더이상 불러올 데이터가 없습니다!", Toast.LENGTH_SHORT).show();
            UtilUi.hideWaitDialog(dialog);
            return;
        }else{
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
                List<ModelPicture> imageList =  new ArrayList<>();
                for ( int j=0 ; j < mq.getImage().size(); j++){
                    tempPicture = new ModelPicture(
                            mq.getImage().get(j).getOriginalPath() ,
                            mq.getImage().get(j).getTh_path());
                    imageList.add( tempPicture); // 썸네일 이미지로 리스트 보여주기
                }
                // 이미지 세팅
                if( imageList.size() >0){
                    e.setMagazineImageList( imageList );
                }


                mAdapter.add(e);
            }
        }
        UtilUi.hideWaitDialog(dialog);
    }
}
