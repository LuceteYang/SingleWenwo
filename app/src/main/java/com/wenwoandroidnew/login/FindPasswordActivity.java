package com.wenwoandroidnew.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.join.JoinChoiceActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindPasswordActivity extends AppCompatActivity {
    @Bind(R.id.btn_change_password)
    ImageView changepassword;
    @Bind(R.id.edit_password_email)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        ButterKnife.bind(this);
    }

    public void onChangePassword( View view){
        editText.setError(null);

        // Store values at the time of the login attempt.
        String email = editText.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            editText.setError(getString(R.string.error_field_required));
            focusView = editText;
            cancel = true;
        } else if (!isEmailValid(email)) {
            editText.setError(getString(R.string.error_invalid_email));
            focusView = editText;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            Toast.makeText(this,editText.getText().toString(),Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    // 이메일 회원가입 클릭 이벤트
    public void onSignupEmailFind(View view){
        startActivity(new Intent(FindPasswordActivity.this, JoinChoiceActivity.class));
        finish();
    }

    public void OnFinishPassword(View view){
        finish();
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

}
