package com.wenwoandroidnew.system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wenwoandroidnew.system.common.ModelBase;

import java.util.List;

/**
 * Created by SeungJin on 2015-11-16.
 */
public class ModelUser  implements ModelBase {


    @SerializedName("qemail")@Expose
    String qemail;

    @SerializedName("profileImage")@Expose
    List<ImageList> profileImage;

    @SerializedName("nickname")@Expose
    String nickname;

    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQemail() {
        return qemail;
    }

    public void setQemail(String qemail) {
        this.qemail = qemail;
    }

    public List<ImageList> getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(List<ImageList> profileImage) {
        this.profileImage = profileImage;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public class ImageList {

        @SerializedName("originalPath") @Expose
        String originalPath;

        @SerializedName("th_path") @Expose
        String th_path;

        public String getOriginalPath() {
            return originalPath;
        }

        public void setOriginalPath(String originalPath) {
            this.originalPath = originalPath;
        }

        public String getTh_path() {
            return th_path;
        }

        public void setTh_path(String th_path) {
            this.th_path = th_path;
        }
    }


}
