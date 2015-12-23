package com.wenwoandroidnew.system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wenwoandroidnew.system.common.ModelBase;

/**
 * Created by SeungJin on 2015-11-16.
 */
public class ModelFriend  implements ModelBase {


    @SerializedName("nickname")@Expose
    String nickname;

    @SerializedName("shareQnQ")@Expose
    String shareQnQ;

//    @SerializedName("profileImage")@Expose
//    String profileImage;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getShareQnQ() {
        return shareQnQ;
    }

    public void setShareQnQ(String shareQnQ) {
        this.shareQnQ = shareQnQ;
    }
}
