package com.wenwoandroidnew.system.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.greenfrvr.rubberloader.RubberLoaderView;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.NullCallResult;
import com.wenwoandroidnew.system.model.query.ModelQuestionRegisterQuery;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by SeungJin on 2015-11-18.
 */
public class UtilUi {

    // 질문 타이틀, 내용 가져와서 만들기
    public static ModelQuestionRegisterQuery createQuestionRegister( View view, String type ){
        ModelQuestionRegisterQuery questionRegister = new ModelQuestionRegisterQuery();

        // 타이틀과 내용을 읽기
        questionRegister.setTitle(((EditText) (view.findViewById(R.id.et_question_title))).getText().toString());
        questionRegister.setText(((EditText) (view.findViewById(R.id.et_question_content))).getText().toString());

        //서버에 넘길 다른정보들도 여기에서 추가
        questionRegister.setQemail(AppGlobalSetting.getLocalLoginUser().getEmail());

        // 두개중 하나라도 입력이 안되었으면 에러로 처리
        if( UtilCommon.isEnableString( questionRegister.getTitle() , questionRegister.getText()) == false){
            questionRegister.setSuccess( false , "질문 타이틀 및 내용을 넣어주세요");
        }
        else{
            //둘다 입력되었을경우 성공처리
            questionRegister.setSuccess(true);
            questionRegister.setType(type);
        }

        return questionRegister;
    }

    // 대기 다이아로그
    public static Dialog showWaitDialog( Context context , String message){
        final Dialog _dialog = new Dialog( context);
        _dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        _dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        _dialog.setContentView(R.layout.wait_progress_dialog);
        _dialog.setCancelable(false);
        
        ((RubberLoaderView) (_dialog.findViewById( R.id.waiter_loader))).startLoading();
        TextView tv = (TextView) _dialog.findViewById( R.id.tv_waiter_loader);
        tv.setText(message);
        _dialog.show();

        _dialog.findViewById( R.id.btn_progress_canel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppGlobalSetting.callResult = new NullCallResult();
                _dialog.hide();
            }
        });
        return _dialog;
    }


    public static void hideWaitDialog( Dialog dialog){
        dialog.hide();
    }


}
