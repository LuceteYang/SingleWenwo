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
import com.wenwoandroidnew.system.util.UtilCommon;

import java.io.IOException;

/**
 * Created by ModelLoginQuery on 2015-11-01.
 */
public class QuestionDetailView extends FrameLayout {
    ImageView iconView, questionImage1, questionImage2, modifyImage, profileView;
    RelativeLayout statusview;
    TextView questionCategory, questionVoteNumber, questionPName, questionTime, questionLocation, questionTitle, questionContext, questionAccept;
    QuestionItem questionInfo;
    RelativeLayout imageviewLayout;

    public QuestionDetailView(Context context) {
        super(context);
        init();
    }

    public QuestionDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init(){
        inflate( getContext(), R.layout.view_question_detail, this);
        this.iconView = (ImageView) findViewById( R.id.icon_question_detail);
        this.questionCategory = (TextView) findViewById( R.id.text_category_detail);
        this.questionVoteNumber = (TextView) findViewById( R.id.text_vote_number_detail);
        this.questionTitle = (TextView) findViewById( R.id.text_question_title_detail);
        this.questionPName = (TextView) findViewById( R.id.text_question_name_detail);
        this.questionTime = (TextView) findViewById( R.id.text_question_time_detail);
        this.questionLocation = (TextView) findViewById( R.id.text_question_location_detail);
        this.questionContext = (TextView) findViewById( R.id.text_question_context_detail);
        this.questionAccept = (TextView) findViewById( R.id.text_question_accept_detail);
        this.questionImage1 = (ImageView) findViewById(R.id.iv_question_detail1);
        this.questionImage2 = (ImageView) findViewById(R.id.iv_question_detail2);
        this.modifyImage = (ImageView)findViewById(R.id.iv_modify_question);
        this.statusview = (RelativeLayout)findViewById(R.id.relativeLayout);
        this.profileView = (ImageView)findViewById(R.id.imageView3_detail);
        this.imageviewLayout = (RelativeLayout)findViewById(R.id.imageviewLayout);
    }

    public void setQuestionInfo(QuestionItem questionInfo) {
        this.questionInfo = questionInfo;

        this.iconView.setImageDrawable(questionInfo.getQuestionIcon());
        this.questionCategory.setText(questionInfo.getQuestionCategory());
        this.questionVoteNumber.setText(questionInfo.getVoteNumber());
        this.questionPName.setText(questionInfo.getQuestionPName());
        this.questionTime.setText(questionInfo.getQuestionTime());
        this.questionLocation.setText(questionInfo.getQuestionLocation());
        this.questionTitle.setText(questionInfo.getQuestionTitle());
        this.questionContext.setText(questionInfo.getQuestionContext());
        this.questionAccept.setText(questionInfo.getQuestionAccept());


    if( questionInfo.getProfileList() != null && questionInfo.getProfileList().size()>0){
        try {
            // 이미지 뷰를 같이 넘겨서 그곳에 다운로드 받은 이미지 설정함
            UtilCommon.urlToDrawableProfileImage(questionInfo.getProfileList().get(0).getTh(), this.profileView);
        }  catch (IOException e) {
            Log.d("MagazineView : 에러", e.toString());
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }else{
        this.profileView.setImageDrawable(getResources().getDrawable(R.drawable.profile_default));
    }
if(questionInfo.getType().equals("1")){
    if( questionInfo.getQuestionImage1() != null && questionInfo.getQuestionImage1().size()>0){
        try {
            // 이미지 뷰를 같이 넘겨서 그곳에 다운로드 받은 이미지 설정함
            imageviewLayout.setVisibility(VISIBLE);
            questionImage1.setVisibility(VISIBLE);
            UtilCommon.urlToDrawableProfileImage(questionInfo.getQuestionImage1().get(0).getTh(), this.questionImage1);
        }  catch (IOException e) {
            Log.d("MagazineView : 에러" , e.toString());
            Toast.makeText( getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    if( questionInfo.getQuestionImage2() != null && questionInfo.getQuestionImage2().size()>0){
        try {
            // 이미지 뷰를 같이 넘겨서 그곳에 다운로드 받은 이미지 설정함
            imageviewLayout.setVisibility(VISIBLE);
            questionImage2.setVisibility(VISIBLE);
            UtilCommon.urlToDrawableProfileImage(questionInfo.getQuestionImage2().get(0).getTh(), this.questionImage2);
        }  catch (IOException e) {
            Log.d("MagazineView : 에러" , e.toString());
            Toast.makeText( getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
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

    public ImageView getIconView() {
        return iconView;
    }

    public ImageView getQuestionImage1() {
        return questionImage1;
    }

    public ImageView getQuestionImage2() {
        return questionImage2;
    }

    public ImageView getModifyImage() {
        return modifyImage;
    }

    public TextView getQuestionCategory() {
        return questionCategory;
    }

    public TextView getQuestionVoteNumber() {
        return questionVoteNumber;
    }

    public TextView getQuestionPName() {
        return questionPName;
    }

    public TextView getQuestionTime() {
        return questionTime;
    }

    public TextView getQuestionLocation() {
        return questionLocation;
    }

    public TextView getQuestionTitle() {
        return questionTitle;
    }

    public TextView getQuestionContext() {
        return questionContext;
    }

    public TextView getQuestionAccept() {
        return questionAccept;
    }

    public QuestionItem getQuestionInfo() {
        return questionInfo;
    }
}
