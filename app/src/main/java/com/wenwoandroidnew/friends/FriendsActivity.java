package com.wenwoandroidnew.friends;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.friends.friendquestion.FriendQuestionActivity;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.model.ModelFriendList;
import com.wenwoandroidnew.system.module.ModuleFriend;


public class FriendsActivity extends AppCompatActivity implements CallResult<ModelFriendList> {

    ActionBar actionBar;
    ExpandableListView listView;
    FriendAdapter mAdapter;
    boolean bCheck = false;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_friend, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_friend_delete :
                if( bCheck == false){
                    for(int i = 0; i<mAdapter.getChildrenCount(1);i++){
                        ChildFriendItem cfi = (ChildFriendItem) mAdapter.getChild(1,i);
                        cfi.bDeleteCheck=true;
                    }
                    mAdapter.notifyDataSetChanged();
                    item.setIcon(getResources().getDrawable(R.drawable.select));
                    listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                    bCheck = true;
                }
                else{
                    Log.i("dd", "first");
                    for(int i = 0; i<mAdapter.getChildrenCount(1);i++){
                        ChildFriendItem cfi = (ChildFriendItem) mAdapter.getChild(1,i);
                        cfi.bDeleteCheck=false;
                    }
                    mAdapter.notifyDataSetChanged();
                    item.setIcon(getResources().getDrawable(R.drawable.delete));
                    listView.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
                    bCheck = false;
                }
                Toast.makeText(getApplicationContext(),"Friend delete",Toast.LENGTH_SHORT).show();
                return true;
            //내 QR코드 생성
            case android.R.id.home :
                AlertDialog.Builder builder = new AlertDialog.Builder(FriendsActivity.this);

                // 프로질사진아이콘 사진줄이기
                Drawable dr = AppGlobalSetting.myEmailQRCode;
                Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
                Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));

                builder.setIcon(d);
                builder.setTitle( AppGlobalSetting.getLocalLoginUser().getEmail());
                //QR코드 이미지 생성
                ImageView qrview = new ImageView(this);
                qrview.setBackgroundResource(R.drawable.qrview);

                builder.setView(qrview);
                AlertDialog dialog = builder.create();
                dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        View header = getLayoutInflater().inflate(R.layout.view_friend_header, null, false);
        ActionBarSetting();
        listView = (ExpandableListView)findViewById(R.id.friends_listView);
        mAdapter = new FriendAdapter();
        listView.addHeaderView(header);     //어댑터 셋팅하기전에 헤더붙여야함
        listView.setAdapter(mAdapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        listView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                listView.expandGroup(groupPosition);
            }
        });
       // initData();
        ModuleFriend.getFriendList( this, AppGlobalSetting.getLocalLoginUser().getEmail());
        if(bCheck==true) {
            listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    Toast.makeText(getApplicationContext(), "c click = " + childPosition, Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), "c click = " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void initData() {
        ChildFriendItem m = new ChildFriendItem();
//        m.FriendPicture=getResources().getDrawable(R.drawable.servicebi);
        m.FriendName="很高興";
        mAdapter.add("My Profile", m);
        for (int i = 0; i < 3; i++) {
            ChildFriendItem d = new ChildFriendItem();
//            d.FriendPicture=getResources().getDrawable(R.drawable.servicebi);
            d.FriendShare="10";
            d.FriendName="很高興";
/*            mAdapter.add("New", d);
            mAdapter.add("Favorite",d);*/
            mAdapter.add("Friends", d);
        }
    }

    public void onGoFriendDetail(View view){
        Intent i = new Intent(this, FriendQuestionActivity.class);
        startActivity(i);
    }


    @Override
    public void callResult(ModelFriendList modelFriendList) {
        ChildFriendItem m = new ChildFriendItem();
//        m.FriendPicture=getResources().getDrawable(R.drawable.servicebi);
        m.FriendName="很高興";
        mAdapter.add("My Profile", m);
        for( ModelFriendList.FriendData data : modelFriendList.getData()){

            ChildFriendItem d = new ChildFriendItem();
//            try {
//                d.FriendPicture = UtilCommon.urlToDrawableProfileImage( data.getProfileImage());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            d.Qemail = data.getQemail();
            d.FriendShare = data.getShareQnQ();
            d.FriendName= data.getNickName();
            d.Favorite=data.getFavorite();
//            d.FriendPicture=getResources().getDrawable(R.drawable.servicebi);

/*            mAdapter.add("New", d);
            mAdapter.add("Favorite",d);*/
            mAdapter.add("Friends", d);

        }
        for (int i = 0; i < mAdapter.getGroupCount(); i++) {
            listView.expandGroup(i);
        }
    }






    private void ActionBarSetting(){
        actionBar =getSupportActionBar();
        TextView textView = new TextView(getApplicationContext());
        textView.setText("Friends");
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(textView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        actionBar.setDisplayShowTitleEnabled(false);
        //홈아이콘 생성
        actionBar.setDisplayHomeAsUpEnabled(true);
        //홈아이콘 바꾸기
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.me_qrscancode_40x40);
    }
}
