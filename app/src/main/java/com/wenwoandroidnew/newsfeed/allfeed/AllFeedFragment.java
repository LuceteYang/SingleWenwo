package com.wenwoandroidnew.newsfeed.allfeed;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.ErrorDialogFragment;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wenwoandroidnew.system.manager.PropertyManager;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.newsfeed.FeedAdapter;
import com.wenwoandroidnew.newsfeed.QuestionItem;
import com.wenwoandroidnew.newsfeed.answer.AnswerListFragment;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.model.ModelQuestionList;
import com.wenwoandroidnew.system.model.query.ModelQuestionQuery;
import com.wenwoandroidnew.system.module.ModelPicture;
import com.wenwoandroidnew.system.module.ModuleQuestion;
import com.wenwoandroidnew.system.util.AppSetting;
import com.wenwoandroidnew.system.util.UtilUi;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class AllFeedFragment extends Fragment implements  CallResult<ModelQuestionList>{
    FeedAdapter mAdapter;
//이게 데모용
    private PullToRefreshListView listView;
    private Dialog dialog;

    public AllFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_feed, container, false);
        listView = (PullToRefreshListView) view.findViewById(R.id.all_freed_listView);
        mAdapter = new FeedAdapter();
        listView.setAdapter(mAdapter);
        Log.i("registrationToken", PropertyManager.getInstance().getRegistrationToken());
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    QuestionItem item = mAdapter.getItem(position-1);
                    if(item.getStatus().equals("0")){
                        AnswerListFragment dialog = new AnswerListFragment();
                        Bundle b = new Bundle();
                        b.putParcelable("question", item);
                        dialog.setArguments(b);
                        dialog.show(getActivity().getSupportFragmentManager(), "answer");
                    }else if(item.getStatus().equals("1")){
                        AnswerListFragment dialog = new AnswerListFragment();
                        Bundle b = new Bundle();
                        b.putParcelable("question", item);
                        dialog.setArguments(b);
                        dialog.show(getActivity().getSupportFragmentManager(), "answer");
                    }else if(item.getStatus().equals("2")){
                        Toast.makeText(getActivity(),"질문수정....준비중입니다",Toast.LENGTH_SHORT).show();
                    }
                    }
            });

        ModelQuestionQuery query = new ModelQuestionQuery();
        query.call_type = AppSetting.FEED_CALL_TYPE.ALL; // 리스트 타입을 넣어줌
        query.isFirstStart = true;

        dialog = UtilUi.showWaitDialog(getContext() , "ALL Feed 조회중..."); // 다이아로그 띄우기
        ModuleQuestion.getQuestionList(this, query);

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ModelQuestionQuery query = new ModelQuestionQuery();
                query.call_type = AppSetting.FEED_CALL_TYPE.ALL; // 리스트 타입을 넣어줌
                query.isFirstStart = false;

                if (dialog != null) {
                    UtilUi.hideWaitDialog(dialog);
                }
                dialog = UtilUi.showWaitDialog(getContext(), "ALL Feed refleshing 조회중..."); // 다이아로그 띄우기
                ModuleQuestion.getQuestionList(AllFeedFragment.this, query);
            }
        });

        return view;
    }
    @Override
    public void callResult(ModelQuestionList modelQuestionList) {


        listView.setRefreshing(false);
        if (modelQuestionList.getData() == null) {
            Toast.makeText(getActivity(), "더이상 불러올 데이터가 없습니다!", Toast.LENGTH_SHORT).show();
            UtilUi.hideWaitDialog(dialog);
            return;
        }
        for (int i = 0; i < modelQuestionList.getData().size(); i++) {

            ModelQuestionList.ModelData mq = modelQuestionList.getData().get(i);
            QuestionItem d = new QuestionItem(Parcel.obtain());
            Drawable questionIcon;
            d.questionCategory = mq.getCategory();//"Emergency";
            d.voteNumber = Integer.parseInt(mq.getSpentSeed());
            d.questionLocation = mq.getSi() + " " + mq.getDu() + " " + mq.getDong(); //"낙성대역 근처";
            d.questionPName = mq.getNickName();//"박상환";
            d.questionTitle = mq.getTitle();//"안녕하세요";
            d.questionAccept =Integer.toString(mq.getAnum().size());
            d.questionTime = mq.getDueTime();//"2015년 5월 8일";
            d.questionContext = mq.getText();//"낙성대역 근처 치킨집좀 알려주세용~~";
            d.status = Integer.toString(mq.getStatus());
            d.qnum = mq.getQnum();
            ModelPicture tempPicture = null;
            for ( int j=0 ; j < mq.getImage().size(); j++) {
                List<ModelPicture> imageList =  new ArrayList<>();
                tempPicture = new ModelPicture(
                        mq.getImage().get(j).getOriginalPath() ,
                        mq.getImage().get(j).getTh_path());
                imageList.add(tempPicture); // 썸네일 이미지로 리스트 보여주기
                if(j==0){
                    d.setQuestionImage1(imageList);
                }else{
                    d.setQuestionImage2(imageList);
                }
            }
            if(mq.getProfileImage()!=null) {
                for (int k = 0; k < mq.getProfileImage().size(); k++) {
                    List<ModelPicture> ProfileimageList = new ArrayList<>();
                    tempPicture = null;
                    tempPicture = new ModelPicture(
                            mq.getProfileImage().get(0).getOriginalPath(),
                            mq.getProfileImage().get(0).getTh_path());
                    ProfileimageList.add(tempPicture); // 썸네일 이미지로 리스트 보여주기
                    d.setProfileList(ProfileimageList);
                }
            }
            d.setType(Integer.toString(mq.getType()));
//            0:text, 1:image, 2:recording
                if (mq.getType() == 0) {
                    questionIcon = getResources().getDrawable(R.drawable.blank);
            } else if (mq.getType() == 1) {
                questionIcon = getResources().getDrawable(R.drawable.myfeed_img);
            } else {
                questionIcon = getResources().getDrawable(R.drawable.myfeed_voice);
            }

            d.questionIcon = questionIcon;

            mAdapter.add(d);
        }
        UtilUi.hideWaitDialog(dialog);
    }
}
