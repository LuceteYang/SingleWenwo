package com.wenwoandroidnew.newsfeed.answer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenwoandroidnew.R;


/**
 * Created by ModelLoginQuery on 2015-11-02.
 */
public class AnswerFooterView extends FrameLayout {

    ImageView Adopt;
    AnswerFooteritem answerfooterInfo;

    public AnswerFooterView(Context context) {
        super(context);
        init();
    }

    public AnswerFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        inflate( getContext(), R.layout.view_answer_footer, this);
        this.Adopt = (ImageView) findViewById( R.id.imageView13);
    }

    public void setFooterInfo(AnswerFooteritem answerfooterInfo) {
        this.answerfooterInfo = answerfooterInfo;
        this.Adopt.setImageDrawable(answerfooterInfo.getAdopt());
    }

}
