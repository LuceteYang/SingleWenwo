package com.wenwoandroidnew.system.common;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class CommonResult<T> {

    @SerializedName("code")
    public String code;

    @SerializedName("msg")
    public String msg;


    @SerializedName("result")
    public List<T> result = new ArrayList<>();


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<T> getResult() {
        return result;
    }


}