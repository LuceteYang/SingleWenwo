package com.wenwoandroidnew.newsfeed.myfeed;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.newsfeed.NewsFeedFragment;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.model.ModelQuestionList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFeedFragment extends Fragment {

    FragmentTabHost tabHost;

    public MyFeedFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_feed, container, false);
        tabHost = (FragmentTabHost)view.findViewById(R.id.tabHost);
        tabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent_myfeed);
        tabHost.addTab(tabHost.newTabSpec("tab_my_feed_all").setIndicator(null, getResources().getDrawable(R.drawable.tab_myfeed_all)), MyFeedAllFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab_my_feed_wait").setIndicator(null, getResources().getDrawable(R.drawable.tab_myfeed_wait)), MyFeedWaitFragment.class, null);//대기1
        tabHost.addTab(tabHost.newTabSpec("tab_my_feed_yet").setIndicator(null, getResources().getDrawable(R.drawable.tab_myfeed_yet)), MyFeedYetFragment.class, null);    //시효2
        tabHost.addTab(tabHost.newTabSpec("tab_my_feed_accept").setIndicator(null, getResources().getDrawable(R.drawable.tab_myfeed_accept)), MyFeedAcceptFragment.class, null);//채택0
        setTabColor(tabHost);
        return view;
    }

    public static void setTabColor(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFFFFF")); //unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFFFF")); // selected
    }


}
