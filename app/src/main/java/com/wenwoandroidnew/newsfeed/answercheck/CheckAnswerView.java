package com.wenwoandroidnew.newsfeed.answercheck;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.newsfeed.answer.AnswerItem;
import com.wenwoandroidnew.system.util.UtilCommon;

import java.io.IOException;


/**
 * Created by ModelLoginQuery on 2015-11-03.
 */
public class CheckAnswerView extends LinearLayout{


    ImageView Answerimgch;
    TextView AnswerNamech, AnswerLevelch, AnswerAcceptch, AnswerContentch;
    AnswerItem AnswerInfo;

    public CheckAnswerView(Context context) {
        super(context);
        init();
    }

    public CheckAnswerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        inflate( getContext(), R.layout.view_check_answer, this);
        this.Answerimgch = (ImageView) findViewById( R.id.iv_answer_picture_ch);
        this.AnswerNamech = (TextView) findViewById( R.id.tv_answer_name_ch);
        this.AnswerLevelch = (TextView) findViewById( R.id.tv_answer_level_ch);
        this.AnswerAcceptch = (TextView) findViewById( R.id.tv_answer_accept_ch);
        this.AnswerContentch = (TextView) findViewById(R.id.tv_answer_content_ch);
    }
    public void setAnswerCheckInfo(AnswerItem AnswerInfo) {
        this.AnswerInfo = AnswerInfo;

        if( AnswerInfo.getProfileList() != null && AnswerInfo.getProfileList().size()>0){
            try {
                // 이미지 뷰를 같이 넘겨서 그곳에 다운로드 받은 이미지 설정함
                UtilCommon.urlToDrawableProfileImage(AnswerInfo.getProfileList().get(0).getTh(), this.Answerimgch);
            }  catch (IOException e) {
                Log.d("MagazineView : 에러", e.toString());
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }else{
            this.Answerimgch.setImageDrawable(getResources().getDrawable(R.drawable.default121));
        }

        this.AnswerNamech.setText(AnswerInfo.getAnswerName());
        this.AnswerLevelch.setText(AnswerInfo.getAnswerLevel());
        this.AnswerAcceptch.setText(AnswerInfo.getAnswerAccept());
        this.AnswerContentch.setText(AnswerInfo.getAnswerContent());
    }
    public ImageView getAnswerimgch() {
        return Answerimgch;
    }

    public void setAnswerimgch(ImageView answerimgch) {
        Answerimgch = answerimgch;
    }

    public TextView getAnswerNamech() {
        return AnswerNamech;
    }

    public void setAnswerNamech(TextView answerNamech) {
        AnswerNamech = answerNamech;
    }

    public TextView getAnswerLevelch() {
        return AnswerLevelch;
    }

    public void setAnswerLevelch(TextView answerLevelch) {
        AnswerLevelch = answerLevelch;
    }

    public TextView getAnswerAcceptch() {
        return AnswerAcceptch;
    }

    public void setAnswerAcceptch(TextView answerAcceptch) {
        AnswerAcceptch = answerAcceptch;
    }

    public TextView getAnswerContentch() {
        return AnswerContentch;
    }

    public void setAnswerContentch(TextView answerContentch) {
        AnswerContentch = answerContentch;
    }

    public AnswerItem getAnswerInfo() {
        return AnswerInfo;
    }

    public void setAnswerInfo(AnswerItem answerInfo) {
        AnswerInfo = answerInfo;
    }
}
