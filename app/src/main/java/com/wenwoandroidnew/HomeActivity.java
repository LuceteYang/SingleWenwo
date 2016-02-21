package com.wenwoandroidnew;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.wenwoandroidnew.contents.ParentContentsFragment;
import com.wenwoandroidnew.discover.ParentDiscoverFragment;
import com.wenwoandroidnew.login.EmailLoginActivity;
import com.wenwoandroidnew.login.WechatLoginActivity;
import com.wenwoandroidnew.me.ParentMeFragment;
import com.wenwoandroidnew.newsfeed.NewsFeedFragment;
import com.wenwoandroidnew.newsfeed.answer.MyDialogFragmentListener;
import com.wenwoandroidnew.question.ParentQuestionFragment;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.BackPressCloseHandler;
import com.wenwoandroidnew.system.module.ModuleUser;

public class HomeActivity extends AppCompatActivity  implements MyDialogFragmentListener {
    FragmentTabHost tabHost;
    private BackPressCloseHandler backPressCloseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActionBar aBar = getSupportActionBar();
        aBar.setDisplayHomeAsUpEnabled(false);
        aBar.setDisplayShowTitleEnabled(false);
        Bundle b = getIntent().getExtras();
        tabHost = (FragmentTabHost) findViewById(R.id.tabHost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        tabHost.addTab(tabHost.newTabSpec("tab_newsfeed").setIndicator(null, getResources().getDrawable(R.drawable.tab_newsfeed)), NewsFeedFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab_contents").setIndicator(null, getResources().getDrawable(R.drawable.tab_contents)), ParentContentsFragment.class, null);
        if(b!=null) {
            Bundle myBundle = new Bundle();
            String status = b.getString("Status");
            String title = b.getString("title");
            String context = b.getString("context");
            myBundle.putString("Status", status);
            myBundle.putString("title",title);
            myBundle.putString("context",context);
            tabHost.addTab(tabHost.newTabSpec("tab_question").setIndicator(null, getResources().getDrawable(R.drawable.tab_question)), ParentQuestionFragment.class, myBundle);
        }else{
            tabHost.addTab(tabHost.newTabSpec("tab_question").setIndicator(null, getResources().getDrawable(R.drawable.tab_question)), ParentQuestionFragment.class, null);
        }
        tabHost.addTab(tabHost.newTabSpec("tab_discover").setIndicator(null, getResources().getDrawable(R.drawable.tab_discover)), ParentDiscoverFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab_me").setIndicator(null, getResources().getDrawable(R.drawable.tab_me)), ParentMeFragment.class, null);
        backPressCloseHandler = new BackPressCloseHandler(this);
        setTabColor(tabHost);
        if(b!=null){
            tabHost.setCurrentTab(2);
        }


        this.init();
    }

    private void init(){
        AppGlobalSetting.context = this;
    }


    public void OnGoCharge(View view){
        Intent intent = new Intent(this,ChargeSeedActivity.class);
        startActivity(intent);
    }

    public void onWechatLogin(View view) {
        Toast.makeText(getApplicationContext(), "서비스 준비중입니다.", Toast.LENGTH_SHORT).show();
    }

    public void onEmailLogin(View view) {
        startActivity(new Intent(getApplicationContext(), EmailLoginActivity.class));
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }

    public static void setTabColor(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFFFFF")); //unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFFFF")); // selected

    }

    public void OnLogout(View view) {
        String message;

        AppGlobalSetting.context =this;

        if (AppGlobalSetting.isLocalLogin() == true) {
            ModuleUser.logout();
            message = "Logout Success";
        } else {
            message = "로그인된 유져가아닙니다.";
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReturnValue(String foo) {
        Log.i("onReturnValue", "Got value " + foo + " back from Dialog!");
    }
}
