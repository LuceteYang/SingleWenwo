package com.wenwoandroidnew.system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wenwoandroidnew.system.common.ModelBase;

/**
 * Created by User on 2015-11-16.
 */
public class ModelMyInfo  implements ModelBase {
    @SerializedName("data")
    @Expose
    Data data;

    public ModelMyInfo.Data getData() {
        return data;
    }

    public void setData(ModelMyInfo.Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ModelMyInfo{" +
                "data=" + data +
                '}';
    }

    public class Data{
        @SerializedName("questionCount")
        @Expose
        int questionCount;

        @SerializedName("qemail")
        @Expose
        String qemail;

        @SerializedName("seeds")
        @Expose
        int seed;

        @SerializedName("nickname")
        @Expose
        String nickname;

        @SerializedName("birthDate")
        @Expose
        String birthDate;

        @SerializedName("sex")
        @Expose
        String sex;

        @SerializedName("pw")
        @Expose
        String pw;

        @Override
        public String toString() {
            return "data{" +
                    "questionCount=" + questionCount +
                    ", qemail='" + qemail + '\'' +
                    ", seeds=" + seed +
                    ", nickname='" + nickname + '\'' +
                    ", birthDate='" + birthDate + '\'' +
                    ", sex='" + sex + '\'' +
                    ", pw='" + pw + '\'' +
                    '}';
        }

        public int getQuestionCount() {
            return questionCount;
        }

        public void setQuestionCount(int questionCount) {
            this.questionCount = questionCount;
        }

        public String getQemail() {
            return qemail;
        }

        public void setQemail(String qemail) {
            this.qemail = qemail;
        }

        public int getSeed() {
            return seed;
        }

        public void setSeed(int seed) {
            this.seed = seed;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

//        public String getProfileImage() {
//            return profileImage;
//        }
//
//        public void setProfileImage(String profileImage) {
//            this.profileImage = profileImage;
//        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPw() {
            return pw;
        }

        public void setPw(String pw) {
            this.pw = pw;
        }
    }
}
