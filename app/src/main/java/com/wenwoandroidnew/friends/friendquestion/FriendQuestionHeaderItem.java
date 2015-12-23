package com.wenwoandroidnew.friends.friendquestion;

import android.graphics.drawable.Drawable;

import com.wenwoandroidnew.friends.MandFItem;
import com.wenwoandroidnew.system.module.ModelPicture;

import java.util.List;

/**
 * Created by ModelLoginQuery on 2015-11-04.
 */
public class FriendQuestionHeaderItem {


    List<ModelPicture> FriendPicture;
    String FriendName;
    String FriendShare;
    String Qemail;
    String Favorite;
    boolean like;

    public String getFavorite() {
        return Favorite;
    }

    public void setFavorite(String favorite) {
        Favorite = favorite;
    }


    public String getQemail() {
        return Qemail;
    }

    public void setQemail(String qemail) {
        Qemail = qemail;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public String getFriendShare() {
        return FriendShare;
    }

    public void setFriendShare(String friendShare) {
        FriendShare = friendShare;
    }

    public String getFriendName() {
        return FriendName;
    }

    public void setFriendName(String friendName) {
        FriendName = friendName;
    }

    public List<ModelPicture> getFriendPicture() {
        return FriendPicture;
    }

    public void setFriendPicture(List<ModelPicture> friendPicture) {
        FriendPicture = friendPicture;
    }
}
