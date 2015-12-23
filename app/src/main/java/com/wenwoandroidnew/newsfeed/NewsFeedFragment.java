package com.wenwoandroidnew.newsfeed;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.wenwoandroidnew.HomeActivity;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.friends.Friend1Activity;
import com.wenwoandroidnew.friends.FriendsActivity;
import com.wenwoandroidnew.newsfeed.allfeed.AllFeedFragment;
import com.wenwoandroidnew.newsfeed.myfeed.MyFeedFragment;
import com.wenwoandroidnew.system.AppGlobalSetting;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends Fragment {

    public static boolean isAllFeed = true;

    ArrayAdapter<String> mAdapter;
    ActionBar actionBar;
    ImageView friendicon;
    static int preposition = 0;
    private static final String F1_TAG = "f1";
    private static final String F2_TAG = "f2";

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_news_feed_friend:
                Intent intent = new Intent(getActivity(), Friend1Activity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    Fragment f1 = null;
    Fragment f2, current;

    public NewsFeedFragment() {
        // Required empty public constructor
        this.setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (AppGlobalSetting.isLocalLogin()) {
            inflater.inflate(R.menu.menu_news_feed, menu);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_feed, container, false);

        actionBar = ((HomeActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        if (AppGlobalSetting.isLocalLogin() == true) {
            Spinner spinner = new Spinner(this.getContext());
            actionBar.setCustomView(spinner, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
            mAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.view_feedspinner_text);
            mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(mAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (preposition != position) {
                        if (position == 0) {
                            preposition = 0;
                            f1 = new AllFeedFragment();
                            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                            ft.replace(R.id.news_feed_content, f1, F1_TAG);
                            ft.commit();
                        } else {
                            preposition = 1;
                            Fragment old = getChildFragmentManager().findFragmentByTag(F1_TAG);
                            f2 = new MyFeedFragment();
                            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                            if (old != null) {
                                ft.detach(old);
                            }
                            ft.replace(R.id.news_feed_content, f2, F2_TAG);
                            ft.commit();
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            initData();
        } else {
            TextView textView = new TextView(getActivity());
            textView.setText("All feed");
            textView.setTextColor(getResources().getColor(R.color.colorWhite));
            actionBar.setCustomView(textView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

        }

        if(f1==null){
            f1 = new AllFeedFragment();
        }

//        if (isAllFeed) {
//            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.news_feed_content, new AllFeedFragment());
//            ft.commit();
//        } else {
//            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.news_feed_content, new MyFeedFragment());
//            ft.commit();
//        }


        getChildFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
          FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.news_feed_content, f1, F1_TAG);
//        if(getChildFragmentManager().findFragmentByTag(F2_TAG)!=null){
//            ft.replace(R.id.news_feed_content, f2, F2_TAG);
//        }else {
//            ft.replace(R.id.news_feed_content, f1, F1_TAG);
//        }
        ft.commit();
        return view;
    }


    private void initData() {
        mAdapter.add("All feed");
        mAdapter.add("My feed");
    }

}
