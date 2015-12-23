package com.wenwoandroidnew.friends.friendquestion;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.newsfeed.FeedAdapter;
import com.wenwoandroidnew.newsfeed.QuestionItem;
import com.wenwoandroidnew.newsfeed.answer.AnswerListFragment;
import com.wenwoandroidnew.newsfeed.answer.MyDialogFragmentListener;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.model.ModelQuestionList;
import com.wenwoandroidnew.system.model.query.ModelemailQuery;
import com.wenwoandroidnew.system.module.ModelPicture;
import com.wenwoandroidnew.system.module.ModuleFriend;
import com.wenwoandroidnew.system.util.UtilUi;

import java.util.ArrayList;
import java.util.List;

public class FriendQuestionActivity extends AppCompatActivity implements CallResult<ModelQuestionList>, MyDialogFragmentListener {

    ActionBar actionBar;
    ListView listView;
    FeedAdapter mAdapter;
    FriendQuestionHeaderView headerView;
    boolean like=false;
    private Dialog dialog;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_question);
        listView =(ListView)findViewById(R.id.friend_quesiton_listView);
        dialog = UtilUi.showWaitDialog(FriendQuestionActivity.this, "FriendQuestion 조회중..."); // 다이아로그 띄우기
        ActionBarSetting();
        Intent i = getIntent();
        String qemail = i.getStringExtra("qemail");
        String Favorite = i.getStringExtra("Favorite");
        String FriendName = i.getStringExtra("FriendName");
        String FriendShare = i.getStringExtra("FriendShare");
        String FriendProfile = i.getStringExtra("FriendProfile");
        String FriendThProfile = i.getStringExtra("FriendThProfile");
        FriendQuestionHeaderItem a  = new FriendQuestionHeaderItem();
        a.setFriendName(FriendName);
        a.setQemail(qemail);
        a.setFavorite(Favorite);
        a.setFriendShare(FriendShare);
        List<ModelPicture> imageList =  new ArrayList<>();
        ModelPicture tempPicture = null;
            tempPicture = new ModelPicture(
                    FriendProfile,
                    FriendThProfile);

            imageList.add(tempPicture); // 썸네일 이미지로 리스트 보여주기
        // 이미지 세팅
        if( imageList.size() >0){
            a.setFriendPicture(imageList);
        }else{
            List<ModelPicture> nullimageList =  new ArrayList<>();
            a.setFriendPicture(nullimageList);
        }
        headerView = new FriendQuestionHeaderView( getApplicationContext());
        headerView.SetFriendQuestionHeaderInfo(a);
        listView.addHeaderView(headerView);
        mAdapter = new FeedAdapter();
        listView.setAdapter(mAdapter);

        headerView.LikeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (headerView.friendQuestionHeaderInfo.isLike() == true) {
                    headerView.friendQuestionHeaderInfo.setLike(false);
                    mAdapter.notifyDataSetChanged();
                } else {
                    headerView.friendQuestionHeaderInfo.setLike(true);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });



        ModelemailQuery q = new ModelemailQuery(qemail);
        ModuleFriend.getFriendQuestion(FriendQuestionActivity.this, q);
//        initData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                }else {
                    QuestionItem item = mAdapter.getItem(position-1);
                    if(item.getStatus().equals("0")){
                        AnswerListFragment dialog = new AnswerListFragment();
                        Bundle b = new Bundle();
                        b.putParcelable("question", item);
                        dialog.setArguments(b);
                        dialog.show(getSupportFragmentManager(), "answer");
                    }else if(item.getStatus().equals("1")){
                        AnswerListFragment dialog = new AnswerListFragment();
                        Bundle b = new Bundle();
                        b.putParcelable("question", item);
                        dialog.setArguments(b);
                        dialog.show(getSupportFragmentManager(), "answer");
                    }else if(item.getStatus().equals("2")){
                        Toast.makeText(FriendQuestionActivity.this,"질문수정....준비중입니다",Toast.LENGTH_SHORT).show();
                    }
                }
                }
        });
    }

    @Override
    public void callResult(ModelQuestionList modelQuestionList) {

        if (modelQuestionList.getData() == null) {
            Toast.makeText(FriendQuestionActivity.this, "더이상 불러올 데이터가 없습니다!", Toast.LENGTH_SHORT).show();
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
            d.questionAccept = Integer.toString(mq.getAnum().size());
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
            List<ModelPicture> ProfileimageList = new ArrayList<>();
            tempPicture = null;
            for (int j = 0; j < mq.getProfileImage().size(); j++) {
                tempPicture = new ModelPicture(
                        mq.getProfileImage().get(0).getOriginalPath(),
                        mq.getProfileImage().get(0).getTh_path());
                ProfileimageList.add(tempPicture); // 썸네일 이미지로 리스트 보여주기
            }
            if( ProfileimageList.size() >0){
                d.setProfileList(ProfileimageList );
            }


//            0:text, 1:image, 2:recording
            if (mq.getType() == 0) {
                questionIcon = getResources().getDrawable(R.drawable.myfeed_img);
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

    private void ActionBarSetting(){
        actionBar = getSupportActionBar();
        //홈아이콘 생성
        actionBar.setDisplayHomeAsUpEnabled(true);
        //홈아이콘 바꾸기
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.header_cancel_28x28);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        TextView textView = new TextView(this);
        textView.setText("친구 질문");
        textView.setTextColor(Color.WHITE);
        actionBar.setCustomView(textView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

    }

    @Override
    public void onReturnValue(String foo) {
        Log.i("onReturnValue", "Got value " + foo + " back from Dialog!");
    }
}
