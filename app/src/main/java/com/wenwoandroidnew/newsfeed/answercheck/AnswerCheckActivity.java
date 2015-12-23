package com.wenwoandroidnew.newsfeed.answercheck;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.newsfeed.QuestionItem;
import com.wenwoandroidnew.newsfeed.answer.AnswerItem;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.common.CallResultOnemore;
import com.wenwoandroidnew.system.model.ModelAnswerList;
import com.wenwoandroidnew.system.model.query.ModelAnswerPickQuery;
import com.wenwoandroidnew.system.module.ModelPicture;
import com.wenwoandroidnew.system.module.ModuleAnswer;
import com.wenwoandroidnew.system.util.UtilUi;

import java.util.ArrayList;
import java.util.List;


public class AnswerCheckActivity extends AppCompatActivity implements CallResult<ModelAnswerList>, CallResultOnemore<Boolean> {
    ListView listView;
    CheckAnswerAdapter mAdapter;
    ActionBar actionBar;
    private Dialog dialog;
    private QuestionItem item;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_answer_check,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_answer_choice_complete :
                finish();
                return true;
            case android.R.id.home :
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_check);

        actionBar =getSupportActionBar();
        //홈아이콘 생성
        actionBar.setDisplayHomeAsUpEnabled(true);
        //홈아이콘 바꾸기
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.header_cancel_28x28);
        actionBar.setDisplayShowTitleEnabled(false);
        item = new QuestionItem(Parcel.obtain());
        listView = (ListView)findViewById(R.id.answer_check_listView);
        mAdapter = new CheckAnswerAdapter();
        listView.setAdapter(mAdapter);
        Intent i = getIntent();
        item.status = i.getStringExtra("status");
        item.qnum = i.getStringExtra("qnum");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckAnswerView view2 = (CheckAnswerView)view;
                final String anum=view2.AnswerInfo.getAnswernumber();
                AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(AnswerCheckActivity.this);
                myAlertDialog.setTitle("답변 채택");
                myAlertDialog.setMessage("이 답변을 채택하시겠습니까??");
                myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getApplicationContext(),"ANSWER_NUMBER"+anum,Toast.LENGTH_SHORT).show();
                        ModelAnswerPickQuery a = new ModelAnswerPickQuery();
                        a.anum=anum;
                        ModuleAnswer.doAnswerPick(AnswerCheckActivity.this, a);
                    }});
                myAlertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {

                    }});
                myAlertDialog.show();

            }
        });

        if(item.getStatus().equals("1")){
            ModuleAnswer.getAnswerList(this, Integer.parseInt(item.qnum), 0);
        }else if(item.getStatus().equals("0")){
            ModuleAnswer.getAnswerList(this, Integer.parseInt(item.qnum), 1);
        }

    }

    @Override
    public void callResult(ModelAnswerList modelAnswerList) {
        for (int i = 0; i < modelAnswerList.getData().size(); i++) {
            ModelAnswerList.AnswerData mq = modelAnswerList.getData().get(i);
            AnswerItem d = new AnswerItem();
            ModelPicture tempPicture = null;
            for(int k=0;k<mq.getProfileImage().size();k++){
                List<ModelPicture> ProfileimageList =  new ArrayList<>();
                tempPicture = null;
                tempPicture = new ModelPicture(
                        mq.getProfileImage().get(0).getOriginalPath() ,
                        mq.getProfileImage().get(0).getTh_path());
                ProfileimageList.add(tempPicture); // 썸네일 이미지로 리스트 보여주기
                d.setProfileList(ProfileimageList);
            }
            d.AnswerContent=mq.getText();
            d.AnswerAccept="86%";
            d.AnswerLevel="1";
            d.AnswerName=mq.getNickname();
            d.Answernumber=Integer.toString(mq.getAnum());
            mAdapter.add(d);
        }
    }

    @Override
    public void CallResultOnemore(Boolean aBoolean) {
        String message;
        if( aBoolean.booleanValue() == Boolean.TRUE){
            message = "답변채택완료`";
        }
        else{
            message = "답변채택완료";
        }
        Toast.makeText(AnswerCheckActivity.this, message, Toast.LENGTH_SHORT).show();
        finish();
    }
}
