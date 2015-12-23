package com.wenwoandroidnew;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.model.LocalLoginUser;
import com.wenwoandroidnew.system.module.ModuleUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SampleActivity extends AppCompatActivity{

    @Bind(R.id.btn_login)
    Button btnLogin;

    @Bind(R.id.btn_logout)
    Button btnLogout;

    @Bind(R.id.tv_logininfo)
    TextView tvLoingInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        AppGlobalSetting.context = getApplicationContext();
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        this.init();
        checkUser();
    }

    private void init() {

        //
        LocalLoginUser localLoginUser = ModuleUser.doLocalLoginStatus();
        if( localLoginUser == null ){

        }
        else{

        }
    }

    private void checkUser() {

        if (AppGlobalSetting.isLocalLogin() == false) {
            this.btnLogout.setVisibility(View.GONE);
            this.btnLogin.setVisibility(View.VISIBLE);
            tvLoingInfo.setText("로그인상태 아님");

        } else {
            this.btnLogout.setVisibility(View.VISIBLE);
            this.btnLogin.setVisibility(View.GONE);
            tvLoingInfo.setText( AppGlobalSetting.getLocalLoginUser().getName() + "로그인 되었음");
        }
    }

    @OnClick(R.id.btn_login)
    void onBtnLogin() {
        //ModuleUser.login(this, "deepplin@naver.com", "한승진");
    }

    @OnClick(R.id.btn_logout)
    void onBtnLogout() {
        ModuleUser.logout();
    }


}
