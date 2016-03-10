package com.wenwoandroidnew.newsfeed.answer;

import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.wenwoandroidnew.newsfeed.QandAItem;
import com.wenwoandroidnew.newsfeed.ZoomPictureActivity;
import com.wenwoandroidnew.system.module.ModelPicture;

import java.util.List;

/**
 * Created by ModelLoginQuery on 2015-11-02.
 */
public class AnswerItem implements QandAItem {
    public Drawable AnswerType;
    List<ModelPicture> AnswerPersonPicture;
    List<ModelPicture> answerImage1;
    List<ModelPicture> answerImage2;
    public String AnswerName;
    public String AnswerLevel;
    public String AnswerAccept;
    public String AnswerContent;
    public String AnswerQnum;
    public String AnswerStatus;
    public String AnswerCategory;
    public String Answernumber;
    public String aemail;
    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ModelPicture> getProfileList() {
        return AnswerPersonPicture;
    }

    public void setProfileList(List<ModelPicture> profileList) {
        this.AnswerPersonPicture = profileList;
    }

    public List<ModelPicture> getAnswerImage1() {
        return answerImage1;
    }

    public void setAnswerImage1(List<ModelPicture> answerImage1) {
        this.answerImage1 = answerImage1;
    }

    public List<ModelPicture> getAnswerImage2() {
        return answerImage2;
    }

    public void setAnswerImage2(List<ModelPicture> answerImage2) {
        this.answerImage2 = answerImage2;
    }

    public String getAemail() {
        return aemail;
    }

    public void setAemail(String aemail) {
        this.aemail = aemail;
    }

    public Drawable getAnswerType() {
        return AnswerType;
    }

    public void setAnswerType(Drawable answerType) {
        AnswerType = answerType;
    }



    public String getAnswerQnum() {
        return AnswerQnum;
    }

    public void setAnswerQnum(String answerQnum) {
        AnswerQnum = answerQnum;
    }

    public String getAnswerStatus() {
        return AnswerStatus;
    }

    public void setAnswerStatus(String answerStatus) {
        AnswerStatus = answerStatus;
    }

    public String getAnswerCategory() {
        return AnswerCategory;
    }

    public void setAnswerCategory(String answerCategory) {
        AnswerCategory = answerCategory;
    }

    public String getAnswernumber() {
        return Answernumber;
    }

    public void setAnswernumber(String answernumber) {
        Answernumber = answernumber;
    }

    public String getAnswerName() {
        return AnswerName;
    }

    public void setAnswerName(String answerName) {
        AnswerName = answerName;
    }

    public String getAnswerLevel() {
        return AnswerLevel;
    }

    public void setAnswerLevel(String answerLevel) {
        AnswerLevel = answerLevel;
    }

    public String getAnswerAccept() {
        return AnswerAccept;
    }

    public void setAnswerAccept(String answerAccept) {
        AnswerAccept = answerAccept;
    }

    public String getAnswerContent() {
        return AnswerContent;
    }

    public void setAnswerContent(String answerContent) {
        AnswerContent = answerContent;
    }


}
