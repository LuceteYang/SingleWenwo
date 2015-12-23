package com.wenwoandroidnew.me;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenwoandroidnew.ChargeSeedActivity;
import com.wenwoandroidnew.LoginRequestFragment;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.friends.Friend1Activity;
import com.wenwoandroidnew.system.AppGlobalSetting;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParentMeFragment extends Fragment {

    private static final String ME_TAG = "me";
    private static final String LOGIN_TAG = "login";
    ActionBar actionBar;

    public ParentMeFragment() {
        // Required empty public constructor
        this.setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(AppGlobalSetting.isLocalLogin()){
            inflater.inflate(R.menu.menu_me, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //내 QR코드 보기
            case android.R.id.home:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // 프로질사진아이콘 사진줄이기
                Drawable dr = AppGlobalSetting.myEmailQRCode;
                Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
                Drawable d = new BitmapDrawable(getResources(), bitmap);

                builder.setIcon(d);
                builder.setTitle( AppGlobalSetting.getLocalLoginUser().getEmail());
                //QR코드 이미지 생성
                ImageView qrview = new ImageView(getContext());

                qrview.setImageDrawable( d);
                qrview.setScaleType(ImageView.ScaleType.FIT_CENTER);
                builder.setView(qrview);
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            //해바라기씨 충전소 이동
            case  R.id.menu_me_charge :
                Intent intent = new Intent(getActivity(), ChargeSeedActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    Fragment fMe,fLogin;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parent_me, container, false);

        //액션바설정
        TextView textView = new TextView(getActivity());
        textView.setText("Me");
        textView.setTextColor(getResources().getColor(R.color.colorWhite));
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(textView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        actionBar.setDisplayShowTitleEnabled(false);
        if(AppGlobalSetting.isLocalLogin()) {
            //홈아이콘 생성
            actionBar.setDisplayHomeAsUpEnabled(true);
            //홈아이콘 바꾸기
            actionBar.setHomeAsUpIndicator(R.drawable.me_qrscancode_40x40);
        }

        //로그인상황에 따른 페이지변환
        MeFragmentChange();

        return view;
    }

    private void MeFragmentChange(){
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        if(AppGlobalSetting.isLocalLogin()){
            if(getChildFragmentManager().findFragmentByTag(ME_TAG)==null){
                if(fMe==null){
                    fMe = new MeFragment();
                }
                if(getChildFragmentManager().findFragmentByTag(LOGIN_TAG)==null){
                    //로그인요구페이지가 없을때
                    ft.add(R.id.fragment_parent_me, fMe, ME_TAG);
                }else{
                    //로그인요구페이지가 전에 떠있을때
                    ft.detach(getChildFragmentManager().findFragmentByTag(LOGIN_TAG));
                    ft.add(R.id.fragment_parent_me, fMe, ME_TAG);
                }
            }
            ft.commit();
        }else{
            if(getChildFragmentManager().findFragmentByTag(LOGIN_TAG)==null){
                if(fLogin==null){
                    fLogin = new LoginRequestFragment().newInstance("Me");
                }
                if(getChildFragmentManager().findFragmentByTag(ME_TAG)==null){
                    //퀘스쳔페이지가 없을때
                    ft.add(R.id.fragment_parent_me, fLogin, LOGIN_TAG);
                }else{
                    //퀘스쳔페이지가 전에 떠있을때
                    ft.detach(getChildFragmentManager().findFragmentByTag(ME_TAG));
                    ft.add(R.id.fragment_parent_me, fLogin, LOGIN_TAG);
                }
            }
            ft.commit();
        }

    }


}
