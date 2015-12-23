package com.wenwoandroidnew.system.common;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class CommonListResult<T> {

    @SerializedName("code")
    public String code;

    @SerializedName("msg")
    public String msg;


    @SerializedName("result")
    public T result;


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getResult() {
        return result;
    }
}