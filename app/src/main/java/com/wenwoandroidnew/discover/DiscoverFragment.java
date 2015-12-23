package com.wenwoandroidnew.discover;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.answerer.AnswerActivity;
import com.wenwoandroidnew.system.module.ModuleUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment  {

    ActionBar actionBar;

    public DiscoverFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_discover, container, false);


        view.findViewById(R.id.btn_qr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getContext(), QRCodeActivity.class));
            }
        });

        view.findViewById(R.id.btn_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(),MapActivity.class);
                startActivity(a);
            }
        });
        view.findViewById(R.id.btn_exchange).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(),CurrencyActivity.class);
                startActivity(a);
            }
        });
        view.findViewById(R.id.btn_weather).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(),WeatherActivity.class);
                startActivity(a);
            }
        });

        view.findViewById(R.id.btn_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Setting Page", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.btn_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        view.findViewById(R.id.btn_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(),AnswerActivity.class);
                startActivity(a);
            }
        });



        return view;
    }

    private void logout(){
        ModuleUser.logout();
        Toast.makeText( getContext(), "로그아웃완료", Toast.LENGTH_SHORT).show();
    }

}
