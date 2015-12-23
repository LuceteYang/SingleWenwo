package com.wenwoandroidnew.friends.friendquestion;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.system.util.UtilCommon;

import java.io.IOException;

/**
 * Created by ModelLoginQuery on 2015-11-04.
 */
public class FriendQuestionHeaderView extends LinearLayout {
    ImageView FriendPicture, LikeView;
    TextView FriendName, FriendShare;
    FriendQuestionHeaderItem friendQuestionHeaderInfo;


    public FriendQuestionHeaderView(Context context) {
        super(context);
        init();
    }

    public FriendQuestionHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        inflate(getContext(), R.layout.view_friend_question_header, this);
        this.FriendPicture = (ImageView) findViewById( R.id.iv_friend_question_picture);
        this.FriendName = (TextView) findViewById( R.id.tv_friend_question_name);
        this.FriendShare = (TextView) findViewById( R.id.tv_friend_question_count);
        this.LikeView = (ImageView)findViewById(R.id.ic_friend_question);
        LikeView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShareListener != null) {
                    ShareListener.onShareClick(friendQuestionHeaderInfo.isLike());
                }
            }
        });
    }


    boolean isChecked = false;

    public void SetFriendQuestionHeaderInfo(FriendQuestionHeaderItem friendQuestionHeaderInfo){
        this.friendQuestionHeaderInfo = friendQuestionHeaderInfo;
        // 이미지가 있을 경우만 처리함
        if( friendQuestionHeaderInfo.getFriendPicture() != null && friendQuestionHeaderInfo.getFriendPicture().size()>0){
            try {
                // 이미지 뷰를 같이 넘겨서 그곳에 다운로드 받은 이미지 설정함
                UtilCommon.urlToDrawableProfileImage(friendQuestionHeaderInfo.getFriendPicture().get(0).getTh(), FriendPicture);
            } catch (IOException e) {
                Log.d("MagazineView : 에러", e.toString());
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }else {
            this.FriendPicture.setImageDrawable(getResources().getDrawable(R.drawable.default121));
        }

        this.FriendShare.setText(friendQuestionHeaderInfo.getFriendShare());
        this.FriendName.setText(friendQuestionHeaderInfo.getFriendName());
        if(friendQuestionHeaderInfo.isLike()){
            this.LikeView.setImageDrawable(getResources().getDrawable(R.drawable.friends_favorite_onclick_40x48));
        }else{
            this.LikeView.setImageDrawable(getResources().getDrawable(R.drawable.friends_favorite_40x48));
        }
    }


    public ImageView getFriendPicture() {
        return FriendPicture;
    }

    public void setFriendPicture(ImageView friendPicture) {
        FriendPicture = friendPicture;
    }

    public TextView getFriendName() {
        return FriendName;
    }

    public void setFriendName(TextView friendName) {
        FriendName = friendName;
    }

    public TextView getFriendShare() {
        return FriendShare;
    }

    public void setFriendShare(TextView friendShare) {
        FriendShare = friendShare;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    FriendShareListener ShareListener;
    public void setOnShareListener(FriendShareListener listener) {
        ShareListener = listener;
    }
}
