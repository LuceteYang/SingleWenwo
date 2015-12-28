package com.wenwoandroidnew.answerer;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.newsfeed.QuestionItem;
import com.wenwoandroidnew.newsfeed.answer.AnswerAdapter;
import com.wenwoandroidnew.newsfeed.answer.AnswerFooterView;
import com.wenwoandroidnew.newsfeed.answer.AnswerItem;
import com.wenwoandroidnew.newsfeed.answercheck.AnswerCheckActivity;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.model.ModelAnswerList;
import com.wenwoandroidnew.system.module.ModelPicture;
import com.wenwoandroidnew.system.module.ModuleAnswer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class
        AnswererListFragment extends DialogFragment implements CallResult<ModelAnswerList> {

    private PullToRefreshListView listView;
//    ListView listView;
    private AnswererAdapter mAdapter;
    private QuestionItem item;
    private Dialog dialog;
    AnswerFooterView footerview;

    public AnswererListFragment() {
        // Required empty public constructor
    }

    @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_answerer_list, container, false);
        listView = (PullToRefreshListView)view.findViewById(R.id.answerer_listView);
        mAdapter = new AnswererAdapter();
        listView.setAdapter(mAdapter);

        //번들을 통해 값전달
        Bundle b = getArguments();
        item =b.getParcelable("question");

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                dismiss();
            }
        });
        listView.setLoadingDrawable(null);
        listView.setReleaseLabel(null);
        listView.setPullLabel(null);
        listView.setRefreshingLabel(null);

        // 서버에 데이터 호출
        if(item.getStatus().equals("1")){
            ModuleAnswer.getAnswerList(this, Integer.parseInt(item.getQnum()), 0);
        }else if(item.getStatus().equals("0")){
            ModuleAnswer.getAnswerList(this, Integer.parseInt(item.getQnum()), 1);
        }




        return view;
    }

    @Override
    public void callResult(ModelAnswerList modelAnswerList) {
        mAdapter.add(item);
        for (int i = 0; i < modelAnswerList.getData().size(); i++) {
            Drawable questionIcon;
            ModelAnswerList.AnswerData mq = modelAnswerList.getData().get(i);
            AnswerItem d = new AnswerItem();
            d.aemail=mq.getAemail();
            d.AnswerName = mq.getNickname();
            ModelPicture tempPicture = null;
            for ( int j=0 ; j < mq.getImage().size(); j++) {
                List<ModelPicture> imageList =  new ArrayList<>();
                tempPicture = new ModelPicture(
                        mq.getImage().get(j).getOriginalPath() ,
                        mq.getImage().get(j).getTh_path());
                imageList.add(tempPicture); // 썸네일 이미지로 리스트 보여주기
                if(j==0){
                    d.setAnswerImage1(imageList);
                }else{
                    d.setAnswerImage2(imageList);
                }
            }

            for(int k=0;k<mq.getProfileImage().size();k++){
                List<ModelPicture> ProfileimageList =  new ArrayList<>();
                tempPicture = null;
                tempPicture = new ModelPicture(
                        mq.getProfileImage().get(0).getOriginalPath() ,
                        mq.getProfileImage().get(0).getTh_path());
                ProfileimageList.add(tempPicture); // 썸네일 이미지로 리스트 보여주기
                d.setProfileList(ProfileimageList);
            }
            d.AnswerLevel= "Level: 1";
            d.AnswerAccept= "채택율 : 80%";
            d.AnswerContent= mq.getText() ;
            d.Answernumber=Integer.toString(mq.getAnum());
            d.Answernumber=Integer.toString(mq.getAnum());
            d.AnswerQnum=mq.getQnum();
            d.AnswerCategory=mq.getCategory();
            d.AnswerStatus=Integer.toString(mq.getStatus());
            d.aemail = mq.getAemail();

            if (mq.getType() == 0) {
                questionIcon = getResources().getDrawable(R.drawable.blank);
            } else if (mq.getType() == 1) {
                questionIcon = getResources().getDrawable(R.drawable.myfeed_img);
            } else {
                questionIcon = getResources().getDrawable(R.drawable.myfeed_voice);
            }
            d.AnswerType = questionIcon;
            mAdapter.add(d);
        }
    }
}
