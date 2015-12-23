package com.wenwoandroidnew.newsfeed;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.wenwoandroidnew.newsfeed.answer.AnswerItem;
import com.wenwoandroidnew.system.module.ModelPicture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ModelLoginQuery on 2015-11-01.
 */
public class QuestionItem  implements QandAItem, Parcelable {
    public Drawable questionIcon;
    public int voteNumber=0;
    public String questionPName="";
    public String questionCategory="";
    public String questionTime="";
    public String questionLocation="";
    public String questionTitle="";
    public String questionContext="";
    public String questionAccept="";
    public String status;
    public String qnum;
    public String type;
    public int pick;
    boolean open;
    int anumNumber;
    List<ModelPicture> profileList;
    List<ModelPicture> questionImage1;
    List<ModelPicture> questionImage2;
    boolean isAllfeed=true;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAllfeed() {
        return isAllfeed;
    }

    public void setIsAllfeed(boolean isAllfeed) {
        this.isAllfeed = isAllfeed;
    }

    @Override
    public String toString() {
        return "QuestionItem{" +
                "questionIcon=" + questionIcon +
                ", voteNumber=" + voteNumber +
                ", questionPName='" + questionPName + '\'' +
                ", questionCategory='" + questionCategory + '\'' +
                ", questionTime='" + questionTime + '\'' +
                ", questionLocation='" + questionLocation + '\'' +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionContext='" + questionContext + '\'' +
                ", questionAccept='" + questionAccept + '\'' +
                ", status='" + status + '\'' +
                ", qnum='" + qnum + '\'' +
                ", pick=" + pick +
                ", open=" + open +
                ", anumNumber=" + anumNumber +
                ", profileList=" + profileList +
                ", questionImage1=" + questionImage1 +
                ", questionImage2=" + questionImage2 +
                ", children=" + children +
                '}';
    }

    public int getAnumNumber() {
        return anumNumber;
    }

    public void setAnumNumber(int anumNumber) {
        this.anumNumber = anumNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(questionPName);
        dest.writeString(questionCategory);
        dest.writeList(profileList);
        dest.writeString(questionTime);
        dest.writeString(questionLocation);
        dest.writeString(questionTitle);
        dest.writeString(questionContext);
        dest.writeString(questionAccept);
        dest.writeInt(voteNumber);
        dest.writeList(questionImage1);
        dest.writeList(questionImage2);
        dest.writeString(qnum);
        dest.writeInt(pick);
    }


    public QuestionItem(Parcel in){
        questionPName = in.readString();
        questionCategory = in.readString();
        questionTime = in.readString();
        questionLocation = in.readString();
        questionTitle = in.readString();
        questionContext = in.readString();
        questionAccept = in.readString();
        voteNumber = in.readInt();
        questionImage1 = new ArrayList<ModelPicture>();
        in.readList(questionImage1, ModelPicture.class.getClassLoader());
        questionImage2 =  new ArrayList<ModelPicture>();
        in.readList(questionImage2, ModelPicture.class.getClassLoader());
        status = in.readString();
        qnum = in.readString();
        pick = in.readInt();
        Bitmap bitmap = (Bitmap) in.readParcelable(getClass().getClassLoader());
        if ( bitmap != null ) {
            questionIcon = new BitmapDrawable(bitmap);
        }
        else {
            questionIcon = null;
        }
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getQnum() {
        return qnum;
    }

    public void setQnum(String qnum) {
        this.qnum = qnum;
    }

    public int getPick() {
        return pick;
    }

    public void setPick(int pick) {
        this.pick = pick;
    }

    List<AnswerItem> children = new ArrayList<AnswerItem>();

    public Drawable getQuestionIcon() {
        return questionIcon;
    }
    public void setQuestionIcon(Drawable questionIcon) {
        this.questionIcon = questionIcon;
    }

    public String getVoteNumber() {
        String stvoteNumber=String.valueOf(voteNumber);
        return stvoteNumber;
    }
    public void setVoteNumber(int voteNumber) {
        this.voteNumber = voteNumber;
    }

    public String getQuestionPName() {
        return questionPName;
    }
    public void setQuestionPName(String questionPName) {
        this.questionPName = questionPName;
    }


    public String getQuestionTime() {
        return questionTime;
    }
    public void setQuestionTime(String questionTime) {
        this.questionTime = questionTime;
    }


    public String getQuestionCategory() {
        return questionCategory;
    }
    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory;
    }


    public String getQuestionLocation() {
        return questionLocation;
    }
    public void setQuestionLocation(String questionLocation) {
        this.questionLocation = questionLocation;
    }


    public String getQuestionTitle() {
        return questionTitle;
    }
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }


    public String getQuestionContext() {
        return questionContext;
    }
    public void setQuestionContext(String questionContext) {
        this.questionContext = questionContext;
    }

    public String getQuestionAccept() {
        return questionAccept;
    }
    public void setQuestionAccept(String questionAccept) {
        this.questionAccept = questionAccept;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ModelPicture> getProfileList() {
        return profileList;
    }

    public void setProfileList(List<ModelPicture> profileList) {
        this.profileList = profileList;
    }

    public List<ModelPicture> getQuestionImage1() {
        return questionImage1;
    }

    public void setQuestionImage1(List<ModelPicture> questionImage1) {
        this.questionImage1 = questionImage1;
    }

    public List<ModelPicture> getQuestionImage2() {
        return questionImage2;
    }

    public void setQuestionImage2(List<ModelPicture> questionImage2) {
        this.questionImage2 = questionImage2;
    }

    public List<AnswerItem> getChildren() {
        return children;
    }

    public void setChildren(List<AnswerItem> children) {
        this.children = children;
    }

}
