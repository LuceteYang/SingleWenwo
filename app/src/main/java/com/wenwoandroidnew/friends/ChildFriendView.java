package com.wenwoandroidnew.friends;

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
 * Created by ModelLoginQuery on 2015-11-04.
 */
public class ChildFriendView extends LinearLayout {
    ImageView FriendPicture, cbDeleteBox;
    TextView FriendName, FriendShare;
    LinearLayout GoDetail;

    boolean MeOrNot;
    boolean MeOrNotCanClick;



    ChildFriendItem ChildFiendInfo;


    public ChildFriendView(Context context) {
        super(context);
        init();
    }

    public ChildFriendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        inflate( getContext(), R.layout.view_friends_child, this);
        this.FriendPicture = (ImageView) findViewById( R.id.iv_profile_photo);
        this.FriendName = (TextView) findViewById( R.id.tv_friend_name);
        this.FriendShare = (TextView) findViewById( R.id.tv_friend_share);
        this.cbDeleteBox = (ImageView) findViewById( R.id.icon_delete);
        this.GoDetail = (LinearLayout)findViewById(R.id.onGoFriendDetail);
    }

    // 삭제 체크박스 보이기 안보이기 세팅
    // true 이면 보이고 false 이면 안보임
    public void setDeleteCheckBox( boolean bCheck){
        if( bCheck == true){
            this.cbDeleteBox.setVisibility(VISIBLE);
        }
        else{
            this.cbDeleteBox.setVisibility(GONE);
        }
    }

    public void setChildFiendInfo(ChildFriendItem childFiendInfo) {
        this.ChildFiendInfo = childFiendInfo;

        this.FriendName.setText(childFiendInfo.getFriendName());
        this.FriendShare.setText(childFiendInfo.getFriendShare());
        // 이미지가 있을 경우만 처리함
        if( childFiendInfo.getFriendPicture() != null && childFiendInfo.getFriendPicture().size()>0){
            try {
                // 이미지 뷰를 같이 넘겨서 그곳에 다운로드 받은 이미지 설정함
                UtilCommon.urlToDrawableProfileImage(childFiendInfo.getFriendPicture().get(0).getTh(), FriendPicture);
            } catch (IOException e) {
                Log.d("MagazineView : 에러" , e.toString());
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }else{
            if(childFiendInfo.MeOrNot){
                this.FriendPicture.setImageDrawable(AppGlobalSetting.myThProfileImage);
            }else{
                this.FriendPicture.setImageDrawable(getResources().getDrawable(R.drawable.profile_default));
            }
        }
        this.setDeleteCheckBox(childFiendInfo.bDeleteCheck);
        if(childFiendInfo.MeOrNot){
            this.GoDetail.setVisibility(INVISIBLE);
        }else{
            this.GoDetail.setVisibility(VISIBLE);
        }
        if(childFiendInfo.MeOrNotCanClick){
            this.GoDetail.setClickable(true);
        }else{
            this.GoDetail.setClickable(false);
        }
    }

    boolean isChecked = false;



    public ChildFriendItem getChildFiendInfo() {
        return ChildFiendInfo;
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

    public ImageView getCbDeleteBox() {
        return cbDeleteBox;
    }

    public void setCbDeleteBox(ImageView cbDeleteBox) {
        this.cbDeleteBox = cbDeleteBox;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
