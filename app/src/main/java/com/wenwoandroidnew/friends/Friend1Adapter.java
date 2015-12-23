package com.wenwoandroidnew.friends;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wenwoandroidnew.friends.ChildFriendItem;
import com.wenwoandroidnew.friends.ChildFriendView;
import com.wenwoandroidnew.friends.GroupFriendItem;
import com.wenwoandroidnew.friends.GroupFriendView;
import com.wenwoandroidnew.friends.MandFItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ModelLoginQuery on 2015-11-04.
 */
public class Friend1Adapter extends BaseAdapter {

    List<MandFItem> items = new ArrayList<MandFItem>();

    private static final int VIEW_TYPE_COUNT = 2;

    private static final int TYPE_INDEX_GROUP = 0;
    private static final int TYPE_INDEX_CHILD = 1;


    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void add(MandFItem data) {
        items.add(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        MandFItem d = items.get(position);
        if (d instanceof GroupFriendItem) {
            return TYPE_INDEX_GROUP;
        } else if (d instanceof ChildFriendItem)  {
            return TYPE_INDEX_CHILD;
        }
        return position;
    }

    public void removeItem(int position){
        items.remove(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)) {
            case TYPE_INDEX_GROUP: {
                GroupFriendView view;
                if (convertView != null && convertView instanceof GroupFriendView) {
                    view = (GroupFriendView)convertView;
                } else {
                    view = new GroupFriendView(parent.getContext());
                }
                view.setGroupFriendInfo((GroupFriendItem) items.get(position));
                return view;
            }
            case TYPE_INDEX_CHILD: {
                ChildFriendView view;
                if (convertView != null && convertView instanceof ChildFriendView) {
                    view = (ChildFriendView)convertView;
                } else {
                    view = new ChildFriendView(parent.getContext());
                }
                view.setChildFiendInfo((ChildFriendItem) items.get(position));
                return view;
            }
            default: {
                return null;
            }
        }
    }
}
