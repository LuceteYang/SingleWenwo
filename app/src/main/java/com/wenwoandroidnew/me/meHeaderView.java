package com.wenwoandroidnew.me;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.util.UtilCommon;

import java.io.IOException;

/**
 * Created by ModelLoginQuery on 2015-11-05.
 */
public class meHeaderView extends LinearLayout {
    ImageView MyProfile;
    TextView myNmae, myQuestion, questionNmuber;
    meHeaderItem meHeaderInfo;
    public meHeaderView(Context context) {
        super(context);
        init();
    }

    public meHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        inflate( getContext(), R.layout.view_me_header, this);
        this.MyProfile = (ImageView) findViewById( R.id.iv_me_header_profile);
        this.myNmae = (TextView) findViewById( R.id.tv_me_header_name);
        this.myQuestion = (TextView) findViewById( R.id.tv_me_my_question);
        this.questionNmuber = (TextView) findViewById( R.id.tv_me_question_number);
    }
    public void setMeHeaderInfo(meHeaderItem meHeaderInfo) {
        this.meHeaderInfo = meHeaderInfo;

        this.MyProfile.setImageDrawable(AppGlobalSetting.myThProfileImage);
        this.myNmae.setText(meHeaderInfo.getMyName());
        this.myQuestion.setText(meHeaderInfo.getMyQuestion());
        this.questionNmuber.setText(meHeaderInfo.getQuestionNumber());

    }
}
