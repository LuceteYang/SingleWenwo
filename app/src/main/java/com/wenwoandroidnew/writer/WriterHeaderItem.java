package com.wenwoandroidnew.writer;

import android.graphics.drawable.Drawable;

import com.wenwoandroidnew.system.module.ModelPicture;

import java.util.List;

/**
 * Created by ModelLoginQuery on 2015-11-07.
 */
public class WriterHeaderItem {
    List<ModelPicture> writerProfile;
    String writerName;
    String writerCoupon;
    String writerGrade;
    String writerAccept;

    public List<ModelPicture> getWriterProfile() {
        return writerProfile;
    }

    public void setWriterProfile(List<ModelPicture> writerProfile) {
        this.writerProfile = writerProfile;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getWriterCoupon() {
        return writerCoupon;
    }

    public void setWriterCoupon(String writerCoupon) {
        this.writerCoupon = writerCoupon;
    }

    public String getWriterGrade() {
        return writerGrade;
    }

    public void setWriterGrade(String writerGrade) {
        this.writerGrade = writerGrade;
    }

    public String getWriterAccept() {
        return writerAccept;
    }

    public void setWriterAccept(String writerAccept) {
        this.writerAccept = writerAccept;
    }
}
