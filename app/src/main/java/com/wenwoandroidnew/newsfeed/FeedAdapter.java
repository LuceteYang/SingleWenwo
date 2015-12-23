package com.wenwoandroidnew.newsfeed;

import android.view.View;
import android.view.ViewGroup;

import com.wenwoandroidnew.system.common.BasicAdater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ModelLoginQuery on 2015-11-01.
 */
public class FeedAdapter extends BasicAdater<QuestionItem> {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        QuestionView view;

        if (convertView == null) {
            view = new QuestionView(parent.getContext());
        } else {
            view = (QuestionView) convertView;
        }
        view.setQuestionInfo(items.get(position));
        return view;
    }
}
