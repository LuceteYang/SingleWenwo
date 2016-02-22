package com.wenwoandroidnew.newsfeed.myfeed;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wenwoandroidnew.HomeActivity;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.newsfeed.FeedAdapter;
import com.wenwoandroidnew.newsfeed.QuestionItem;
import com.wenwoandroidnew.newsfeed.allfeed.AllFeedFragment;
import com.wenwoandroidnew.newsfeed.answer.AnswerListFragment;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.model.ModelQuestionList;
import com.wenwoandroidnew.system.model.query.ModelQuestionQuery;
import com.wenwoandroidnew.system.module.ModelPicture;
import com.wenwoandroidnew.system.module.ModuleQuestion;
import com.wenwoandroidnew.system.util.AppSetting;
import com.wenwoandroidnew.system.util.UtilUi;

import java.util.ArrayList;
import java.util.List;


public class MyFeedAcceptFragment extends Fragment implements CallResult<ModelQuestionList> {
    private PullToRefreshListView listView;
    private FeedAdapter mAdapter;

    private Dialog dialog;

    public MyFeedAcceptFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dialog = UtilUi.showWaitDialog(getContext(), "My Feed Adoption 조회중..."); // 다이아로그 띄우기

        View view = inflater.inflate(R.layout.fragment_my_feed_accept, container, false);
        listView = (PullToRefreshListView)view.findViewById(R.id.my_freed_accept_listView);
        mAdapter = new FeedAdapter();
        listView.setAdapter(mAdapter);
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
                    Intent intent = new Intent(getContext(), HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("modify","TRUE");
                    intent.putExtra("Status",item.getType());
                    intent.putExtra("title",item.getQuestionTitle());
                    intent.putExtra("context",item.getQuestionContext());
                    startActivity(intent);
                }
            }
        });

        // 질문 쿼리 생성
        ModelQuestionQuery query = new ModelQuestionQuery();
        query.call_type = AppSetting.FEED_CALL_TYPE.MY_ACCEPT; // 리스트 타입을 넣어줌
        query.status = "0";
        query.isFirstStart = true;
        ModuleQuestion.getQuestionList(this, query);

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ModelQuestionQuery query = new ModelQuestionQuery();
                query.call_type = AppSetting.FEED_CALL_TYPE.MY_ACCEPT; // 리스트 타입을 넣어줌
                query.isFirstStart = false;
                query.status = "0";
                if (dialog != null) {
                    UtilUi.hideWaitDialog(dialog);
                }

                dialog = UtilUi.showWaitDialog(getContext(), "My Feed Adoption refleshing 조회중..."); // 다이아로그 띄우기
                ModuleQuestion.getQuestionList(MyFeedAcceptFragment.this, query);
            }
        });

        listView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
            }
        });
        return view;
    }

    @Override
    public void callResult(ModelQuestionList modelQuestionList) {

        if( modelQuestionList.getData() == null){
            Toast.makeText(getActivity(), "더이상 불러올 데이터가 없습니다!", Toast.LENGTH_SHORT).show();
            listView.setRefreshing(false);
            return;
        }
        for (int i = 0; i < modelQuestionList.getData().size(); i++) {

            ModelQuestionList.ModelData mq = modelQuestionList.getData().get(i);
            QuestionItem d = new QuestionItem(Parcel.obtain());
            Drawable questionIcon;
            d.questionCategory = mq.getCategory();//"Emergency";
            d.voteNumber = Integer.parseInt(mq.getSpentSeed());
            d.questionLocation = mq.getSi() + " " + mq.getDu() + " " + mq.getDong(); //"낙성대역 근처";
            d.questionPName = AppGlobalSetting.getLocalLoginUser().getName();
            d.questionTitle = mq.getTitle();//"안녕하세요";
            d.questionAccept =Integer.toString(mq.getAnum().size());
            d.questionTime = mq.getDueTime();//"2015년 5월 8일";
            d.questionContext = mq.getText();//"낙성대역 근처 치킨집좀 알려주세용~~";
            d.status = Integer.toString(mq.getStatus());
            d.qnum = mq.getQnum();
            d.setType(Integer.toString(mq.getType()));
            d.setIsAllfeed(false);

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

        Log.d(Integer.toString(modelQuestionList.getData().size()),"dd");
        if(modelQuestionList.getData().size()<6){
            listView.setRefreshing(false);
        }
        UtilUi.hideWaitDialog(dialog);
    }


}
