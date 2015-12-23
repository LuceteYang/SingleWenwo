package com.wenwoandroidnew.contents;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

import com.wenwoandroidnew.contents.magazineDetail.MagazineDetailImageItem;
import com.wenwoandroidnew.system.module.ModelPicture;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ModelLoginQuery on 2015-11-05.
 */
public class Magazineitem implements Parcelable {
    String content;
    String writer;
    String nickname;
    String writtenTime;
    String like;
    String save;
    String isLike;
    String isSave;
    String directoy;
    List<ModelPicture> profileImageList;
    List<ModelPicture> magazineImageList;

    public void readFromParcel(Parcel in) {
        this.content = in.readString();
        this.writer = in.readString();
        this.like = in.readString();
        this.save = in.readString();
        this.directoy = in.readString();
        this.isLike = in.readString();
        this.isSave = in.readString();
        this.nickname = in.readString();
        this.writtenTime = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeString(this.writer);
        dest.writeString(this.like);
        dest.writeString(this.save);
        dest.writeString(this.directoy);
        dest.writeString(this.isLike);
        dest.writeString(this.isSave);
        dest.writeString(this.nickname);
        dest.writeString(this.writtenTime);
        dest.writeList(this.profileImageList);
        dest.writeList(this.magazineImageList);
    }

    public static final Parcelable.Creator<Magazineitem> CREATOR = new Parcelable.Creator<Magazineitem>() {
        public Magazineitem createFromParcel(Parcel in) {
            return new Magazineitem(in);
        }

        public Magazineitem[] newArray(int size){
            return new Magazineitem[size];
        }
    };

    public Magazineitem(Parcel in){
        content = in.readString();
        writer = in.readString();
        like = in.readString();
        isLike = in.readString();
        save = in.readString();
        isSave = in.readString();
        directoy = in.readString();
        nickname = in.readString();
        writtenTime = in.readString();
        profileImageList = new ArrayList<ModelPicture>();
        in.readList(profileImageList,ModelPicture.class.getClassLoader());
        magazineImageList = new ArrayList<ModelPicture>();
        in.readList(magazineImageList, ModelPicture.class.getClassLoader());
    }

    @Override
    public String toString() {
        return getWriter().toString()+"    "+getContent().toString()+"    "+getDirectoy()+"    "+getLike()+"    "+getSave();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWrittenTime() {
        return writtenTime;
    }

    public void setWrittenTime(String writtenTime) {
        this.writtenTime = writtenTime;
    }

    public List<ModelPicture> getProfileImageList() {
        return profileImageList;
    }

    public void setProfileImageList(List<ModelPicture> profileImageList) {
        this.profileImageList = profileImageList;
    }

    public String getIsLike() {
        return isLike;
    }

    public void setIsLike(String isLike) {
        this.isLike = isLike;
    }

    public String getIsSave() {
        return isSave;
    }

    public void setIsSave(String isSave) {
        this.isSave = isSave;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getDirectoy() {
        return directoy;
    }

    public void setDirectoy(String directoy) {
        this.directoy = directoy;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getSave() {
        return save;
    }


    public List<ModelPicture> getMagazineImageList() {
        return magazineImageList;
    }

    public void setMagazineImageList(List<ModelPicture> magazineImageList) {
        this.magazineImageList = magazineImageList;
    }


}
