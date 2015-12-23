package com.wenwoandroidnew.newsfeed.answer;

import android.graphics.drawable.Drawable;

import com.wenwoandroidnew.newsfeed.QandAItem;

/**
 * Created by ModelLoginQuery on 2015-11-02.
 */
public class AnswerFooteritem implements QandAItem {
    public Drawable Adopt;

    public Drawable getAdopt() {
        return Adopt;
    }

    public void setAdopt(Drawable adopt) {
        Adopt = adopt;
    }
}
