package com.wenwoandroidnew.system.common;

import android.util.Log;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ModelLoginQuery on 2015-11-01.
 */
public abstract class BasicAdater<T> extends BaseAdapter {
    protected List<T> items = new ArrayList<>();

    public void add( T t){
        items.add(t);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
