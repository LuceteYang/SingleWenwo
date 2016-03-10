package com.wenwoandroidnew.newsfeed.answer;

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
 * Created by ModelLoginQuery on 2015-11-02.
 */
public class AnswerView extends FrameLayout {

    ImageView AnswerType, ImageView1, ImageView2, AnswerPersonPicture;
    TextView AnswerName, AnswerLevel, AnswerAccept, AnswerContent;
    AnswerItem answerItemInfo;
    RelativeLayout imageviewLayout;



    public AnswerView(Context context) {
        super(context);
        init();
    }

    public AnswerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        inflate( getContext(), R.layout.view_answer, this);
        this.AnswerType = (ImageView) findViewById( R.id.icon_answer);
        this.ImageView1 = (ImageView) findViewById( R.id.imageView8);
        this.ImageView2 = (ImageView) findViewById( R.id.imageView9);
        this.AnswerPersonPicture = (ImageView) findViewById( R.id.iv_answer_profile);
        this.AnswerName = (TextView) findViewById( R.id.tv_answer_name);
        this.AnswerLevel = (TextView) findViewById( R.id.tv_answer_level);
        this.AnswerAccept = (TextView) findViewById( R.id.text_answer_accept_percent);
        this.AnswerContent = (TextView) findViewById( R.id.tv_answer_content);
        this.imageviewLayout = (RelativeLayout)findViewById(R.id.answerImageView);
    }

    public void setAnswerInfo(AnswerItem answerItemInfo) {
        this.answerItemInfo = answerItemInfo;

        this.AnswerType.setImageDrawable(answerItemInfo.getAnswerType());
        this.AnswerName.setText(answerItemInfo.getAnswerName());
        this.AnswerLevel.setText(answerItemInfo.getAnswerLevel());
        this.AnswerAccept.setText(answerItemInfo.getAnswerAccept());
        this.AnswerContent.setText(answerItemInfo.getAnswerContent());

        if( answerItemInfo.getProfileList() != null && answerItemInfo.getProfileList().size()>0){
            try {
                // 이미지 뷰를 같이 넘겨서 그곳에 다운로드 받은 이미지 설정함
                UtilCommon.urlToDrawableProfileImage(answerItemInfo.getProfileList().get(0).getTh(), this.AnswerPersonPicture);
//                this.AnswerPersonPicture.setTransitionName(answerItemInfo.getProfileList().get(0).getTh());
            }  catch (IOException e) {
                Log.d("MagazineView : 에러", e.toString());
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }else{
            this.AnswerPersonPicture.setImageDrawable(getResources().getDrawable(R.drawable.default121));
        }

        if(answerItemInfo.getType().equals("1")){
            if( answerItemInfo.getAnswerImage1() != null && answerItemInfo.getAnswerImage1().size()>0){
                try {
                    // 이미지 뷰를 같이 넘겨서 그곳에 다운로드 받은 이미지 설정함
                    imageviewLayout.setVisibility(VISIBLE);
                    ImageView1.setVisibility(VISIBLE);
                    UtilCommon.urlToDrawableProfileImage(answerItemInfo.getAnswerImage1().get(0).getTh(), this.ImageView1);
                }  catch (IOException e) {
                    Log.d("MagazineView : 에러" , e.toString());
                    Toast.makeText( getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            if( answerItemInfo.getAnswerImage2() != null && answerItemInfo.getAnswerImage2().size()>0){
                try {
                    // 이미지 뷰를 같이 넘겨서 그곳에 다운로드 받은 이미지 설정함
                    imageviewLayout.setVisibility(VISIBLE);
                    ImageView2.setVisibility(VISIBLE);
                    UtilCommon.urlToDrawableProfileImage(answerItemInfo.getAnswerImage2().get(0).getTh(), this.ImageView2);
                }  catch (IOException e) {
                    Log.d("MagazineView : 에러" , e.toString());
                    Toast.makeText( getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }



    }

    public ImageView getAnswerType() {
        return AnswerType;
    }

    public void setAnswerType(ImageView answerType) {
        AnswerType = answerType;
    }

    public ImageView getImageView1() {
        return ImageView1;
    }

    public void setImageView1(ImageView imageView1) {
        ImageView1 = imageView1;
    }

    public ImageView getImageView2() {
        return ImageView2;
    }

    public void setImageView2(ImageView imageView2) {
        ImageView2 = imageView2;
    }

    public ImageView getAnswerPersonPicture() {
        return AnswerPersonPicture;
    }

    public void setAnswerPersonPicture(ImageView answerPersonPicture) {
        AnswerPersonPicture = answerPersonPicture;
    }

    public TextView getAnswerName() {
        return AnswerName;
    }

    public void setAnswerName(TextView answerName) {
        AnswerName = answerName;
    }

    public TextView getAnswerLevel() {
        return AnswerLevel;
    }

    public void setAnswerLevel(TextView answerLevel) {
        AnswerLevel = answerLevel;
    }

    public TextView getAnswerAccept() {
        return AnswerAccept;
    }

    public void setAnswerAccept(TextView answerAccept) {
        AnswerAccept = answerAccept;
    }

    public TextView getAnswerContent() {
        return AnswerContent;
    }

    public void setAnswerContent(TextView answerContent) {
        AnswerContent = answerContent;
    }

    public AnswerItem getAnswerItemInfo() {
        return answerItemInfo;
    }

    public void setAnswerItemInfo(AnswerItem answerItemInfo) {
        this.answerItemInfo = answerItemInfo;
    }
}
