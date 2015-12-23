package com.wenwoandroidnew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv_hi) TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind( this);
    }


    @OnClick(R.id.btn_show_sample) void onTempLogin( ){

        Intent i = new Intent( MainActivity.this, SampleActivity.class);
        startActivity(i);
    }
}
