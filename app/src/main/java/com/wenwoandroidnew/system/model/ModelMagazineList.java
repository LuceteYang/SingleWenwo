package com.wenwoandroidnew.system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wenwoandroidnew.system.common.ModelBase;

import java.util.List;

/**
 * Created by User on 2015-11-16.
 */
public class ModelMagazineList  implements ModelBase {

    @SerializedName("data")
    @Expose
    List<MagazineData> data;

    public List<MagazineData> getData() {
        return data;
    }

    public class MagazineData {

        @SerializedName("mnum")
        @Expose
        int mnum;

        @SerializedName("aemail")
        @Expose
        String aemail;

        @SerializedName("title")
        @Expose
        String title;

        @SerializedName("text")
        @Expose
        String text;

        @SerializedName("category")
        @Expose
        String category;

        @SerializedName("writtenTime")
        @Expose
        String writtenTime;

        @SerializedName("savedCount")
        @Expose
        int savedCount;

        @SerializedName("like")
        @Expose
        int like;

        @SerializedName("nickname")
        @Expose
        String nickname;

        @SerializedName("profileImage")
        @Expose
        List<MagazineImageList> Profileimage;

        @SerializedName("image")
        @Expose
        List<MagazineImageList> image;


        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public List<MagazineImageList> getProfileimage() {
            return Profileimage;
        }

        public void setProfileimage(List<MagazineImageList> profileimage) {
            Profileimage = profileimage;
        }

        public int getMnum() {
            return mnum;
        }

        public void setMnum(int mnum) {
            this.mnum = mnum;
        }

        public String getAemail() {
            return aemail;
        }

        public void setAemail(String aemail) {
            this.aemail = aemail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getWrittenTime() {
            return writtenTime;
        }

        public void setWrittenTime(String writtenTime) {
            this.writtenTime = writtenTime;
        }

        public int getSavedCount() {
            return savedCount;
        }

        public void setSavedCount(int savedCount) {
            this.savedCount = savedCount;
        }

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public List<MagazineImageList> getImage() {
            return image;
        }

        public void setImage(List<MagazineImageList> image) {
            this.image = image;
        }

        public class MagazineImageList {

            @SerializedName("originalPath")
            @Expose
            String originalPath;

            @SerializedName("th_path")
            @Expose
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
}