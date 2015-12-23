package com.wenwoandroidnew.contents.magazineDetail;

import android.view.View;
import android.view.ViewGroup;

import com.wenwoandroidnew.system.common.BasicAdater;

/**
 * Created by ModelLoginQuery on 2015-11-05.
 */
public class MagazineDetailAdapter extends BasicAdater<MagazineDetailImageItem> {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MagazienDetailImageView view;

        if (convertView == null) {
            view =  new MagazienDetailImageView(parent.getContext());
        } else {
            view = (MagazienDetailImageView) convertView;
        }
        view.setMagazineDetailImageInfo(items.get(position ));
        return view;
    }
}
