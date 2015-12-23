package com.wenwoandroidnew.me;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wenwoandroidnew.R;


/**
 * Created by ModelLoginQuery on 2015-11-02.
 */
public class ContentsView extends LinearLayout {
    ImageView ContentsImage;
    TextView ContentsCategory, ContentsTitle, ContentsAnswerPerson;
    ContentsItem contentInfo;
    public ContentsView(Context context) {
        super(context);
        init();
    }

    public ContentsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        inflate( getContext(), R.layout.view_contents, this);
        this.ContentsImage = (ImageView) findViewById( R.id.imageView6);
        this.ContentsTitle = (TextView) findViewById( R.id.contents_content);
        this.ContentsCategory = (TextView) findViewById( R.id.contents_category);
        this.ContentsAnswerPerson = (TextView) findViewById( R.id.contents_answer_person);
    }

    public void setContentsInfo(ContentsItem contentInfo) {
        this.contentInfo = contentInfo;

        this.ContentsImage.setImageDrawable(contentInfo.getContentsImage());
        this.ContentsTitle.setText(contentInfo.getContentsTitle());
        this.ContentsCategory.setText(contentInfo.getContentsCategory());
        this.ContentsAnswerPerson.setText(contentInfo.getContentsAnswerPerson());
    }

}
