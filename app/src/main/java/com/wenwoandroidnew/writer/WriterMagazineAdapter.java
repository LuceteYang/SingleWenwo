package com.wenwoandroidnew.writer;

import android.view.View;
import android.view.ViewGroup;

import com.wenwoandroidnew.system.common.BasicAdater;

/**
 * Created by ModelLoginQuery on 2015-11-05.
 */
public class WriterMagazineAdapter extends BasicAdater<WriterMagazineItem> {
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WriterMagazineView view;

        if (convertView == null) {
            view =  new WriterMagazineView(parent.getContext());
        } else {
            view = (WriterMagazineView) convertView;
        }
        view.setWriterMagazineInfo(items.get(position));
        return view;
    }
}
