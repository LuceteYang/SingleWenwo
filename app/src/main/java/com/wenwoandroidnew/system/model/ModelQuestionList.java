package com.wenwoandroidnew.system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wenwoandroidnew.system.common.ModelBase;

import java.util.List;

/**
 * Created by SeungJin on 2015-11-12.
 */
public class ModelQuestionList  implements ModelBase {

    @SerializedName("data")
    @Expose
    List<ModelData> data;

    public List<ModelData> getData() {
        return data;
    }



    public class ModelData{

        @SerializedName ("profileImage") @Expose
        List<ImageList> profileImage;

        @SerializedName("nickname")@Expose
        String nickName;

        @SerializedName("qnum")@Expose
        String qnum;

        @SerializedName("category")@Expose
        String category;

        @SerializedName("type")@Expose
        int type;

        @SerializedName("image") @Expose
        List<ImageList> image;

        @SerializedName("text") @Expose
        String text;

        @SerializedName("title") @Expose
        String title;

        @SerializedName("pickTime") @Expose
        String pickTime;

        @SerializedName("dueTime") @Expose
        String dueTime;

        @SerializedName("spentSeed") @Expose
        String spentSeed;

        @SerializedName("open") @Expose
        String open;

        @SerializedName("status") @Expose
        int status;

        @SerializedName("dong") @Expose
        String dong;

        @SerializedName("gu") @Expose
        String du;

        @SerializedName("si") @Expose
        String si;

        @SerializedName("writtenTime") @Expose
        String writtenTime;

        @SerializedName("qemail") @Expose
        String qemail;

        @SerializedName("answerCount") @Expose
        String anserCount;

        @SerializedName("anum") @Expose
        List<String> anum;


        public String getNickName() {
            return nickName;
        }

        public String getQnum() {
            return qnum;
        }

        public String getCategory() {
            return category;
        }

        public int getType() {
            return type;
        }

        public List<ImageList> getImage() {
            return image;
        }

        public String getText() {
            return text;
        }

        public String getTitle() {
            return title;
        }

        public String getPickTime() {
            return pickTime;
        }

        public String getDueTime() {
            return dueTime;
        }

        public String getSpentSeed() {
            return spentSeed;
        }

        public String getOpen() {
            return open;
        }

        public int getStatus() {
            return status;
        }

        public String getDong() {
            return dong;
        }

        public String getDu() {
            return du;
        }

        public String getSi() {
            return si;
        }

        public String getWrittenTime() {
            return writtenTime;
        }

        public String getQemail() {
            return qemail;
        }

        public String getAnserCount() {
            return anserCount;
        }

        public List<String> getAnum() {
            return anum;
        }

        public List<ImageList> getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(List<ImageList> profileImage) {
            this.profileImage = profileImage;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public void setQnum(String qnum) {
            this.qnum = qnum;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setImage(List<ImageList> image) {
            this.image = image;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPickTime(String pickTime) {
            this.pickTime = pickTime;
        }

        public void setDueTime(String dueTime) {
            this.dueTime = dueTime;
        }

        public void setSpentSeed(String spentSeed) {
            this.spentSeed = spentSeed;
        }

        public void setOpen(String open) {
            this.open = open;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setDong(String dong) {
            this.dong = dong;
        }

        public void setDu(String du) {
            this.du = du;
        }

        public void setSi(String si) {
            this.si = si;
        }

        public void setWrittenTime(String writtenTime) {
            this.writtenTime = writtenTime;
        }

        public void setQemail(String qemail) {
            this.qemail = qemail;
        }

        public void setAnserCount(String anserCount) {
            this.anserCount = anserCount;
        }

        public void setAnum(List<String> anum) {
            this.anum = anum;
        }

        public class ImageList {

            @SerializedName("originalPath") @Expose
            String originalPath;

            @SerializedName("th_path") @Expose
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

