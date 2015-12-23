package com.wenwoandroidnew.newsfeed.allfeed;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wenwoandroidnew.system.manager.PropertyManager;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.newsfeed.FeedAdapter;
import com.wenwoandroidnew.newsfeed.QuestionItem;
import com.wenwoandroidnew.newsfeed.answer.AnswerListFragment;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.model.ModelQuestionList;
import com.wenwoandroidnew.system.model.query.ModelQuestionQuery;
import com.wenwoandroidnew.system.module.ModelPicture;
import com.wenwoandroidnew.system.module.ModuleQuestion;
import com.wenwoandroidnew.system.util.AppSetting;
import com.wenwoandroidnew.system.util.UtilUi;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class AllFeedFragment extends Fragment implements  CallResult<ModelQuestionList>, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    FeedAdapter mAdapter;
//이게 데모용
    private PullToRefreshListView listView;

    GoogleApiClient mGoogleApiClient;
    // Request code to use when launching the resolution activity
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    // Unique tag for the error dialog fragment
    private static final String DIALOG_ERROR = "dialog_error";
    // Bool to track whether the app is already resolving an error
    private boolean mResolvingError = false;
    public static final int RESULT_OK = -1;
    private static final String STATE_RESOLVING_ERROR = "resolving_error";

    private Dialog dialog;

    public AllFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_feed, container, false);
        listView = (PullToRefreshListView) view.findViewById(R.id.all_freed_listView);
        mAdapter = new FeedAdapter();
        listView.setAdapter(mAdapter);
        Log.i("registrationToken", PropertyManager.getInstance().getRegistrationToken());
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    QuestionItem item = mAdapter.getItem(position-1);
                    if(item.getStatus().equals("0")){
                        AnswerListFragment dialog = new AnswerListFragment();
                        Bundle b = new Bundle();
                        b.putParcelable("question", item);
                        dialog.setArguments(b);
                        dialog.show(getActivity().getSupportFragmentManager(), "answer");
                    }else if(item.getStatus().equals("1")){
                        AnswerListFragment dialog = new AnswerListFragment();
                        Bundle b = new Bundle();
                        b.putParcelable("question", item);
                        dialog.setArguments(b);
                        dialog.show(getActivity().getSupportFragmentManager(), "answer");
                    }else if(item.getStatus().equals("2")){
                        Toast.makeText(getActivity(),"질문수정....준비중입니다",Toast.LENGTH_SHORT).show();
                    }
                    }
            });

        //처음 api설정 객체 생성
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        //스코프를 가지는 경우 addscope로 등록해준다.

        if (mGoogleApiClient.isConnected()) {   //구글 크라이언트와 연결되었는지 확인하고
            //Location Update를 위해 리퀘스트객체 생성
            LocationRequest request = LocationRequest.create();
            request.setInterval(100);     //시간설정
            request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);    //위치정보를 어떻게 수집하겠느냐
            request.setNumUpdates(1);       //한번만수신하겠어
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, request, this);
        }
        //리스너를 달아줍니다.


        ModelQuestionQuery query = new ModelQuestionQuery();
        query.call_type = AppSetting.FEED_CALL_TYPE.ALL; // 리스트 타입을 넣어줌
        query.isFirstStart = true;

        dialog = UtilUi.showWaitDialog(getContext() , "ALL Feed 조회중..."); // 다이아로그 띄우기
        ModuleQuestion.getQuestionList(this, query);

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ModelQuestionQuery query = new ModelQuestionQuery();
                query.call_type = AppSetting.FEED_CALL_TYPE.ALL; // 리스트 타입을 넣어줌
                query.isFirstStart = false;

                if (dialog != null) {
                    UtilUi.hideWaitDialog(dialog);
                }
                dialog = UtilUi.showWaitDialog(getContext(), "ALL Feed refleshing 조회중..."); // 다이아로그 띄우기
                ModuleQuestion.getQuestionList(AllFeedFragment.this, query);
            }
        });

        return view;
    }


    //requestLocation에 의해 위치정보 바뀌면 호출됨
    @Override
    public void onLocationChanged(Location location) {
//        messageView.setText(location.getLatitude() + "," + location.getLongitude());
    }

    //더이상 위치정보를 수신하지 않기위해서서
    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }


    @Override
    public void onConnected(Bundle bundle) {
        //LastNkownLocation을 획득한다.
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location != null) {

//            ModelQuestionQuery query = new ModelQuestionQuery();
//            query.a = String.valueOf(location.getLatitude());
//            query.b = String.valueOf(location.getLongitude());
//            query.call_type = AppSetting.FEED_CALL_TYPE.ALL; // 리스트 타입을 넣어줌
//
//            dialog = UtilUi.showWaitDialog(getContext() , "ALL Feed 조회중..."); // 다이아로그 띄우기
//
//            ModuleQuestion.getQuestionList(this, query);

        } else {
//            Toast.makeText(getActivity(), "No Location", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void callResult(ModelQuestionList modelQuestionList) {


        listView.setRefreshing(false);
        if (modelQuestionList.getData() == null) {
            Toast.makeText(getActivity(), "더이상 불러올 데이터가 없습니다!", Toast.LENGTH_SHORT).show();
            UtilUi.hideWaitDialog(dialog);
            return;
        }
        for (int i = 0; i < modelQuestionList.getData().size(); i++) {

            ModelQuestionList.ModelData mq = modelQuestionList.getData().get(i);
            QuestionItem d = new QuestionItem(Parcel.obtain());
            Drawable questionIcon;
            d.questionCategory = mq.getCategory();//"Emergency";
            d.voteNumber = Integer.parseInt(mq.getSpentSeed());
            d.questionLocation = mq.getSi() + " " + mq.getDu() + " " + mq.getDong(); //"낙성대역 근처";
            d.questionPName = mq.getNickName();//"박상환";
            d.questionTitle = mq.getTitle();//"안녕하세요";
            d.questionAccept =Integer.toString(mq.getAnum().size());
            d.questionTime = mq.getDueTime();//"2015년 5월 8일";
            d.questionContext = mq.getText();//"낙성대역 근처 치킨집좀 알려주세용~~";
            d.status = Integer.toString(mq.getStatus());
            d.qnum = mq.getQnum();
            ModelPicture tempPicture = null;
            for ( int j=0 ; j < mq.getImage().size(); j++) {
                List<ModelPicture> imageList =  new ArrayList<>();
                tempPicture = new ModelPicture(
                        mq.getImage().get(j).getOriginalPath() ,
                        mq.getImage().get(j).getTh_path());
                imageList.add(tempPicture); // 썸네일 이미지로 리스트 보여주기
                if(j==0){
                    d.setQuestionImage1(imageList);
                }else{
                    d.setQuestionImage2(imageList);
                }
            }
            if(mq.getProfileImage()!=null) {
                for (int k = 0; k < mq.getProfileImage().size(); k++) {
                    List<ModelPicture> ProfileimageList = new ArrayList<>();
                    tempPicture = null;
                    tempPicture = new ModelPicture(
                            mq.getProfileImage().get(0).getOriginalPath(),
                            mq.getProfileImage().get(0).getTh_path());
                    ProfileimageList.add(tempPicture); // 썸네일 이미지로 리스트 보여주기
                    d.setProfileList(ProfileimageList);
                }
            }
            d.setType(Integer.toString(mq.getType()));
//            0:text, 1:image, 2:recording
                if (mq.getType() == 0) {
                    questionIcon = getResources().getDrawable(R.drawable.blank);
            } else if (mq.getType() == 1) {
                questionIcon = getResources().getDrawable(R.drawable.myfeed_img);
            } else {
                questionIcon = getResources().getDrawable(R.drawable.myfeed_voice);
            }

            d.questionIcon = questionIcon;

            mAdapter.add(d);
        }
        UtilUi.hideWaitDialog(dialog);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    //Error 발생시 ConnectionResult의 hasResolution()호출함
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (mResolvingError) {
            // Already attempting to resolve an error.
            return;
        } else if (result.hasResolution()) {
            try {
                mResolvingError = true;
                result.startResolutionForResult(getActivity(), REQUEST_RESOLVE_ERROR);
            } catch (IntentSender.SendIntentException e) {
                // There was an error with the resolution intent. Try again.
                mGoogleApiClient.connect();
            }
        } else {
            // Show dialog using GoogleApiAvailability.getErrorDialog()
            showErrorDialog(result.getErrorCode());
            mResolvingError = true;
        }
    }

    // The rest of this code is all about building the error dialog

    /* Creates a dialog for an error message */
    private void showErrorDialog(int errorCode) {
        // Create a fragment for the error dialog
        ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
        // Pass the error that should be displayed
        Bundle args = new Bundle();
        args.putInt(DIALOG_ERROR, errorCode);
        dialogFragment.setArguments(args);
        dialogFragment.show(getActivity().getSupportFragmentManager(), "errordialog");
    }

    /* Called from ErrorDialogFragment when the dialog is dismissed. */
    public void onDialogDismissed() {
        mResolvingError = false;
    }

    /* A fragment to display an error dialog */
    public static class ErrorDialogFragment extends DialogFragment {
        public ErrorDialogFragment() {
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Get the error code and retrieve the appropriate dialog
            int errorCode = this.getArguments().getInt(DIALOG_ERROR);
            return GoogleApiAvailability.getInstance().getErrorDialog(
                    this.getActivity(), errorCode, REQUEST_RESOLVE_ERROR);
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
//            ((MainActivity) getActivity()).onDialogDismissed();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_RESOLVE_ERROR) {
            mResolvingError = false;
            if (resultCode == RESULT_OK) {
                // Make sure the app is not already connected or attempting to connect
                if (!mGoogleApiClient.isConnecting() && !mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_RESOLVING_ERROR, mResolvingError);
    }

    @Override
    public void onStart() {      //구글서비스 코넥트
        super.onStart();
        if (!mResolvingError) {  // more about this later
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onStop() {       //구글서비스 끊음
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    //구글 GEOcoder메시지 핸들러
    public class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
//            Toast.makeText(getActivity(),locationAddress,Toast.LENGTH_LONG).show();
        }
    }
}
