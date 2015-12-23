package com.wenwoandroidnew.system.module;

import android.os.Parcel;
import android.os.Parcelable;

import com.wenwoandroidnew.contents.Magazineitem;

/**
 * Created by SeungJin on 2015-11-27.
 */
public class ModelPicture implements Parcelable {

    private String origin ="";
    private String th = "";

    public ModelPicture(String origin, String th) {
        this.origin = origin;
        this.th = th;
    }

    public ModelPicture(Parcel in) {
        origin = in.readString();
        th = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.origin);
        dest.writeString(this.th);
    }

    public static final Parcelable.Creator<ModelPicture> CREATOR = new Parcelable.Creator<ModelPicture>() {
        public ModelPicture createFromParcel(Parcel in) {
            return new ModelPicture(in);
        }

        public ModelPicture[] newArray(int size){
            return new ModelPicture[size];
        }
    };

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTh() {
        return th;
    }

    public void setTh(String th) {
        this.th = th;
    }
}
