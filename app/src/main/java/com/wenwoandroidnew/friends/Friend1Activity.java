package com.wenwoandroidnew.friends;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.friends.friendquestion.FriendQuestionActivity;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.common.CallResultOnemore;
import com.wenwoandroidnew.system.model.ModelFriendList;
import com.wenwoandroidnew.system.model.query.ModelFriendDeleteQuery;
import com.wenwoandroidnew.system.module.ModelPicture;
import com.wenwoandroidnew.system.module.ModuleFriend;
import com.wenwoandroidnew.system.util.UtilCommon;
import com.wenwoandroidnew.system.util.UtilUi;

import java.util.ArrayList;
import java.util.List;

public class Friend1Activity extends AppCompatActivity implements CallResult<ModelFriendList>, CallResultOnemore<Boolean> {
    ActionBar actionBar;
    ListView listView;
    Friend1Adapter mAdapter;
    boolean bCheck = false;
    private Dialog dialog;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_friend, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_friend_delete :
                if( bCheck == false){
                    for(int i = 3; i<mAdapter.getCount();i++) {
                        ChildFriendItem a = (ChildFriendItem)mAdapter.getItem(i);
                        a.setMeOrNotCanClick(false);
                        a.setbDeleteCheck(true);
                    }
                    mAdapter.notifyDataSetChanged();
                    item.setIcon(getResources().getDrawable(R.drawable.select));
                    bCheck = true;
                }
                else{
                    for(int i = 3; i<mAdapter.getCount();i++) {
                        ChildFriendItem a = (ChildFriendItem)mAdapter.getItem(i);
                        a.setMeOrNotCanClick(true);
                        a.setbDeleteCheck(false);
                    }
                    mAdapter.notifyDataSetChanged();
                    item.setIcon(getResources().getDrawable(R.drawable.delete));
                    bCheck = false;
                }
                return true;
            //내 QR코드 생성
            case android.R.id.home :
                AlertDialog.Builder builder = new AlertDialog.Builder(Friend1Activity.this);

                // 프로질사진아이콘 사진줄이기
                Drawable dr = AppGlobalSetting.myEmailQRCode;
                Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
                Drawable d = new BitmapDrawable(getResources(), bitmap);

                builder.setIcon(d);
                builder.setTitle( AppGlobalSetting.getLocalLoginUser().getEmail());
                //QR코드 이미지 생성
                ImageView qrview = new ImageView(this);

                qrview.setImageDrawable( d);
                qrview.setScaleType(ImageView.ScaleType.FIT_CENTER);
                builder.setView(qrview);
                AlertDialog dialog = builder.create();
                dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        final FriendHeaderView header= new FriendHeaderView(getApplicationContext());
        ActionBarSetting();
        listView = (ListView)findViewById(R.id.friends1_listView);
        mAdapter = new Friend1Adapter();
        listView.addHeaderView(header);     //어댑터 셋팅하기전에 헤더붙여야함
        listView.setAdapter(mAdapter);
        dialog = UtilUi.showWaitDialog(Friend1Activity.this , "Friend 조회중..."); // 다이아로그 띄우기
        ModuleFriend.getFriendList(this, AppGlobalSetting.getLocalLoginUser().getEmail());

        header.etView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String keyword = s.toString();
                searchFriend(keyword);
//                listView.setFilterText(s.toString());
//                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(header.etView==null)
        {
            Toast.makeText(getApplicationContext(), "비었습니다", Toast.LENGTH_SHORT).show();
        }

        //editText때문에 불필요하게 키패드가 올라오는 것을 방지
        //editText를 누를 때만 팝업되도록한다.
        header.etView.setInputType(0);
        header.etView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                header.etView.setInputType(1);

                InputMethodManager mg = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                mg.showSoftInput(header.etView, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        //친구 글보기
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (mAdapter.getItemViewType(position - 1) == 0) {
                } else if (mAdapter.getItemViewType(position - 1) == 1) {
                    if (bCheck == true && position > 2) {
                        ChildFriendItem b = (ChildFriendItem) ((ChildFriendView) view).getChildFiendInfo();
                        String myfriend = b.getQemail();
                        String me = AppGlobalSetting.getLocalLoginUser().getEmail();
                        final ModelFriendDeleteQuery q = new ModelFriendDeleteQuery();
                        q.myfriend = myfriend;
                        q.me = me;
                        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(Friend1Activity.this);
                        myAlertDialog.setTitle("친구 삭제");
                        myAlertDialog.setMessage("이 친구를 정말 삭제하시겠습니까??ㅠㅠ");
                        myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                ModuleFriend.friendDelete(Friend1Activity.this, q);
                                mAdapter.removeItem(position - 1);
                                mAdapter.notifyDataSetChanged();
                            }
                        });
                        myAlertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });
                        myAlertDialog.show();


                    } else if (bCheck == false && position > 2) {
                        ChildFriendView a = (ChildFriendView) view;
                        ChildFriendItem b = (ChildFriendItem) ((ChildFriendView) view).getChildFiendInfo();
                        String FriendName = b.getFriendName();
                        String FriendShare = b.getFriendShare();
                        String Qemail = b.getQemail();
                        String Favorite = b.getFavorite();
                        Intent i = new Intent(Friend1Activity.this, FriendQuestionActivity.class);
                        i.putExtra("Favorite", Favorite);
                        i.putExtra("qemail", Qemail);
                        i.putExtra("FriendName", FriendName);
                        i.putExtra("FriendShare", FriendShare);
                        if(b.getFriendPicture()!=null){
                        for ( int j=0 ; j < b.getFriendPicture().size(); j++) {
                            i.putExtra("FriendProfile", b.getFriendPicture().get(0).getOrigin());
                            i.putExtra("FriendThProfile", b.getFriendPicture().get(0).getTh());
                        }}
                        startActivity(i);
                    }

                }
//                    Toast.makeText(getApplicationContext(),Integer.toString(position),Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }


    public void onGoFriendDetail(View view){
        Intent i = new Intent(this, FriendQuestionActivity.class);
        startActivity(i);
    }

    @Override
    public void callResult(ModelFriendList modelFriendList) {
        UtilUi.hideWaitDialog(dialog);
        GroupFriendItem g = new GroupFriendItem();
        String name = "My Profile";
        g.GroupName=name;
        mAdapter.add(g);
        ChildFriendItem m = new ChildFriendItem();
        m.MeOrNot=true;
        Log.i("dd",AppGlobalSetting.getLocalLoginUser().getName());
        m.FriendName= AppGlobalSetting.getLocalLoginUser().getName();
        mAdapter.add(m);
        String name1 = "My Friends";
        GroupFriendItem g1 = new GroupFriendItem();
        g1.GroupName=name1;
        mAdapter.add(g1);
        for( ModelFriendList.FriendData data : modelFriendList.getData()){

            ChildFriendItem d = new ChildFriendItem();
            d.Qemail = data.getQemail();
            d.MeOrNot=false;
            d.FriendShare = data.getShareQnQ();
            d.FriendName= data.getNickName();
            d.Favorite=data.getFavorite();
            List<ModelPicture> imageList =  new ArrayList<>();
            ModelPicture tempPicture = null;
            for ( int j=0 ; j < data.getImage().size(); j++) {

                tempPicture = new ModelPicture(
                        data.getImage().get(j).getOriginalPath(),
                        data.getImage().get(j).getTh_path());

                imageList.add(tempPicture); // 썸네일 이미지로 리스트 보여주기
            }
            // 이미지 세팅
            if( imageList.size() >0){
                d.setFriendPicture(imageList);
            }

            mAdapter.add(d);

        }
    }

    @Override
    public void CallResultOnemore(Boolean Boolean) {
        String message = "";
        if( Boolean.booleanValue() == Boolean.TRUE){
            message = "친구삭제 완료";
            mAdapter.notifyDataSetChanged();
        }
        else{
            message = "친구삭제 실패";
        }
        Toast.makeText(Friend1Activity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void searchFriend(String keyword) {
        if (!TextUtils.isEmpty(keyword)) {
            mAdapter.clear();
//            for (MovieItem item : result.items) {
//                mAdapter.add(item);
//            }
        } else {
            mAdapter.clear();
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
