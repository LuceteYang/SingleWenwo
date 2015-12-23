package com.wenwoandroidnew.join;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.model.query.ModelemailQuery;
import com.wenwoandroidnew.system.module.ModuleMyInfo;
import com.wenwoandroidnew.system.module.ModuleUser;
import com.wenwoandroidnew.system.util.UtilUi;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CertificationEmailActivity extends AppCompatActivity implements CallResult<Boolean> {
    @Bind(R.id.btn_certify_email)
    ImageView btn_certify_email;

    @Bind(R.id.edit_certify_email)
    EditText edit_certify_email;
    Boolean jungbok = false;

    String PARMAM_INPUT_MESSAGE="mantype";
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification_email);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        btn_certify_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_certify_email.setError(null);

                // Store values at the time of the login attempt.
                String email = edit_certify_email.getText().toString();

                boolean cancel = false;
                View focusView = null;

                // Check for a valid email address.
                if (TextUtils.isEmpty(email)) {
                    edit_certify_email.setError(getString(R.string.error_field_required));
                    focusView = edit_certify_email;
                    cancel = true;
                } else if (!isEmailValid(email)) {
                    edit_certify_email.setError(getString(R.string.error_invalid_email));
                    focusView = edit_certify_email;
                    cancel = true;
                }
                if (cancel) {
                    focusView.requestFocus();
                } else {
                    dialog = UtilUi.showWaitDialog(CertificationEmailActivity.this, "이메일 조회중..."); // 다이아로그 띄우기
                    Intent intent = getIntent();
                    String mantype=intent.getStringExtra(PARMAM_INPUT_MESSAGE);
                    ModelemailQuery a = new ModelemailQuery(email);
                    Checkemailcheck(a);
                }
            }
        });
    }

    public void Checkemailcheck(ModelemailQuery a){
        ModuleUser.emailCheck(this,a);
    }

    public void onGoEmailLogin(View view){
        finish();
    }


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @Override
    public void callResult(Boolean aBoolean) {
        UtilUi.hideWaitDialog(dialog);
        String message = "";
        if( aBoolean.booleanValue() == Boolean.TRUE){
            message = "중복이메일 없음";
            jungbok = true;
            Intent i = new Intent(CertificationEmailActivity.this, JoinActivity.class);
            i.putExtra("email",edit_certify_email.getText().toString());
            i.putExtra("mantype","qemail");
            startActivity(i);
        }
        else{
            message = "중복아이디 있음";
        }
        Toast.makeText(CertificationEmailActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
