package com.wenwoandroidnew.system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wenwoandroidnew.system.common.ModelBase;

import java.util.List;

/**
 * Created by User on 2015-11-13.
 */
public class ModelAnswerList  implements ModelBase {

    @SerializedName("data")
    @Expose
    List<AnswerData> data;

    public List<AnswerData> getData() {
        return data;
    }

    public class AnswerData {

        @SerializedName("anum")
        @Expose
        int anum;

        @SerializedName("aemail")
        @Expose
        String aemail;

        @SerializedName("text")
        @Expose
        String text;

        @SerializedName("category")
        @Expose
        String category;

        @SerializedName("qnum")
        @Expose
        String qnum;

        @SerializedName("type")
        @Expose
        int type;

        @SerializedName("status")
        @Expose
        int status;

        @SerializedName("image")
        @Expose
        List<ImageList> image;

        @SerializedName("profileImage")
        @Expose
        List<ImageList> profileImage;

        @SerializedName("nickname")
        @Expose
        String nickname;

        public List<ImageList> getImage() {
            return image;
        }

        public void setImage(List<ImageList> image) {
            this.image = image;
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

        public int getAnum() {
            return anum;
        }

        public void setAnum(int anum) {
            this.anum = anum;
        }

        public String getAemail() {
            return aemail;
        }

        public void setAemail(String aemail) {
            this.aemail = aemail;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getQnum() {
            return qnum;
        }

        public void setQnum(String qnum) {
            this.qnum = qnum;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public class ImageList {

            @SerializedName("originalPath")
            @Expose
            String originalPath;

            @SerializedName("th_path")
            @Expose
            String th_path;

            public void setOriginalPath(String originalPath) {
                this.originalPath = originalPath;
            }

            public String getTh_path() {
                return th_path;
            }

            public void setTh_path(String th_path) {
                this.th_path = th_path;
            }

            public String getOriginalPath() {
                return originalPath;
            }
        }

    }

}
