package com.wenwoandroidnew.contents;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wenwoandroidnew.LoginRequestFragment;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.system.AppGlobalSetting;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParentContentsFragment extends Fragment {

    private static final String F5_TAG = "f5";
    private static final String F6_TAG = "f6";
    ActionBar actionBar;

    public ParentContentsFragment() {
        // Required empty public constructor
        this.setHasOptionsMenu(false);
    }
    Fragment f5,f6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parent_contents, container, false);

        //로그인상황에 따른 페이지변환
        ContentsFragmentChange();

        //액션바 설정
        TextView textView = new TextView(getActivity());
        textView.setText("Contents");
        textView.setTextColor(getResources().getColor(R.color.colorWhite));
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(textView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        actionBar.setDisplayShowTitleEnabled(false);

        return view;
    }

    private void ContentsFragmentChange(){
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        if(AppGlobalSetting.isLocalLogin()){
            if(getChildFragmentManager().findFragmentByTag(F5_TAG)==null){
                if(f5==null){
                    f5 = new ContentsFragment();
                }
                if(getChildFragmentManager().findFragmentByTag(F6_TAG)==null){
                    //로그인요구페이지가 없을때
                    ft.add(R.id.fragment_parent_contents, f5, F5_TAG);
                }else{
                    //로그인요구페이지가 전에 떠있을때
                    ft.detach(getChildFragmentManager().findFragmentByTag(F6_TAG));
                    ft.add(R.id.fragment_parent_contents, f5, F5_TAG);
                }
            }
            ft.commit();
        }else{
            if(getChildFragmentManager().findFragmentByTag(F6_TAG)==null){
                if(f6==null){
                    f6 = new LoginRequestFragment().newInstance("Contents");
                }
                if(getChildFragmentManager().findFragmentByTag(F5_TAG)==null){
                    //콘텐츠페이지가 없을때
                    ft.add(R.id.fragment_parent_contents, f6, F6_TAG);
                }else{
                    //콘텐츠페이지가 전에 떠있을때
                    ft.detach(getChildFragmentManager().findFragmentByTag(F5_TAG));
                    ft.add(R.id.fragment_parent_contents, f6, F6_TAG);
                }
            }
            ft.commit();
        }
    }


}
