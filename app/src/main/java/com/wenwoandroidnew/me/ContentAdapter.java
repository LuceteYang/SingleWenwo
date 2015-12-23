package com.wenwoandroidnew.me;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.wenwoandroidnew.contents.magazineDetail.MagazineDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ModelLoginQuery on 2015-11-02.
 */
public class ContentAdapter extends BaseAdapter {
    List<ContentsItem> items = new ArrayList<>();

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

    public void add(ContentsItem contentsItem){
        items.add(contentsItem);
        notifyDataSetChanged();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ContentsView view;
        if (convertView == null) {
            view =  new ContentsView(parent.getContext());
        } else {
            view = (ContentsView) convertView;
        }
        view.setContentsInfo(items.get(position));


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentsItem item = items.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("value", item);
                Intent i = new Intent(v.getContext(), MagazineDetailActivity.class);
                v.getContext().startActivity(i);
            }
        });
        return view;
    }

}
