package com.wenwoandroidnew.system.model.query;

/**
 * Created by SeungJin on 2015-11-05.
 */
public class ModelFriendDeleteQuery {

    public String me;

    public String myfriend;

    public ModelFriendDeleteQuery(){}

    public ModelFriendDeleteQuery(String me, String myfriend) {
        this.me = me;
        this.myfriend = myfriend;
    }

    @Override
    public String toString() {
        return "ModelFriendDeleteQuery{" +
                "me='" + me + '\'' +
                ", myfriend='" + myfriend + '\'' +
                '}';
    }
}
