package com.wenwoandroidnew.friends;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.contents.magazineDetail.MagazineDetailActivity;
import com.wenwoandroidnew.friends.friendquestion.FriendQuestionActivity;
import com.wenwoandroidnew.me.ContentsItem;

import java.util.ArrayList;

/**
 * Created by ModelLoginQuery on 2015-11-04.
 */
public class FriendAdapter extends BaseExpandableListAdapter {
    ArrayList<GroupFriendItem> items = new ArrayList<GroupFriendItem>();
    ArrayList<ChildFriendView> viewItems = new ArrayList<>();



    public void add(String groupName, ChildFriendItem child) {
        GroupFriendItem g = null;
        for (GroupFriendItem item : items) {
            if (item.getGroupName().equals(groupName)) {
                g = item;
                break;
            }
        }
        if (g == null) {
            g = new GroupFriendItem();
            g.GroupName = groupName;
            items.add(g);
        }

        if (child != null) {
            ChildFriendItem item = new ChildFriendItem();
            item.FriendName = child.FriendName;
            item.FriendShare= child.FriendShare;
            item.FriendPicture=child.FriendPicture;
            item.bDeleteCheck=false;
            g.children.add(item);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return  items.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return items.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return items.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return (long)groupPosition << 32 | 0xFFFFFFFF;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return (long)groupPosition << 32 | childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupFriendView v;
        if (convertView != null) {
            v = (GroupFriendView)convertView;
        } else {
            v = new GroupFriendView(parent.getContext());
        }
//        v.setGroupName(items.get(groupPosition));
//        v.setIsExpand(isExpanded);
        return v;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildFriendView v;
        if (convertView != null) {
            v = (ChildFriendView)convertView;
        } else {
            v = new ChildFriendView(parent.getContext());
        }

        v.setChildFiendInfo(items.get(groupPosition).children.get(childPosition));
        if (isLastChild) {
            v.setBackgroundColor(Color.YELLOW);
        } else {
            v.setBackgroundColor(Color.TRANSPARENT);
        }

        viewItems.add(v); // 자식뷰를 리스트에 추가

//        v.findViewById(R.id.onGoFriendDetail).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ChildFriendItem i =viewItems.get(groupPosition,childPosition,);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("value", i.FriendName);
//                Intent j = new Intent(v.getContext(), MagazineDetailActivity.class);
//                v.getContext().startActivity(j);
//            }
//        });

        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
