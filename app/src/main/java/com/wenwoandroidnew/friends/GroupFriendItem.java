package com.wenwoandroidnew.friends;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ModelLoginQuery on 2015-11-04.
 */
public class GroupFriendItem implements MandFItem {
     public String GroupName;

    List<ChildFriendItem> children = new ArrayList<ChildFriendItem>();

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }
}
