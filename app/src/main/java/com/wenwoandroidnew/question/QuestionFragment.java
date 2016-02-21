package com.wenwoandroidnew.question;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.wenwoandroidnew.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {
    FragmentTabHost tabHost;
    public QuestionFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_question, container, false);
        Bundle b = getArguments();
        tabHost = (FragmentTabHost)view.findViewById(R.id.tabHost);
        tabHost.setup(getActivity(), getChildFragmentManager(), R.id.question_content);
        Bundle myBundle = new Bundle();
            if(b!=null){
                String status = b.getString("Status");
                String title = b.getString("title");
                String context = b.getString("context");
                Log.d("questionfragment",status+title+context);
                if(status.equals("0")) {
                myBundle.putString("Status", status);
                myBundle.putString("title",title);
                myBundle.putString("context",context);
                tabHost.addTab(tabHost.newTabSpec("tab_text").setIndicator(null, getResources().getDrawable(R.drawable.tab_question_text)), TextQuestionFragment.class, myBundle);
                tabHost.addTab(tabHost.newTabSpec("tab_picture").setIndicator(null, getResources().getDrawable(R.drawable.tab_question_picture)), PictureQuestionFragment.class, null);
                tabHost.addTab(tabHost.newTabSpec("tab_voice").setIndicator(null, getResources().getDrawable(R.drawable.tab_question_voice)), VoiceQuestionFragment.class, null);
                }else if(status.equals("1")) {
                myBundle.putString("Status", status);
                myBundle.putString("title",title);
                myBundle.putString("context",context);
                tabHost.addTab(tabHost.newTabSpec("tab_text").setIndicator(null, getResources().getDrawable(R.drawable.tab_question_text)), TextQuestionFragment.class, null);
                tabHost.addTab(tabHost.newTabSpec("tab_picture").setIndicator(null, getResources().getDrawable(R.drawable.tab_question_picture)), PictureQuestionFragment.class, myBundle);
                tabHost.addTab(tabHost.newTabSpec("tab_voice").setIndicator(null, getResources().getDrawable(R.drawable.tab_question_voice)), VoiceQuestionFragment.class, null);
                }else if(status.equals("2")){
                myBundle.putString("Status", status);
                myBundle.putString("title",title);
                myBundle.putString("context",context);
                tabHost.addTab(tabHost.newTabSpec("tab_text").setIndicator(null, getResources().getDrawable(R.drawable.tab_question_text)), TextQuestionFragment.class, null);
                tabHost.addTab(tabHost.newTabSpec("tab_picture").setIndicator(null, getResources().getDrawable(R.drawable.tab_question_picture)), PictureQuestionFragment.class, null);
                tabHost.addTab(tabHost.newTabSpec("tab_voice").setIndicator(null, getResources().getDrawable(R.drawable.tab_question_voice)), VoiceQuestionFragment.class, myBundle);
                }
            }else{
                tabHost.addTab(tabHost.newTabSpec("tab_text").setIndicator(null, getResources().getDrawable(R.drawable.tab_question_text)), TextQuestionFragment.class, null);
                tabHost.addTab(tabHost.newTabSpec("tab_picture").setIndicator(null, getResources().getDrawable(R.drawable.tab_question_picture)), PictureQuestionFragment.class, null);
                tabHost.addTab(tabHost.newTabSpec("tab_voice").setIndicator(null, getResources().getDrawable(R.drawable.tab_question_voice)), VoiceQuestionFragment.class, null);
            }
            setTabColor(tabHost);
        if(b!=null) {
            String a = b.getString("Status");
            if (a.equals("0")) {
                tabHost.setCurrentTab(0);
            } else if (a.equals("1")) {
                tabHost.setCurrentTab(1);
            } else if (a.equals("2")) {
                tabHost.setCurrentTab(2);
            }
        }

        return view;
    }

    int childRequestCode = -1;
    public void callMeActivity(Intent intent , int requestCode) {
        childRequestCode = requestCode;

        ((ParentQuestionFragment)getParentFragment()).callMeActivity( intent , requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Fragment f = null;
        if (requestCode == PictureQuestionFragment.REQUEST_PICTURE_ACTIVITY) {
            f = getChildFragmentManager().findFragmentByTag("tab_picture");
        }

        else if (requestCode == VoiceQuestionFragment.REQUEST_RECORED_ACTIVITY) {
            f = getChildFragmentManager().findFragmentByTag("tab_voice");
        }

        if (f != null) {
            f.onActivityResult(requestCode,resultCode, data);
        }
    }

    public static void setTabColor(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#EBEBEB")); //unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#EBEBEB")); // selected
    }
}
