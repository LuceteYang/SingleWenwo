package com.wenwoandroidnew.answerer;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenwoandroidnew.HomeActivity;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.newsfeed.QandAItem;
import com.wenwoandroidnew.newsfeed.QuestionDetailView;
import com.wenwoandroidnew.newsfeed.QuestionItem;
import com.wenwoandroidnew.newsfeed.ZoomPictureActivity;
import com.wenwoandroidnew.newsfeed.answer.AnswerItem;
import com.wenwoandroidnew.newsfeed.answer.AnswerView;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.writer.WriterActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ModelLoginQuery on 2015-11-02.
 */
public class AnswererAdapter extends BaseAdapter {

    List<QandAItem> items = new ArrayList<QandAItem>();

    private static final int VIEW_TYPE_COUNT = 2;

    private static final int TYPE_INDEX_QUESTION = 0;
    private static final int TYPE_INDEX_ANSWER = 1;


    public void add(QandAItem data) {
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
        QandAItem d = items.get(position);
        if (d instanceof QuestionItem) {
            return TYPE_INDEX_QUESTION;
        } else if (d instanceof  AnswerItem)  {
            return TYPE_INDEX_ANSWER;
        }
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)) {
            case TYPE_INDEX_QUESTION: {
                final QuestionDetail1View view;
                if (convertView != null && convertView instanceof QuestionDetail1View) {
                    view = (QuestionDetail1View)convertView;
                } else {
                    view = new QuestionDetail1View(parent.getContext());
                }
                view.setQuestionInfo((QuestionItem) items.get(position));
                view.getQuestionImage1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), ZoomPictureActivity.class);
                        intent.putExtra("URL", view.getQuestionInfo().getQuestionImage1().get(0).getOrigin());
                        v.getContext().startActivity(intent);
                    }
                });

                view.getQuestionImage2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent( v.getContext(), ZoomPictureActivity.class);
                        intent.putExtra("URL",view.getQuestionInfo().getQuestionImage2().get(0).getOrigin());
                        v.getContext().startActivity(intent);
                    }
                });

                view.getModifyImage().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), AnswerActivity.class);
                        v.getContext().startActivity(intent);
                    }
                });
                return view;
            }
            case TYPE_INDEX_ANSWER: {
                final AnswerView view;
                if (convertView != null && convertView instanceof AnswerView) {
                    view = (AnswerView)convertView;
                } else {
                    view = new AnswerView(parent.getContext());
                }
                view.setAnswerInfo((AnswerItem) items.get(position));
                final ImageView profile = (ImageView)view.findViewById(R.id.iv_answer_profile);
                profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(), WriterActivity.class);
                        AnswerItem a = (AnswerItem) items.get(position);
                        i.putExtra("aemail", view.getAnswerItemInfo().getAemail());
                        AppGlobalSetting.answerImage = a.getProfileList().get(0).getTh();
                        AppGlobalSetting.answerName =  ((TextView)view.findViewById( R.id.tv_answer_name)).getText().toString();

                        v.getContext().startActivity(i);
                    }
                });
                ImageView image = (ImageView)view.findViewById(R.id.imageView8);
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent( v.getContext(), ZoomPictureActivity.class);
                        intent.putExtra("URL",view.getAnswerItemInfo().getAnswerImage1().get(0).getOrigin());
                        v.getContext().startActivity(intent);
                    }
                });
                ImageView image1 = (ImageView)view.findViewById(R.id.imageView9);
                image1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent( v.getContext(), ZoomPictureActivity.class);
                        intent.putExtra("URL",view.getAnswerItemInfo().getAnswerImage2().get(0).getOrigin());
                        v.getContext().startActivity(intent);
                    }
                });


                return view;
            }
            default: {
                return null;
            }
        }
    }
}
