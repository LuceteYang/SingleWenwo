package com.wenwoandroidnew.contents.magazineDetail;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.newsfeed.ZoomPictureActivity;
import com.wenwoandroidnew.system.module.ModelPicture;
import com.wenwoandroidnew.system.util.UtilCommon;


/**
 * Created by ModelLoginQuery on 2015-11-05.
 */
public class MagazienDetailImageView extends LinearLayout {

    ImageView imageView1;
    ImageView imageView2;

    int index = 0;
    MagazineDetailImageItem magazineDetailImageInfo;
    public MagazienDetailImageView(Context context) {
        super(context);
        init();
    }

    public MagazienDetailImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        inflate( getContext(), R.layout.view_magazine_detail_image, this);
        imageView1 = ((ImageView) findViewById( R.id.iv_magazine_detail1));

        imageView2 = ((ImageView) findViewById( R.id.iv_magazine_detail2));

    }

    private void showImage( String url){
        Intent intent = new Intent( getContext(), ZoomPictureActivity.class);
        intent.putExtra("URL"  ,url);
        getContext().startActivity( intent);
    }

    public void setMagazineDetailImageInfo(MagazineDetailImageItem magazineDetailImageInfo) {
        this.magazineDetailImageInfo = magazineDetailImageInfo;

        if( magazineDetailImageInfo.getImageList().size() == 0){
            imageView1.setVisibility(GONE);
            imageView2.setVisibility(GONE);
            index = 0;
        }
        else if ( magazineDetailImageInfo.getImageList().size() == 1){
            imageView2.setVisibility(GONE);
            index = 1;
        }
        else if( magazineDetailImageInfo.getImageList().size() == 2){
            index = 2;
        }

        int index2 = 0;
        for(final ModelPicture modelPicture : magazineDetailImageInfo.getImageList()){
            try {
                //new DownloadImageTask().execute(str);
                if ( index == 1){
                    UtilCommon.urlToDrawableProfileImage(modelPicture.getTh(), imageView1);
                    imageView1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showImage( modelPicture.getOrigin());
                        }
                    });
                    //new DownloadImageTask().execute(src);
                }
                else if( index == 2){
                    if( index2 == 0){
                        UtilCommon.urlToDrawableProfileImage(modelPicture.getTh(), imageView1);
                        imageView1.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showImage(modelPicture.getOrigin());
                            }
                        });
                        index2++;
                    }
                    else{
                        UtilCommon.urlToDrawableProfileImage(modelPicture.getTh(), imageView2);
                        imageView2.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showImage( modelPicture.getOrigin());
                            }
                        });
                    }

                }
            }
            catch (Exception e){
                   Log.d("ERROR" , e.getMessage());
                }
        }
    }


}
