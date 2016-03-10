package com.wenwoandroidnew.writer;

import android.content.Intent;
import android.graphics.Color;
import android.preference.PreferenceActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.wenwoandroidnew.ChargeSeedActivity;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.me.meHeaderView;
import com.wenwoandroidnew.newsfeed.answer.MyDialogFragmentListener;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.model.ModelAnswererInfo;
import com.wenwoandroidnew.system.module.ModelPicture;
import com.wenwoandroidnew.system.util.AppSetting;

import java.util.ArrayList;
import java.util.List;

public class WriterActivity extends AppCompatActivity implements CallResult<ModelAnswererInfo>, MyDialogFragmentListener {

    FragmentTabHost tabHost;
    WriterHeaderView header;
    WriterHeaderItem whi;
    ActionBar actionBar;

    public static class Constants {
        public static final String[] TAB_IDS = {"tab_writer_contents", "tab_writer_answer" };
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writer);
        header = (WriterHeaderView) findViewById( R.id.writer_header);

        //액션바 설정
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.header_cancel_28x28);

        Intent i = getIntent();
        String email =  i.getStringExtra("aemail");
        if( AppSetting.LOG_TYPE == true) {
            Log.i("writerpage", email);
        }
        AppGlobalSetting.WRITER_EMAIL = email;
        tabHost = (FragmentTabHost)findViewById(R.id.tabHost);
        tabHost.setup(getApplicationContext(), getSupportFragmentManager(), R.id.realtabcontent_writer);
        tabHost.addTab(tabHost.newTabSpec(Constants.TAB_IDS[0]).setIndicator(null, getResources().getDrawable(R.drawable.tab_writer_content)), WriterMagazineFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec(Constants.TAB_IDS[1]).setIndicator(null, getResources().getDrawable(R.drawable.tab_writer)), WriterAnswerFragment.class, null);
        setTabColor(tabHost);

        this.setWriterHead();

    }

    public static void setTabColor(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFFFFF")); //unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFFFF")); // selected

    }

    private void setWriterHead(){
        header.setTempHeader( AppGlobalSetting.answerImage, AppGlobalSetting.answerName);
    }

    @Override
    public void callResult(ModelAnswererInfo modelAnswererInfo) {
/*        ModelAnswererInfo mq = modelAnswererInfo;
        Log.i("ddd",mq.getData().toString());
        whi = new WriterHeaderItem();
        whi.setWriterName(mq.getData().getNickname());
        List<ModelPicture> ProfileimageList = new ArrayList<>();
        ModelPicture tempPicture = null;
        for (int j = 0; j < mq.getData().getProfileImage().size(); j++) {
            tempPicture = new ModelPicture(
                    mq.getData().getProfileImage().get(0).getOriginalPath(),
                    mq.getData().getProfileImage().get(0).getOriginalPath());
            ProfileimageList.add(tempPicture); // 썸네일 이미지로 리스트 보여주기
        }

        // 이미지 세팅
        if (ProfileimageList.size() > 0) {
            whi.setWriterProfile(ProfileimageList);
        }

        whi.setWriterAccept(String.valueOf(mq.getData().getAnswerRate()));
        whi.setWriterCoupon(String.valueOf(mq.getData().getSeedHistory()));
        header = new WriterHeaderView(getApplicationContext());
        header.setWriterHeaderInfo(whi);*/
    }

    @Override
    public void onReturnValue(String foo) {
        Log.i("onReturnValue", "Got value " + foo + " back from Dialog!");
    }
}
