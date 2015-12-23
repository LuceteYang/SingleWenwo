package com.wenwoandroidnew;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.wenwoandroidnew.login.EmailLoginActivity;
import com.wenwoandroidnew.login.WechatLoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginRequestFragment extends Fragment {

    ActionBar actionBar;
    public LoginRequestFragment() {
        // Required empty public constructor
        this.setHasOptionsMenu(false);
    }

    public static LoginRequestFragment newInstance(String name) {
        LoginRequestFragment fragment = new LoginRequestFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_request, container, false);
        ButterKnife.bind(this, view);
        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);



        return view;
    }





}
