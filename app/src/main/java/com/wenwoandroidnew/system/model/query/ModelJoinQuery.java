package com.wenwoandroidnew.system.model.query;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ModelLoginQuery on 2015-11-11.
 */
public class ModelJoinQuery {

    private List<String> profileImage = new ArrayList<>();
    private String qemail;
    private String nickname;
    private String birthDate;
    private String pw;
    private String sex;
    private String type;


    public List<String> getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        if(profileImage==""){
            return;
        }
        this.profileImage.add( profileImage);
    }

    public String getQemail() {
        return qemail;
    }

    @Override
    public String toString() {
        return "ModelJoinQuery{" +
                "profileImage=" + profileImage +
                ", qemail='" + qemail + '\'' +
                ", nickname='" + nickname + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", pw='" + pw + '\'' +
                ", sex='" + sex + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public void setQemail(String qemail) {
        this.qemail = qemail;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
