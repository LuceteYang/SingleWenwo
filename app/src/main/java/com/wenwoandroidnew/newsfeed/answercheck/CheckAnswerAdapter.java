package com.wenwoandroidnew.newsfeed.answercheck;

import android.view.View;
import android.view.ViewGroup;
import com.wenwoandroidnew.newsfeed.answer.AnswerItem;
import com.wenwoandroidnew.system.common.BasicAdater;


/**
 * Created by ModelLoginQuery on 2015-11-03.
 */
public class CheckAnswerAdapter extends BasicAdater<AnswerItem> {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheckAnswerView view;
        if (convertView == null) {
            view =  new CheckAnswerView(parent.getContext());
        } else {
            view = (CheckAnswerView) convertView;
        }
        view.setAnswerCheckInfo(items.get(position));
        return view;
    }
}
