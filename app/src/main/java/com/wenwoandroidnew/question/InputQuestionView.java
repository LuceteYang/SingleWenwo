package com.wenwoandroidnew.question;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenwoandroidnew.R;

import java.util.ArrayList;

/**
 * Created by ModelLoginQuery on 2015-11-02.
 */
public class InputQuestionView extends FrameLayout {
    ImageView ShopSeedIcon, InputImage1, InputImage2, LoadImageIcon;
    TextView NowSeed, MySeed, InputCount;
    InputQuestionItem InputQuestionInfo;

    public InputQuestionView(Context context) {
        super(context);
        init();
    }

    public InputQuestionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_input_question, this);
        this.ShopSeedIcon = (ImageView) findViewById(R.id.icon_shop_seed);
        this.InputImage1 = (ImageView) findViewById(R.id.input_image1);
        this.InputImage2 = (ImageView) findViewById(R.id.input_image2);
        this.LoadImageIcon = (ImageView) findViewById(R.id.load_image);
        this.NowSeed = (TextView) findViewById(R.id.text_now_seed);
        this.MySeed = (TextView) findViewById(R.id.text_my_seed);
        this.InputCount = (TextView) findViewById(R.id.text_input_counter);
    }

    public void setInputQuestionInfo(InputQuestionItem InputQuestionInfo) {
        this.InputQuestionInfo = InputQuestionInfo;

        this.ShopSeedIcon.setImageDrawable(InputQuestionInfo.getShopSeedIcon());
        this.InputImage1.setImageDrawable(InputQuestionInfo.getInputImage1());
        this.InputImage2.setImageDrawable(InputQuestionInfo.getInputImage2());
        this.LoadImageIcon.setImageDrawable(InputQuestionInfo.getLoadImageIcon());
        this.NowSeed.setText(InputQuestionInfo.getNowSeed());
        this.MySeed.setText(InputQuestionInfo.getMySeed());
        this.InputCount.setText(InputQuestionInfo.getInputCount());
    }

    private static final int REQUEST_IMAGE = 2;
    private ArrayList<String> mSelectPath;

    private OnClickListener btnClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // 이미지 로드하기
                case R.id.load_image: {

                    break;
                }

            }
        }
    };


}
