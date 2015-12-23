package com.wenwoandroidnew.writer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenwoandroidnew.R;


/**
 * Created by ModelLoginQuery on 2015-11-05.
 */
public class WriterMagazineView extends FrameLayout {
    ImageView profileview, contentPhoto;
    TextView nameView, timeView, placeView, contentView;
    WriterMagazineItem writerMagazineInfo;

    public WriterMagazineView(Context context) {
        super(context);
        init();
    }

    public WriterMagazineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_writer_magazine, this);
        this.profileview = (ImageView) findViewById(R.id.iv_writer_magazine_profile);
        this.contentPhoto = (ImageView) findViewById(R.id.iv_writer_magazine);
        this.nameView = (TextView) findViewById(R.id.tv_writer_magazine_name);
        this.timeView = (TextView) findViewById(R.id.tv_writer_magazine_time);
        this.placeView = (TextView) findViewById(R.id.tv_writer_magazine_place);
        this.contentView = (TextView) findViewById(R.id.tv_magazine_content);
    }
    public void setWriterMagazineInfo(WriterMagazineItem writerMagazineInfo) {
        this.writerMagazineInfo = writerMagazineInfo;

        this.profileview.setImageDrawable(writerMagazineInfo.getProfile());
        this.contentPhoto.setImageDrawable(writerMagazineInfo.getContentPhoto());
        this.nameView.setText(writerMagazineInfo.getName());
        this.timeView.setText(writerMagazineInfo.getTime());
        this.placeView.setText(writerMagazineInfo.getPlace());
        this.contentView.setText(writerMagazineInfo.getContent());
    }


    private int currentIndex;
    public void setCurrentTab(int index) {
        currentIndex = index;
//        tabHost.setCurrentTab(index);
    }
}
