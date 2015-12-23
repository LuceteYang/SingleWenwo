package com.wenwoandroidnew.system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wenwoandroidnew.system.common.ModelBase;

import java.util.List;

/**
 * Created by User on 2015-11-16.
 */
public class ModelFriendDelete  implements ModelBase {

    @SerializedName("data")
    @Expose
    List<FriendDeleteData> data;


    public class FriendDeleteData{

    }

}

