package com.wenwoandroidnew.system.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.common.BitMatrix;
import com.wenwoandroidnew.ImageCache;
import com.wenwoandroidnew.system.AppGlobalSetting;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by SeungJin on 2015-10-29.
 */
public class UtilCommon {


    public static String now(){

        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
        Date currentTime = new Date ( );
        String dTime = formatter.format ( currentTime );
        return dTime;
    }

    // 메세지 띄우기
    public static void showMessage( Context context, String message){
        Toast.makeText( context, message, Toast.LENGTH_LONG).show();
    }

    public static void showLog( String key, String message){
        Log.d(key, message);
    }

    public static Bitmap download_Image(String url) {

        //---------------------------------------------------
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("Hub","Error getting the image from server : " + e.getMessage().toString());
        }
        return bm;
        //---------------------------------------------------

    }

    public static void urlToDrawableProfileImage(String url, String url2) {
        new DownloadImageTask2().execute( url, url2);
    }


    // 이미지 다운로드 쓰레드
    private static class DownloadImageTask2 extends AsyncTask<Object, Void, Bitmap> {

        protected Bitmap doInBackground(Object... urls) {
            String urldisplay = (String)urls[0];
            String urldisplay2 = (String)urls[1];

            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                AppGlobalSetting.myProfileImage = new BitmapDrawable( AppGlobalSetting.context.getResources(), mIcon11);

                in = new java.net.URL(urldisplay2).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                AppGlobalSetting.myThProfileImage = new BitmapDrawable( AppGlobalSetting.context.getResources(), mIcon11);

            } catch (Exception e) {
                Log.e("Error", e.toString());
                e.printStackTrace();
            }
            return mIcon11;
        }
    }


    public static void urlToDrawableProfileImage(String url, ImageView _imageView) throws IOException {
        new DownloadImageTask().execute(_imageView, url);
    }

    // 이미지 다운로드 쓰레드
    private static class DownloadImageTask extends AsyncTask<Object, Void, Bitmap> {

        private ImageView imageView;
        private String urldisplay;
        protected Bitmap doInBackground(Object... urls) {
            urldisplay = (String)urls[1];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                ////
                Bitmap bitmap = ImageCache.getInstance().getBitmapFromMemCache(urldisplay);
                if (bitmap == null) {
                    bitmap = ImageCache.getInstance().getBitmapFromDiskCache(urldisplay);
                    ImageCache.getInstance().addBitmapToCache(urldisplay, bitmap);
                }
                ////
            } catch (Exception e) {
                Log.e("Error", e.toString());
                e.printStackTrace();
            }
            imageView = ((ImageView)urls[0]);
            return mIcon11;
        }
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
            ImageCache.getInstance().addBitmapToCache(urldisplay, result);
        }
    }

    // QR비트 매트릭스를 비트맵 이미지로 생성
    public static Bitmap qrcodeMatrixToBitmap(BitMatrix matrix){
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                bmp.setPixel(x, y, matrix.get(x,y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bmp;
    }

    // 스트링이 널인지 값이 없는지 확인
    public static boolean isEnableString( String ... str){

        if( str == null) return false;

        for( String _str : str){
            if( _str == null || _str.trim().length() == 0){
                return false;
            }
        }

        return true;
    }
}
