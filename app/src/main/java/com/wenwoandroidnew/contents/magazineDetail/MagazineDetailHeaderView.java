package com.wenwoandroidnew.contents.magazineDetail;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.util.UtilCommon;

import java.io.IOException;


/**
 * Created by ModelLoginQuery on 2015-11-05.
 */
public class MagazineDetailHeaderView extends FrameLayout {


    ImageView ivWriter;
    TextView tvWriter, tvTime, tvPlace, tvContent;
    MagazineDetailHeaderItem magazineDetailHeaderInfo;

    public MagazineDetailHeaderView(Context context) {
        super(context);
        init();
    }

    public MagazineDetailHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        inflate( getContext(), R.layout.view_magazine_detail_header, this);
        this.ivWriter = (ImageView) findViewById( R.id.iv_magazine_detail_writer);
        this.tvWriter = (TextView) findViewById( R.id.tv_magazine_detail_writer);
        this.tvTime = (TextView) findViewById( R.id.tv_magazine_detail_time);
        this.tvPlace = (TextView) findViewById( R.id.tv_magazine_place);
        this.tvContent= (TextView) findViewById( R.id.tv_magazine_detail_content);
    }

    public void setMagazineDetailHeaderInfo(MagazineDetailHeaderItem magazineDetailHeaderInfo) {
        this.magazineDetailHeaderInfo = magazineDetailHeaderInfo;

        this.tvWriter.setText(magazineDetailHeaderInfo.getWriterName());
        this.tvTime.setText(magazineDetailHeaderInfo.getTime());
        this.tvPlace.setText(magazineDetailHeaderInfo.getPlace());
        this.tvContent.setText(magazineDetailHeaderInfo.getContent());
        AppGlobalSetting.answerName = magazineDetailHeaderInfo.getWriterName();
        // 이미지가 있을 경우만 처리함
        if( magazineDetailHeaderInfo.getProfileImageList() != null && magazineDetailHeaderInfo.getProfileImageList().size()>0){
            try {
                // 이미지 뷰를 같이 넘겨서 그곳에 다운로드 받은 이미지 설정함
                AppGlobalSetting.answerImage = magazineDetailHeaderInfo.getProfileImageList().get(0).getTh();
                UtilCommon.urlToDrawableProfileImage(magazineDetailHeaderInfo.getProfileImageList().get(0).getTh(), ivWriter);
            } catch (IOException e) {
                Log.d("MagazineView : 에러", e.toString());
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public ImageView getIvWriter() {
        return ivWriter;
    }

    public void setIvWriter(ImageView ivWriter) {
        this.ivWriter = ivWriter;
    }

    public TextView getTvWriter() {
        return tvWriter;
    }

    public void setTvWriter(TextView tvWriter) {
        this.tvWriter = tvWriter;
    }

    public TextView getTvTime() {
        return tvTime;
    }

    public void setTvTime(TextView tvTime) {
        this.tvTime = tvTime;
    }

    public TextView getTvPlace() {
        return tvPlace;
    }

    public void setTvPlace(TextView tvPlace) {
        this.tvPlace = tvPlace;
    }

    public TextView getTvContent() {
        return tvContent;
    }

    public void setTvContent(TextView tvContent) {
        this.tvContent = tvContent;
    }

    public MagazineDetailHeaderItem getMagazineDetailHeaderInfo() {
        return magazineDetailHeaderInfo;
    }
}
