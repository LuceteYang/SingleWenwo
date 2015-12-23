package com.wenwoandroidnew.discover;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wenwoandroidnew.LoginRequestFragment;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.friends.Friend1Activity;
import com.wenwoandroidnew.friends.FriendsActivity;
import com.wenwoandroidnew.system.AppGlobalSetting;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParentDiscoverFragment extends Fragment {

    ActionBar actionBar;
    private static final String DISCOVER_TAG = "me";
    private static final String LOGIN_TAG = "login";

    public ParentDiscoverFragment() {
        // Required empty public constructor
        this.setHasOptionsMenu(true);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(AppGlobalSetting.isLocalLogin()){
            inflater.inflate(R.menu.menu_news_feed, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_news_feed_friend :
                Intent intent = new Intent(getActivity(), Friend1Activity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    Fragment fDisvoer,fLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parent_discover, container, false);
        //로그인상황에 따른 페이지변환
        DiscoverFragmentChange();

        //액션바 설정
        TextView textView = new TextView(getActivity());
        textView.setText("Discover");
        textView.setTextColor(getResources().getColor(R.color.colorWhite));
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(textView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);

        return view;
    }
    private void DiscoverFragmentChange(){
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        if(AppGlobalSetting.isLocalLogin()){
            if(getChildFragmentManager().findFragmentByTag(DISCOVER_TAG)==null){
                if(fDisvoer==null){
                    fDisvoer = new DiscoverFragment();
                }
                if(getChildFragmentManager().findFragmentByTag(LOGIN_TAG)==null){
                    //로그인요구페이지가 없을때
                    ft.add(R.id.fragment_parent_discover, fDisvoer, DISCOVER_TAG);
                }else{
                    //로그인요구페이지가 전에 떠있을때
                    ft.detach(getChildFragmentManager().findFragmentByTag(LOGIN_TAG));
                    ft.add(R.id.fragment_parent_discover, fDisvoer, DISCOVER_TAG);
                }
            }
            ft.commit();
        }else{
            if(getChildFragmentManager().findFragmentByTag(LOGIN_TAG)==null){
                if(fLogin==null){
                    fLogin = new LoginRequestFragment().newInstance("Discover");
                }
                if(getChildFragmentManager().findFragmentByTag(DISCOVER_TAG)==null){
                    //퀘스쳔페이지가 없을때
                    ft.add(R.id.fragment_parent_discover, fLogin, LOGIN_TAG);
                }else{
                    //퀘스쳔페이지가 전에 떠있을때
                    ft.detach(getChildFragmentManager().findFragmentByTag(DISCOVER_TAG));
                    ft.add(R.id.fragment_parent_discover, fLogin, LOGIN_TAG);
                }
            }
            ft.commit();
        }
    }

}
