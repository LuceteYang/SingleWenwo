package com.wenwoandroidnew.contents.magazineDetail;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.contents.Magazineitem;
import com.wenwoandroidnew.system.module.ModelPicture;
import com.wenwoandroidnew.system.util.UtilUi;
import com.wenwoandroidnew.writer.WriterActivity;

import java.util.ArrayList;
import java.util.List;


public class MagazineDetailActivity extends AppCompatActivity {

    private Dialog dialog;
    ActionBar actionBar;
    ListView listView;
    MagazineDetailAdapter mAdapter;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //내 QR코드 보기
            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazine_detail);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorSplash));
        }
        final MagazineDetailHeaderItem item = new MagazineDetailHeaderItem();
        Intent i = getIntent();
        final Magazineitem contents = i.getParcelableExtra("tiger");
        item.setWriterName(contents.getNickname());
        item.setContent(contents.getContent());
        item.setTime("23 mins");
        item.setEmail(contents.getWriter());
        item.setWriterImage(getResources().getDrawable(R.drawable.servicebi));
        item.setPlace("낙성대역");
        List<ModelPicture> profileImageList =  new ArrayList<>();
        ModelPicture tempPicture = null;
        for ( int j=0 ; j < contents.getProfileImageList().size(); j++){
            tempPicture = new ModelPicture(
                    contents.getProfileImageList().get(j).getOrigin() ,
                    contents.getProfileImageList().get(j).getTh());
            profileImageList.add(tempPicture); // 썸네일 이미지로 리스트 보여주기
        }
        // 이미지 세팅
        if( profileImageList.size() >0){
            item.setProfileImageList(profileImageList);
        }

        MagazineDetailHeaderView headerview = new MagazineDetailHeaderView(getApplicationContext());
        headerview.setMagazineDetailHeaderInfo(item);

        listView = (ListView)findViewById(R.id.magazine_detail_listView);
        listView.addHeaderView(headerview);
        mAdapter = new MagazineDetailAdapter();
        listView.setAdapter(mAdapter);
        initData( contents);
        headerview.ivWriter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MagazineDetailActivity.this, WriterActivity.class);
                String email =  item.getEmail();
                i.putExtra("aemail",email);
                startActivity(i);
            }
        });
    }

    public void OnMagazineDetailCancel(View view){
        finish();
    }

    private void initData( Magazineitem contents) {
        MagazineDetailImageItem m = new MagazineDetailImageItem();

        List<ModelPicture> tempImageList = new ArrayList<>();

        // 이미지 배열로 저장
        for( ModelPicture modelPicture : contents.getMagazineImageList()){
            tempImageList.add( modelPicture);
        }

        m.setImageList( tempImageList);

        mAdapter.add(m);
    }

}
