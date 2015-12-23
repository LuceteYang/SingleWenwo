package com.wenwoandroidnew.contents.magazineDetail;

import android.graphics.drawable.Drawable;

import com.wenwoandroidnew.system.module.ModelPicture;

import java.util.List;

/**
 * Created by ModelLoginQuery on 2015-11-05.
 */
public class MagazineDetailHeaderItem {
    Drawable writerImage;
    String writerName;
    String time;
    String email;
    String place;
    List<ModelPicture> profileImageList;

    public List<ModelPicture> getProfileImageList() {
        return profileImageList;
    }

    public void setProfileImageList(List<ModelPicture> profileImageList) {
        this.profileImageList = profileImageList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Drawable getWriterImage() {
        return writerImage;
    }

    public void setWriterImage(Drawable writerImage) {
        this.writerImage = writerImage;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    String content;
}
