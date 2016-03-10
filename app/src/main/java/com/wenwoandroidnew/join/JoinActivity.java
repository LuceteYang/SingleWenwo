package com.wenwoandroidnew.join;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wenwoandroidnew.HomeActivity;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.model.query.ModelJoinQuery;
import com.wenwoandroidnew.system.model.query.ModelNickNameCheckQuery;
import com.wenwoandroidnew.system.module.ModuleUser;
import com.wenwoandroidnew.system.util.AppSetting;
import com.wenwoandroidnew.system.util.UtilUi;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class JoinActivity extends AppCompatActivity implements CallResult<Boolean> {

    @Bind(R.id.input_nickname)
    EditText input_nickname;
    @Bind(R.id.join_edit_password)
    EditText edit_password;
    @Bind(R.id.join_edit_password_one)
    EditText edit_password_one;

    @Bind(R.id.radio_group)
    RadioGroup group;

    @Bind(R.id.tv_birthday_day)
    TextView tv_birthday_day;

    @Bind(R.id.tv_birthday_month)
    TextView tv_birthday_month;

    @Bind(R.id.tv_birthday_year)
    TextView tv_birthday_year;

    @Bind(R.id.iv_join_profile)
    ImageView ivJoinProfileImage;

    String checktext = "man";

    Boolean dateChange = false;
    private Dialog dialog;
    int callResultType = 0;
    private String profileImagePatah = "";

    Boolean jungbok = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        ButterKnife.bind(this);

        Intent intent = getIntent();

        final String mantype=intent.getStringExtra("mantype");
        final String email= intent.getStringExtra("email");

        Button btn_nickname_check = (Button)findViewById(R.id.btn_nickname_check);
        btn_nickname_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = UtilUi.showWaitDialog(JoinActivity.this, "중복확인 처리중..."); // 다이아로그 띄우기
                ModelNickNameCheckQuery a = new ModelNickNameCheckQuery();
                String nickname = input_nickname.getText().toString();
                a.nickname = nickname;
                callResultType = 0;
                checkNickname(a);
            }
        });

        //닉네임 중복체크 후에도 수정시 다시 중복체크를 하게 처리
        input_nickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                jungbok = false;
            }
        });

//            Toast.makeText(JoinActivity.this, mantype + "가입, email : " + email, Toast.LENGTH_SHORT).show();

            group.check(R.id.radio_man);
            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()

            {
                @Override
                public void onCheckedChanged (RadioGroup group,int checkedId){
                switch (checkedId) {
                    case R.id.radio_man:
                        checktext = "man";
                        break;
                    case R.id.radio_woman:
                        checktext = "woman";
                        break;
                }
                Toast.makeText(JoinActivity.this, checktext, Toast.LENGTH_SHORT).show();
            }
            }

            );
            ImageView btn = (ImageView) findViewById(R.id.btn_sign_up);
            btn.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                // Reset errors.
                input_nickname.setError(null);
                edit_password.setError(null);
                edit_password_one.setError(null);
                // Store values at the time of the login attempt.
                String nickname = input_nickname.getText().toString();
                String password = edit_password.getText().toString();
                String password1 = edit_password_one.getText().toString();
                StringBuilder strBldr = new StringBuilder();
                strBldr.append(tv_birthday_year.getText().toString());
                strBldr.append("-");
                strBldr.append(tv_birthday_month.getText().toString());
                strBldr.append("-");
                strBldr.append(tv_birthday_day.getText().toString());
                String birthday = strBldr.toString();
                boolean cancel = false;
                View focusView = null;

                //검증하는거 밖에 빼기
                // Check for a valid password, if the user entered one.
                if (TextUtils.isEmpty(password) && !isPasswordValid(password)) {
                    edit_password.setError(getString(R.string.error_invalid_password));
                    focusView = edit_password;
                    cancel = true;
                }

                if (TextUtils.isEmpty(password1) && !isPasswordValid(password1)) {
                    edit_password_one.setError(getString(R.string.error_invalid_password));
                    focusView = edit_password_one;
                    cancel = true;
                }

                if (!password.equals(password1)) {
                    focusView = edit_password;
                    Toast.makeText(getApplicationContext(), "password is not equal", Toast.LENGTH_SHORT).show();
                    cancel = true;
                }

                if (!dateChange) {
                    cancel = true;
                    Toast.makeText(getApplicationContext(), "date need to be change", Toast.LENGTH_SHORT).show();
                    focusView = input_nickname;
                }

                if (!jungbok) {
                    cancel = true;
                    Toast.makeText(getApplicationContext(), "중복체크를 하지 않았습니다", Toast.LENGTH_SHORT).show();
                    focusView = input_nickname;
                }

                // Check for a valid email address.
                if (TextUtils.isEmpty(nickname)) {
                    input_nickname.setError(getString(R.string.error_field_required));
                    focusView = input_nickname;
                    cancel = true;
                }
                if (cancel) {
                    focusView.requestFocus();
                } else {
                    dialog = UtilUi.showWaitDialog(JoinActivity.this, "회원가입 처리중..."); // 다이아로그 띄우기
                    // 회원가입 정보 생성
                    ModelJoinQuery modeljoinquery = new ModelJoinQuery();
                    modeljoinquery.setQemail(email);
                    modeljoinquery.setBirthDate(birthday);
                    modeljoinquery.setNickname(nickname);
                    modeljoinquery.setProfileImage(profileImagePatah);
                    modeljoinquery.setPw(password);
                    modeljoinquery.setSex(checktext);
                    modeljoinquery.setType(mantype);

                    // 가입처리 호출
                    joinRun(modeljoinquery);
                }
            }
            }

            );

        }

    private  void checkNickname(ModelNickNameCheckQuery a){
        ModuleUser.nicknamecheck(this, a);
    }

    private void joinRun(ModelJoinQuery ModelJoinQuery){
        callResultType = 1;
        ModuleUser.join(this, ModelJoinQuery);
    }

    @Override
    public void callResult(Boolean aBoolean) {
        UtilUi.hideWaitDialog(dialog);
        String message = "";
        if( callResultType == 0){
            if( aBoolean.booleanValue() == Boolean.TRUE){
                message = "중복아이디 없음";
                jungbok = true;
            }
            else{
                message = "중복아이디 있음";
            }
        }
        else if( callResultType == 1){
            if( aBoolean.booleanValue() == Boolean.TRUE){

                //회원가입 성공후 홈화면으로 이동
                Intent intent = new Intent(JoinActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
            else{
                message = "회원가입 실패";
            }
        }

        Toast.makeText(JoinActivity.this, message, Toast.LENGTH_SHORT).show();
    }


    public void onSetBirthday(View view){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "Date Picker");
        dateChange = true;
    }

    private static int REQUEST_IMAGE = 0;
    //프로필사진 설정

    public void onSetProfile(View view){

        // 이미지 선택 라이브러리 호출(MultiImageSelectorActivity(
        Intent intent = new Intent( JoinActivity.this, MultiImageSelectorActivity.class);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true); // 카메라 버튼 보이기 여부
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1); // 선택이미지 개수
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE); // 이미지선택이 싱글인지 멀티인지 선택
        startActivityForResult(intent, REQUEST_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                profileImagePatah = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT).get(0);
                ivJoinProfileImage.setImageBitmap(BitmapFactory.decodeFile(profileImagePatah));// 프로필 이미지 세팅
            }
        }
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @OnClick(R.id.iv_join_choice) void onClose(){
        finish();
    }
}
