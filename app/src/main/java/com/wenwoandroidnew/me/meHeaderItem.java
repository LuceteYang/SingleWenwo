package com.wenwoandroidnew.me;

import android.graphics.drawable.Drawable;

import com.wenwoandroidnew.system.module.ModelPicture;

import java.util.List;

/**
 * Created by ModelLoginQuery on 2015-11-05.
 */
public class meHeaderItem {
    List<ModelPicture> profileList;
    String myName;
    String myQuestion;
    String questionNumber;

    public List<ModelPicture> getProfileList() {
        return profileList;
    }

    public void setProfileList(List<ModelPicture> profileList) {
        this.profileList = profileList;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getMyQuestion() {
        return myQuestion;
    }

    public void setMyQuestion(String myQuestion) {
        this.myQuestion = myQuestion;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }
}
