package com.wenwoandroidnew;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.zxing.WriterException;
import com.wenwoandroidnew.qrcode.QRCodeController;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.manager.DataManager;
import com.wenwoandroidnew.system.manager.PropertyManager;
import com.wenwoandroidnew.system.model.LocalLoginUser;
import com.wenwoandroidnew.system.model.ModelUser;
import com.wenwoandroidnew.system.model.query.ModelLoginQuery;
import com.wenwoandroidnew.system.module.ModuleUser;
import com.wenwoandroidnew.system.util.AppSetting;
import com.wenwoandroidnew.system.util.UtilCommon;

public class SplashActivity extends AppCompatActivity implements CallResult<ModelUser> {

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorBackSplash));
        }
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //startService();
            }
        };
        setUpIfNeeded();    //발급받는것 시작

        checkLoginStatus();
    }

    Handler mHandler = new Handler(Looper.getMainLooper());

    //로그인 여부 확인
    private void checkLoginStatus() {

        // 전역변수에 세팅
        AppGlobalSetting.context = this;

        // 로그인 상태 확인
        LocalLoginUser localLoginUser = ModuleUser.doLocalLoginStatus();
        if( AppSetting.LOG_TYPE == true) {
            Log.d("dd", Boolean.toString(AppGlobalSetting.isLocalLogin()));
        }
        // 로그인여부에 따른 처리
        if ( localLoginUser == null) {
//            Toast.makeText(SplashActivity.this, "로그인 안됨", Toast.LENGTH_SHORT).show();
            this.startHomeActivity();
        } else {
            if( AppSetting.LOG_TYPE == true) {
                Log.d("#########", localLoginUser.getEmail() + " , " + localLoginUser.getPassword() + " , " + PropertyManager.getInstance().getRegistrationToken());
            }
                ModuleUser.login(this,
                    new ModelLoginQuery(localLoginUser.getEmail(),
                            localLoginUser.getPassword() ,
                            PropertyManager.getInstance().getRegistrationToken()) );
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(RegistrationIntentService.REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLAY_SERVICES_RESOLUTION_REQUEST &&
                resultCode == Activity.RESULT_OK) {
            setUpIfNeeded();
        }
    }

    private void setUpIfNeeded() {
        if (checkPlayServices()) {  //구글플레이서비스를 사용할수있는지 환경 체크
            String regId = PropertyManager.getInstance().getRegistrationToken();
            if (!regId.equals("")) {    //레지스트레이션아이디있으면
                //startHomeActivity();      //서비스시작
            } else {
                Intent intent = new Intent(this, RegistrationIntentService.class);
                startService(intent);    //발급받은서비스구동
            }
        }
    }

    private void startHomeActivity(){
        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        finish();
    }
/*

    //스플레쉬 실제 코드 여기에다가 쓰면됨
    private void doRealStart() {
        Log.i("asda", PropertyManager.getInstance().getRegistrationToken());
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));

                finish();
            }
        }, 1000);
        this.checkLoginStatus();
    }
*/

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {    //구글플레이서비스 리코버리할수있는지확인
                Dialog dialog = apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST);//구글플레이서비스 업그리이드되면
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                });
                dialog.show();
            } else {
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public void callResult(ModelUser modelUser) {
        LocalLoginUser localLoginUser = new LocalLoginUser();
        localLoginUser.setEmail(modelUser.getQemail());
        localLoginUser.setName(modelUser.getNickname());
        localLoginUser.setLastLoginDate(UtilCommon.now());
        localLoginUser.setPassword(modelUser.getPassword());
        if(modelUser.getProfileImage().size()>0){
            localLoginUser.setThProfileImage(modelUser.getProfileImage().get(0).getTh_path());
            localLoginUser.setProfileImage(modelUser.getProfileImage().get(0).getOriginalPath());
        }

        AppGlobalSetting.myProfileImage = AppGlobalSetting.context.getResources().getDrawable(R.drawable.profile_default);
        AppGlobalSetting.myThProfileImage = AppGlobalSetting.context.getResources().getDrawable(R.drawable.profile_default);
        try {
            if((localLoginUser.getProfileImage()!=null
                    && (localLoginUser.getThProfileImage()!=null))) {
                UtilCommon.urlToDrawableProfileImage(localLoginUser.getProfileImage(), localLoginUser.getThProfileImage());
                //UtilCommon.urlToDrawableProfileImage(localLoginUser.getThProfileImage() , AppGlobalSetting.myThProfileImage);
            }
        } catch (Exception e) {
            Log.d("doLogin", e.toString());
        }

        DataManager.getInstance().insertLoginCheck(localLoginUser); // local DB에 사용자 정보 저장

        AppGlobalSetting.setLocalLoginUser(localLoginUser);

        // 사용자 QR 생성
        try {
            AppGlobalSetting.myEmailQRCode = new BitmapDrawable( new QRCodeController().createQRCode(modelUser.getQemail()));
        } catch (WriterException e) {
            Toast.makeText(SplashActivity.this, "사용자 QR코드 생성 오류", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


//        Toast.makeText(SplashActivity.this, "로그인완료", Toast.LENGTH_SHORT).show();
        this.startHomeActivity();
    }
}
