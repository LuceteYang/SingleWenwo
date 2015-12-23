package com.wenwoandroidnew.newsfeed;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.util.UtilUi;

import uk.co.senab.photoview.PhotoView;


public class ZoomPictureActivity extends AppCompatActivity {
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        PhotoView photoView = (PhotoView) findViewById(R.id.iv_photo);
        Bundle b = getIntent().getExtras();
        String url = b.getString("URL");
        if(url.equals("me")){
            photoView.setImageDrawable(AppGlobalSetting.myThProfileImage);
        }else {
            if (!ImageLoader.getInstance().isInited()) {
                ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
                ImageLoader.getInstance().init(config);
            }

            ImageLoader.getInstance().displayImage(url, photoView);
        }

        }

}

