package com.wenwoandroidnew.newsfeed;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.util.UtilCommon;

import java.io.IOException;

/**
 * Created by ModelLoginQuery on 2015-11-01.
 */
public class QuestionView extends FrameLayout {
    ImageView iconView, profileView;
    RelativeLayout statusview;
    TextView questionCategory, questionVoteNumber, questionPName, questionTime, questionLocation, questionTitle, questionContext, questionAccept;
    QuestionItem questionInfo;

    public QuestionView(Context context) {
        super(context);
        init();
    }

    public QuestionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init(){
        inflate( getContext(), R.layout.view_question, this);
        this.iconView = (ImageView) findViewById( R.id.icon_question);
        this.questionCategory = (TextView) findViewById( R.id.text_category);
        this.questionVoteNumber = (TextView) findViewById( R.id.text_vote_number);
        this.questionTitle = (TextView) findViewById( R.id.text_question_title);
        this.questionPName = (TextView) findViewById( R.id.text_question_name);
        this.questionTime = (TextView) findViewById( R.id.text_question_time);
        this.questionLocation = (TextView) findViewById( R.id.text_question_location);
        this.questionContext = (TextView) findViewById( R.id.text_question_context);
        this.questionAccept = (TextView) findViewById( R.id.text_question_accept);
        this.statusview = (RelativeLayout)findViewById(R.id.status_color);
        this.profileView = (ImageView)findViewById(R.id.iv_view_question_profile);
    }

    public void setQuestionInfo(QuestionItem questionInfo) {
        this.questionInfo = questionInfo;

        this.iconView.setImageDrawable(questionInfo.getQuestionIcon());
        this.questionCategory.setText(questionInfo.getQuestionCategory());
        this.questionVoteNumber.setText(questionInfo.getVoteNumber());
        this.questionPName.setText(questionInfo.getQuestionPName());
        this.questionTime.setText(Integer.toString(questionInfo.getPick()));
        this.questionLocation.setText(questionInfo.getQuestionLocation());
        this.questionTitle.setText(questionInfo.getQuestionTitle());
        this.questionContext.setText(questionInfo.getQuestionContext());

        if( questionInfo.getProfileList() != null && questionInfo.getProfileList().size()>0){
            try {
        // 이미지 뷰를 같이 넘겨서 그곳에 다운로드 받은 이미지 설정함
        UtilCommon.urlToDrawableProfileImage(questionInfo.getProfileList().get(0).getTh(), this.profileView);
           }  catch (IOException e) {
                Log.d("MagazineView : 에러" , e.getMessage());
                Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else{
            if(questionInfo.isAllfeed()==false){
                this.profileView.setImageDrawable(AppGlobalSetting.myThProfileImage);
            }else{
                this.profileView.setImageDrawable(getResources().getDrawable(R.drawable.profile_default));
            }
        }



        int status = Integer.parseInt(questionInfo.getStatus());
        if(status==0){//채택
            statusview.setBackgroundColor(getResources().getColor(R.color.status_2));
            this.questionAccept.setText("채택완료된 답변이 있습니다.");
        }else if(status==1){//대기
            statusview.setBackgroundColor(getResources().getColor(R.color.status_0));
            this.questionAccept.setText("채택 완료된 답변이 0개 입니다.");
            // this.questionAccept.setText("채택 대기중인 \"+questionInfo.anumNumber+\"개의 답변이 있습니다.");
        }else if(status==2){//만료
            statusview.setBackgroundColor(getResources().getColor(R.color.status_1));
            this.questionAccept.setText("답변 시간이 지났습니다 다시 질문하시겠어요?");
        }
    }


}
