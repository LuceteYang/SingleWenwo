package com.wenwoandroidnew.question;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wenwoandroidnew.HomeActivity;
import com.wenwoandroidnew.LoginRequestFragment;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.contents.ContentsFragment;
import com.wenwoandroidnew.system.AppGlobalSetting;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParentQuestionFragment extends Fragment{


    private static final String F3_TAG = "f3";
    private static final String F4_TAG = "f4";
    ActionBar actionBar;

    public ParentQuestionFragment() {
        // Required empty public constructor
        this.setHasOptionsMenu(true);
    }
    Fragment f3,f4;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(AppGlobalSetting.isLocalLogin()) {
            inflater.inflate(R.menu.menu_question, menu);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parent_question, container, false);
        //로그인상황에 따른 페이지변환
        QuestionFragmentChange();

        //액션바설정
        TextView textView = new TextView(getActivity());
        textView.setText("Question");
        textView.setTextColor(getResources().getColor(R.color.colorWhite));
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(textView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);

        return view;
    }

    int childRequestCode = -1;
    public void callMeActivity(Intent intent , int requestCode) {
        childRequestCode = requestCode;
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == childRequestCode) {
            Fragment f = getChildFragmentManager().findFragmentByTag(F3_TAG);
            if (f != null) {
                f.onActivityResult(requestCode,resultCode, data);
            }
        }
    }

    private void  QuestionFragmentChange(){
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        if(AppGlobalSetting.isLocalLogin()){
            if(getChildFragmentManager().findFragmentByTag(F3_TAG)==null){
                if(f3==null){
                    f3 = new QuestionFragment();
                }
                if(getChildFragmentManager().findFragmentByTag(F4_TAG)==null){
                    //로그인요구페이지가 없을때
                    Bundle b = getArguments();
                    f3.setArguments(b);
                    ft.add(R.id.fragment_question_parent, f3, F3_TAG);
                }else{
                    //로그인요구페이지가 전에 떠있을때
                    ft.detach(getChildFragmentManager().findFragmentByTag(F4_TAG));
                    Bundle b = getArguments();
                    f3.setArguments(b);
                    ft.add(R.id.fragment_question_parent, f3, F3_TAG);
                }
            }
            ft.commit();
        }else{
            if(getChildFragmentManager().findFragmentByTag(F4_TAG)==null){
                if(f4==null){
                    f4 = new LoginRequestFragment().newInstance("Contents");
                }
                if(getChildFragmentManager().findFragmentByTag(F3_TAG)==null){
                    //퀘스쳔페이지가 없을때
                    ft.add(R.id.fragment_question_parent, f4, F4_TAG);
                }else{
                    //퀘스쳔페이지가 전에 떠있을때
                    ft.detach(getChildFragmentManager().findFragmentByTag(F3_TAG));
                    ft.add(R.id.fragment_question_parent, f4, F4_TAG);
                }
            }
            ft.commit();
        }
    }


}
