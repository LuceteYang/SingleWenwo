package com.wenwoandroidnew.friends;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wenwoandroidnew.R;

/**
 * Created by ModelLoginQuery on 2015-11-04.
 */
public class GroupFriendView extends LinearLayout {
    TextView GroupName;
    GroupFriendItem groupFriendInfo;
    public GroupFriendView(Context context) {
        super(context);
        init();
    }

    public GroupFriendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.view_friends_group, this);
        this.GroupName = (TextView) findViewById( R.id.tv_group);
    }

    public TextView getGroupName() {
        return GroupName;
    }

    public void setGroupName(TextView groupName) {
        GroupName = groupName;
    }

    public GroupFriendItem getGroupFriendInfo() {
        return groupFriendInfo;
    }

    public void setGroupFriendInfo(GroupFriendItem groupFriendInfo) {
        this.groupFriendInfo = groupFriendInfo;
        this.GroupName.setText(groupFriendInfo.getGroupName());
    }
}
