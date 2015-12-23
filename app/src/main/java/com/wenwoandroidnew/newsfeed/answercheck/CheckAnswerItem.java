package com.wenwoandroidnew.newsfeed.answercheck;

import android.graphics.drawable.Drawable;

/**
 * Created by ModelLoginQuery on 2015-11-03.
 */
public class CheckAnswerItem {
    public Drawable Answerimg;
    public String AnswerName;
    public String AnswerLevel;
    public String AnswerAccept;
    public String AnswerContent;

    public Drawable getAnswerimg() {
        return Answerimg;
    }

    public void setAnswerimg(Drawable answerimg) {
        Answerimg = answerimg;
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
