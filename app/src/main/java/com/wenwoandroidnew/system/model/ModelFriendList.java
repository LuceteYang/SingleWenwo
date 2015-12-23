package com.wenwoandroidnew.system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wenwoandroidnew.system.common.ModelBase;

import java.util.List;

/**
 * Created by SeungJin on 2015-11-16.
 */
public class ModelFriendList  implements ModelBase {

    @SerializedName("data")
    @Expose
    List<FriendData> data;

    public List<FriendData> getData() {
        return data;
    }

    public class FriendData {

        @SerializedName("nickname")@Expose
        String nickName;

        @SerializedName("qemail")@Expose
        String qemail;

        @SerializedName("profileImage")@Expose
        List<ImageList> profileImage;

        @SerializedName("shareQnQ")@Expose
        String shareQnQ;

        @SerializedName("favorite")@Expose
        String favorite;

        public List<ImageList> getImage() {
            return profileImage;
        }

        public void setImage(List<ImageList> image) {
            this.profileImage = image;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getQemail() {
            return qemail;
        }

        public void setQemail(String qemail) {
            this.qemail = qemail;
        }

        public void setShareQnQ(String shareQnQ) {
            this.shareQnQ = shareQnQ;
        }

        public String getFavorite() {
            return favorite;
        }

        public void setFavorite(String favorite) {
            this.favorite = favorite;
        }

        public String getNickName() {
            return nickName;
        }

//        public String getProfileImage() {
//            return profileImage;
//        }

        public String getShareQnQ() {
            return shareQnQ;
        }
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
