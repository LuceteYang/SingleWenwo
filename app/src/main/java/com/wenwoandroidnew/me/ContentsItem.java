package com.wenwoandroidnew.me;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.wenwoandroidnew.newsfeed.answer.AnswerItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ModelLoginQuery on 2015-11-02.
 */
public class ContentsItem implements Serializable {
    Drawable ContentsImage;
    String ContentsCategory;
    String ContentsTitle;
    String ContentsAnswerPerson;

    List<AnswerItem> children = new ArrayList<AnswerItem>();
    public String getContentsCategory() {
        return ContentsCategory;
    }

    public void setContentsCategory(String contentsCategory) {
        ContentsCategory = contentsCategory;
    }

    public String getContentsTitle() {
        return ContentsTitle;
    }

    public void setContentsTitle(String contentsTitle) {
        ContentsTitle = contentsTitle;
    }

    public String getContentsAnswerPerson() {
        return ContentsAnswerPerson;
    }

    public void setContentsAnswerPerson(String contentsAnswerPerson) {
        ContentsAnswerPerson = contentsAnswerPerson;
    }

    public Drawable getContentsImage() {

        return ContentsImage;
    }

    public void setContentsImage(Drawable contentsImage) {
        ContentsImage = contentsImage;
    }
    @Override
    public String toString() {
        return "ContentsItem{" +
                ", ContentsCategory='" + ContentsCategory + '\'' +
                ", ContentsTitle='" + ContentsTitle + '\'' +
                ", ContentsAnswerPerson='" + ContentsAnswerPerson + '\'' +
                ", children=" + children +
                '}';
    }

}
