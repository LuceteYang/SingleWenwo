package com.wenwoandroidnew.writer;

import android.graphics.drawable.Drawable;

/**
 * Created by ModelLoginQuery on 2015-11-05.
 */
public class WriterMagazineItem {
    Drawable profile;
    Drawable contentPhoto;
    String name;
    String time;
    String place;
    String content;

    public Drawable getContentPhoto() {
        return contentPhoto;
    }

    public void setContentPhoto(Drawable contentPhoto) {
        this.contentPhoto = contentPhoto;
    }



    public Drawable getProfile() {
        return profile;
    }

    public void setProfile(Drawable profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
