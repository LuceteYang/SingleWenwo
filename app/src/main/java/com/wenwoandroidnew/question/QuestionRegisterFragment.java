package com.wenwoandroidnew.question;

import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.wenwoandroidnew.HomeActivity;
import com.wenwoandroidnew.LocationAddress;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.discover.MyLocation;
import com.wenwoandroidnew.discover.WeatherActivity;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.common.CallResultOnemore;
import com.wenwoandroidnew.system.model.query.ModelGeocodingQuery;
import com.wenwoandroidnew.system.model.query.ModelQuestionRegisterQuery;
import com.wenwoandroidnew.system.module.ModuleGeocoding;
import com.wenwoandroidnew.system.module.ModuleQuestion;
import com.wenwoandroidnew.system.module.ModuleWeather;
import com.wenwoandroidnew.system.util.AppSetting;
import com.wenwoandroidnew.system.util.UtilUi;

import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.OnWheelScrollListener;
import antistatic.spinnerwheel.adapters.ArrayWheelAdapter;


public class QuestionRegisterFragment extends Fragment implements CallResult<Boolean>{
    ActionBar actionBar;
    TextView tvTime;
    TextView tvSeed;
    TextView tvDirectory;
    boolean timeclicked = false;
    boolean seedclicked = false;
    boolean directoryclicked = false;
    boolean timesetted = false;
    boolean seedsetted = false;
    boolean directorysetted = false;
    boolean open = false;
    boolean location = false;
    String time;
    String send;
    ScrollView scrollview;
    SwitchCompat nicknameSC;
    SwitchCompat locationSC;
    TextView recommendSeedView;
    int seed;
    int recommendSeed;
    String lat;
    String lon;

    public QuestionRegisterFragment() {
        // Required empty public constructor
        this.setHasOptionsMenu(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_question_next :
                if(!timesetted||!seedsetted||!directorysetted){
                    Toast.makeText(getActivity(),"정보입력이 다 되지 않았습니다",Toast.LENGTH_SHORT).show();
                    Log.i("ddd",Boolean.toString(timesetted)+Boolean.toString(seedsetted)+Boolean.toString(directorysetted));
                    return false;
                }
                dialog = UtilUi.showWaitDialog( getContext() , "질문내용 저장중..."); // 다이아로그 띄우기
                if(location==true) {
                    this.questionRegister.setMyloc("true");
                    MyLocation.LocationResult locationResult = new MyLocation.LocationResult() {
                        @Override
                        public void gotLocation(Location location) {
                            lat = Double.toString(location.getLatitude());
                            lon = Double.toString(location.getLongitude());
                        }
                    };
                    this.questionRegister.setLat(lat);
                    this.questionRegister.setLon(lon);
                }
                this.questionRegister.setSi("서울특별시");
                this.questionRegister.setGu("관악구");
                this.questionRegister.setDong("신림동");
                this.questionRegister.setCategory(tvDirectory.getText().toString());
                this.questionRegister.setSpentSeed(tvSeed.getText().toString());
                this.questionRegister.setOpen(Boolean.toString(open));
                this.questionRegister.setDueTile(time);
                ModuleQuestion.register(this, this.questionRegister);

                return true;
            case android.R.id.home:
                getParentFragment().getChildFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                getActivity().onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Dialog dialog;


    private ModelQuestionRegisterQuery questionRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_resister, container, false);
        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        tvTime = (TextView)view.findViewById(R.id.tv_register_time);
        tvDirectory = (TextView)view.findViewById(R.id.tv_register_directory);
        tvSeed = (TextView)view.findViewById(R.id.tv_register_seednum);
        recommendSeedView = (TextView)view.findViewById(R.id.tv_resister_recommend);
        scrollview = (ScrollView)view.findViewById(R.id.scrollView1);
        nicknameSC = (SwitchCompat)view.findViewById(R.id.nickname_open);
        nicknameSC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                open = isChecked;
                Toast.makeText(getActivity(), "showtext" + Boolean.toString(nicknameSC.isChecked()),Toast.LENGTH_SHORT).show();
            }
        });
        locationSC = (SwitchCompat)view.findViewById(R.id.location_open);
        locationSC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                location = isChecked;
                Toast.makeText(getActivity(), "showtext" + Boolean.toString(locationSC.isChecked()),Toast.LENGTH_SHORT).show();
            }
        });


        Bundle b = getArguments();

        questionRegister = (ModelQuestionRegisterQuery) b.getSerializable( "register");

        if(questionRegister.getType()==AppSetting.QUESTION_TYPE_TEXT){
            seed = 1;
        }else{
            seed = 2;
        }

        TextView textView = new TextView(getActivity());
        textView.setTextColor(getResources().getColor(R.color.colorWhite));

        // 타이틀 설정
        textView.setText(
                (questionRegister.getType().equals( AppSetting.QUESTION_TYPE_TEXT) ? AppSetting.QUESTION_TYPE_TEXT_TITLE :
                        (questionRegister.getType().equals( AppSetting.QUESTION_TYPE_PICTURE) ? AppSetting.QUESTION_TYPE_PICTURE_TITLE
                                : AppSetting.QUESTION_TYPE_VOICE_TITLE)));

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(textView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        actionBar.setDisplayShowTitleEnabled(false);

        final AbstractWheel ampm = (AbstractWheel)view.findViewById(R.id.ampm);
        final AbstractWheel seedchoice = (AbstractWheel)view.findViewById(R.id.sw_seedchoice);
        final AbstractWheel directorychoice = (AbstractWheel)view.findViewById(R.id.sw_directorychoice);

        //time setting
        view.findViewById(R.id.on_select_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timeclicked) {
                    ampm.setVisibility(View.GONE);
                    timeclicked = false;
                } else {
                    timeclicked = true;
                    ampm.setVisibility(View.VISIBLE);
                }
            }
        });
        final String[] timearray =  new String[] {"Up to 10 minutes", "Up to 30 minutes", "Up to 1 hour","Up to 2 hours","Up to 4 hours","Up to 6 hours","Up to 8 hours","Up to 10 hours","Up to 24 hours"};
        final String[] timesend  = new String[] {"10", "30", "60","120","240","360","480","600","1440"};
        final Integer[] seedadd = new Integer[]{15,12,10,8,6,5,4,3,2};

        ArrayWheelAdapter<String> ampmAdapter = new ArrayWheelAdapter<String>(getActivity(),timearray);
        ampmAdapter.setItemResource(R.layout.wheel_text_centered);
        ampmAdapter.setItemTextResource(R.id.text131);
        ampm.setViewAdapter(ampmAdapter);
        ampm.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(AbstractWheel wheel) {

            }

            @Override
            public void onScrollingFinished(AbstractWheel wheel) {
                tvTime.setText(timearray[ampm.getCurrentItem()]);
                time=timesend[ampm.getCurrentItem()];
                timesetted=true;
                recommendSeed = seed+seedadd[ampm.getCurrentItem()];
                recommendSeedView.setText(Integer.toString(recommendSeed));
            }
        });

        //directory setting
        view.findViewById(R.id.on_select_directory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (directoryclicked) {
                    directorychoice.setVisibility(View.GONE);
                    directoryclicked = false;
                } else {
                    directoryclicked = true;
                    directorychoice.setVisibility(View.VISIBLE);
                    scrollview.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollview.scrollTo(0, scrollview.getBottom());
                        }
                    });
                }
            }
        });

        final String[] directoryarray =  new String[] {"Food", "Travel", "Traffic","Culture","Shopping"};
        ArrayWheelAdapter<String>directoryAdapter = new ArrayWheelAdapter<String>(getActivity(),directoryarray);
        directoryAdapter.setItemResource(R.layout.wheel_text_centered);
        directoryAdapter.setItemTextResource(R.id.text131);
        directorychoice.setViewAdapter(directoryAdapter);
        directorychoice.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(AbstractWheel wheel) {

            }

            @Override
            public void onScrollingFinished(AbstractWheel wheel) {
                tvDirectory.setText(directoryarray[directorychoice.getCurrentItem()]);
                directorysetted = true;
            }
        });

        //recommend seeds setting
        view.findViewById(R.id.on_select_seeds).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seedclicked) {
                    seedchoice.setVisibility(View.GONE);
                    seedclicked = false;
                } else {
                    seedclicked = true;
                    seedchoice.setVisibility(View.VISIBLE);
                    scrollview.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollview.scrollTo(0, scrollview.getBottom());
                        }
                    });
                }
            }
        });

        final String[] seedarray =  new String[] {"10 Seeds","11 Seeds","12 Seeds","13 Seeds", "14 Seeds", "15 Seeds","16 Seeds","17 Seeds"};
        final String[] seedsendarray =  new String[] {"10","11","12","13", "14", "15","16","17"};

        ArrayWheelAdapter<String>seedAdapter = new ArrayWheelAdapter<String>(getActivity(),seedarray);
        seedAdapter.setItemResource(R.layout.wheel_text_centered);
        seedAdapter.setItemTextResource(R.id.text131);
        seedchoice.setViewAdapter(seedAdapter);
        seedchoice.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(AbstractWheel wheel) {

            }

            @Override
            public void onScrollingFinished(AbstractWheel wheel) {
                tvSeed.setText(seedarray[seedchoice.getCurrentItem()]);
                send = seedsendarray[seedchoice.getCurrentItem()];
                seedsetted = true;
            }
        });

        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setHomeAsUpIndicator(R.drawable.q_complete_correct_64x64);

        return view;
    }


    @Override
    public void callResult(Boolean aBoolean) {

        UtilUi.hideWaitDialog( dialog);
        if( aBoolean == Boolean.TRUE){
            Toast.makeText( getActivity(), "성공", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            getActivity().finish();
        }
        else{
            Toast.makeText( getActivity(), "실패", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkInput(){

        return  true;
    }

}



