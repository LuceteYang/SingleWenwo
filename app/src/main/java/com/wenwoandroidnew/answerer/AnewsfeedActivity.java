package com.wenwoandroidnew.answerer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.newsfeed.FeedAdapter;
import com.wenwoandroidnew.newsfeed.QuestionItem;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.manager.PropertyManager;
import com.wenwoandroidnew.system.model.ModelQuestionList;
import com.wenwoandroidnew.system.model.query.ModelQuestionQuery;
import com.wenwoandroidnew.system.module.ModelPicture;
import com.wenwoandroidnew.system.module.ModuleQuestion;
import com.wenwoandroidnew.system.util.AppSetting;
import com.wenwoandroidnew.system.util.UtilUi;

import java.util.ArrayList;
import java.util.List;

public class AnewsfeedActivity extends AppCompatActivity implements CallResult<ModelQuestionList> {

    private PullToRefreshListView listView;
    private FeedAdapter mAdapter;
    ActionBar actionBar;
    private Dialog dialog;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // XML로 옵션메뉴 추가 하기
         getMenuInflater().inflate(R.menu.menu_answerer, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_answerer:
                AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(AnewsfeedActivity.this);
                myAlertDialog.setTitle("로그아웃");
                myAlertDialog.setMessage("로그아웃 하시겠습니까?");
                myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
//                        ModuleUser.logout();
                        Toast.makeText(AnewsfeedActivity.this, "로그아웃완료", Toast.LENGTH_SHORT).show();
                    }
                });
                myAlertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
                myAlertDialog.show();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        listView = (PullToRefreshListView)findViewById(R.id.answer_listView);

        Log.d("rid", PropertyManager.getInstance().getRegistrationToken());
        //액션바 설정
        TextView textView = new TextView(getApplicationContext());
        textView.setText("NewsFeed");
        textView.setTextColor(getResources().getColor(R.color.colorWhite));
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(textView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);

        mAdapter = new FeedAdapter();
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QuestionItem item = mAdapter.getItem(position - 1);
                if (item.getStatus().equals("0")) {
                    AnswererListFragment dialog = new AnswererListFragment();
                    Bundle b = new Bundle();
                    b.putParcelable("question", item);
                    dialog.setArguments(b);
                    dialog.show(getSupportFragmentManager(), "answer");
                } else if (item.getStatus().equals("1")) {
                    AnswererListFragment dialog = new AnswererListFragment();
                    Bundle b = new Bundle();
                    b.putParcelable("question", item);
                    dialog.setArguments(b);
                    dialog.show(getSupportFragmentManager(), "answer");
                } else if (item.getStatus().equals("2")) {
                    Toast.makeText(AnewsfeedActivity.this, "질문수정....준비중입니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ModelQuestionQuery query = new ModelQuestionQuery();
        query.call_type = AppSetting.FEED_CALL_TYPE.ALL; // 리스트 타입을 넣어줌
        query.isFirstStart = true;

        dialog = UtilUi.showWaitDialog(AnewsfeedActivity.this, "NewsFeed 조회중..."); // 다이아로그 띄우기
        ModuleQuestion.getQuestionList(AnewsfeedActivity.this, query);
    }

    @Override
    public void callResult(ModelQuestionList modelQuestionList) {
        listView.setRefreshing(false);
        if (modelQuestionList.getData() == null) {
            Toast.makeText(AnewsfeedActivity.this, "더이상 불러올 데이터가 없습니다!", Toast.LENGTH_SHORT).show();
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
