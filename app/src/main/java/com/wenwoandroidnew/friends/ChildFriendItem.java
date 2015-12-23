package com.wenwoandroidnew.friends;

import android.graphics.drawable.Drawable;

import com.wenwoandroidnew.system.module.ModelPicture;

import java.util.List;

/**
 * Created by ModelLoginQuery on 2015-11-04.
 */
public class ChildFriendItem implements MandFItem {
    List<ModelPicture> FriendPicture;
    String FriendName;
    String FriendShare;
    String Qemail;
    String Favorite;
    boolean MeOrNot;
    boolean MeOrNotCanClick;

    public boolean isMeOrNot() {
        return MeOrNot;
    }

    public void setMeOrNot(boolean meOrNot) {
        MeOrNot = meOrNot;
    }

    public boolean isMeOrNotCanClick() {
        return MeOrNotCanClick;
    }

    public void setMeOrNotCanClick(boolean meOrNotCanClick) {
        MeOrNotCanClick = meOrNotCanClick;
    }

    boolean bDeleteCheck;

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

    public boolean isbDeleteCheck() {
        return bDeleteCheck;
    }

    public void setbDeleteCheck(boolean bDeleteCheck) {
        this.bDeleteCheck = bDeleteCheck;
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
