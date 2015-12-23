package com.wenwoandroidnew.question;

import android.graphics.drawable.Drawable;

import com.wenwoandroidnew.newsfeed.QandAItem;

/**
 * Created by ModelLoginQuery on 2015-11-02.
 */
public class InputQuestionItem implements QandAItem {
    Drawable ShopSeedIcon;
    int InputCount;
    String NowSeed;
    String MySeed;
    String Title;
    String Content;
    Drawable InputImage1;
    Drawable InputImage2;
    Drawable LoadImageIcon;

    public Drawable getShopSeedIcon() {
        return ShopSeedIcon;
    }

    public void setShopSeedIcon(Drawable shopSeedIcon) {
        ShopSeedIcon = shopSeedIcon;
    }

    public String getInputCount() {
        String stInputCount=String.valueOf(InputCount);
        return stInputCount;
    }

    public void setInputCount(int inputCount) {
        InputCount = inputCount;
    }

    public String getNowSeed() {
        return NowSeed;
    }

    public void setNowSeed(String nowSeed) {
        NowSeed = nowSeed;
    }

    public String getMySeed() {
        return MySeed;
    }

    public void setMySeed(String mySeed) {
        MySeed = mySeed;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Drawable getInputImage1() {
        return InputImage1;
    }

    public void setInputImage1(Drawable inputImage1) {
        InputImage1 = inputImage1;
    }

    public Drawable getInputImage2() {
        return InputImage2;
    }

    public void setInputImage2(Drawable inputImage) {
        InputImage2 = inputImage;
    }

    public Drawable getLoadImageIcon() {
        return LoadImageIcon;
    }

    public void setLoadImageIcon(Drawable loadImageIcon) {
        LoadImageIcon = loadImageIcon;
    }
}
