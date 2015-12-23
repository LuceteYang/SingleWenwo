package com.wenwoandroidnew.system.model;

import android.graphics.drawable.Drawable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by SeungJin on 2015-11-06.
 */
public class LocalLoginUser extends RealmObject{

    @PrimaryKey
    private String email;
    private String password;

    private String name;
    private String lastLoginDate;
    private String profileImage;
    private String thProfileImage;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getThProfileImage() {
        return thProfileImage;
    }

    public void setThProfileImage(String thProfileImage) {
        this.thProfileImage = thProfileImage;
    }
}
