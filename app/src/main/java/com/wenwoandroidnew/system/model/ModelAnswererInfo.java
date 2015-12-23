package com.wenwoandroidnew.system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wenwoandroidnew.system.common.ModelBase;

import java.util.List;

/**
 * Created by User on 2015-11-16.
 */
public class ModelAnswererInfo implements ModelBase {
    @SerializedName("data")
    @Expose
    Data data;

    public ModelAnswererInfo.Data getData() {
        return data;
    }

    public void setData(ModelAnswererInfo.Data data) {
        this.data = data;
    }



    public class Data{
        @SerializedName("aemail")
        @Expose
        String aemail;

        @SerializedName("nickname")
        @Expose
        String nickname;

        @SerializedName("profileImage")
        @Expose
        List<ImageList> profileImage;

        @SerializedName("seedHistory")
        @Expose
        int seedHistory;

        @SerializedName("answerRate")
        @Expose
        int answerRate;

        public String getAemail() {

            return aemail;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "aemail='" + aemail + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", profileImage=" + profileImage +
                    ", seedHistory=" + seedHistory +
                    ", answerRate=" + answerRate +
                    '}';
        }

        public List<ImageList> getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(List<ImageList> profileImage) {
            this.profileImage = profileImage;
        }

        public void setAemail(String aemail) {
            this.aemail = aemail;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getSeedHistory() {
            return seedHistory;
        }

        public void setSeedHistory(int seedHistory) {
            this.seedHistory = seedHistory;
        }

        public int getAnswerRate() {
            return answerRate;
        }

        public void setAnswerRate(int answerRate) {
            this.answerRate = answerRate;
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
