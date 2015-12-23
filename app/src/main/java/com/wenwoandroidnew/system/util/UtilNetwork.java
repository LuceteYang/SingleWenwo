package com.wenwoandroidnew.system.util;

import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.wenwoandroidnew.system.AppGlobalSetting;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.net.ssl.SSLContext;

/**
 * Created by SeungJin on 2015-11-05.
 */
public class UtilNetwork {

    private static OkHttpClient client = new OkHttpClient();
    private static CookieManager cookieManager = new CookieManager();

//public static String callGetAPI(String urlData) {
//    try {
//        URL url = new URL(urlData);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestProperty("Accept", "application/json");
//
//        int code = conn.getResponseCode();
//        if (code == HttpURLConnection.HTTP_OK) {
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while ((line = br.readLine()) != null) {
//                sb.append(line).append("\n\r");
//            }
//            return sb.toString();
//        }
//    } catch (IOException e) {
//        return "{\"code\":404,\"msg\":\"\"}";
//    }
//    return "{\"code\":404,\"msg\":\"\"}";
//}

    private static OkHttpClient getClientWithCookie( OkHttpClient client){
        CookieHandler.setDefault(cookieManager);
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        return client.setCookieHandler(cookieManager);
    }

    public static String callGEOGetAPI(String urlData){
            try {
                URL url = new URL(urlData);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                if( AppSetting.LOG_TYPE == true){
                    Log.d("#############" , "####################");
                    Log.d( "callGEOGetAPI" , "URL: " + urlData);
                    Log.d("#############" , "####################");
                }

                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("appKey", AppSetting.SK_DEVELOPMENT_KEY);

                int code = conn.getResponseCode();
                if (code == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n\r");
                    }
                    String result =  sb.toString();
                    if( AppSetting.LOG_TYPE == true){
                        Log.d("#############" , "####################");
                        Log.d( "callGEOGetAPI" , "result: " + result);
                        Log.d("#############" , "####################");
                    }
                    return result;
                }
            } catch (IOException e) {
                return "{\"code\":404,\"msg\":\"\"}";
            }
            return "{\"code\":404,\"msg\":\"\"}";
    }

    public static String callCurrencyGetAPI(String urlData){
        try {
            URL url = new URL(urlData);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if( AppSetting.LOG_TYPE == true){
                Log.d("#############" , "####################");
                Log.d( "callCurrencyGetAPI" , "URL: " + urlData);
                Log.d("#############" , "####################");
            }

            int code = conn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n\r");
                }
                String result =  sb.toString();
                if( AppSetting.LOG_TYPE == true){
                    Log.d("#############" , "####################");
                    Log.d( "callCurrencyGetAPI" , "result: " + "result");
                    Log.d("#############" , "####################");
                }
                return result;
            }
        } catch (IOException e) {
            return "{\"code\":404,\"msg\":\"\"}";
        }
        return "{\"code\":404,\"msg\":\"\"}";
    }


    public static String callGetAPI( String url) throws Exception {

        //OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = getClientWithCookie( client).newCall(request).execute();
        if( AppSetting.LOG_TYPE == true){
            Log.d("#############" , "####################");
            Log.d( "callGetAPI" , "URL: " + url);
            Log.d("#############" , "####################");
        }
        String result =  response.body().string();
        if( AppSetting.LOG_TYPE == true){
            Log.d("#############" , "####################");
            Log.d( "callGetAPI" , "RESULT: " + result);
            Log.d("#############" , "####################");
        }
        return result;
    }


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    public static String callPostAPI(String url, String json) throws Exception {

        //OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, json);

//        if( url.endsWith( UtilAPIControll.API_LOGIN)){
            Log.d("쿠키처리", "로그인시 쿠키처리");

//            CookieManager cookieManager = new CookieManager();


//        }
        Request request = new Request.Builder().url(url).post(body).build();

        if( AppSetting.LOG_TYPE == true){
            Log.d("#############" , "####################");
            Log.d( "callPostAPI" , "URL: " + url);
            Log.d("#############" , "####################");
        }
        Response response = getClientWithCookie( client).newCall(request).execute();
        String result =  response.body().string();
        if( AppSetting.LOG_TYPE == true){
            Log.d("#############" , "####################");
            Log.d( "callPostAPI" , "RESULT: " + result);
            Log.d("#############" , "####################");
        }
        return result;
    }

    public static String callPostUploadFileAPI(String url, Hashtable<String, String> data, List<String> fileList , String voidPath) throws IOException {

        //OkHttpClient client = new OkHttpClient();

        MultipartBuilder body = new MultipartBuilder()
                .type(MultipartBuilder.FORM);
        RequestBody requestBody;

        Enumeration<String> keys = data.keys();

        while ( keys.hasMoreElements()){
            String key = keys.nextElement();
            body.addFormDataPart( key, data.get(key));

        }

        // 이미지 저장
        if( fileList != null){
            for( String fileName : fileList) {
                File file = new File(fileName);
                body.addFormDataPart("image"
                        , file.getName()
                        , RequestBody.create(MediaType.parse("image/png"), file));
            }
        }

        // 음성파일 저장
        if( voidPath != null && voidPath.trim().length() != 0){
            File file = new File(voidPath);
            body.addFormDataPart("recording"
                    , file.getName()
                    , RequestBody.create(MediaType.parse("audio/mp3"), voidPath));
        }


        requestBody = body.build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID ...")
                .url(url)
                .post(requestBody)
                .build();
        if( AppSetting.LOG_TYPE == true){
            Log.d("#############" , "####################");
            Log.d( "callPostAPI" , "URL: " + url);
            Log.d("#############" , "####################");
        }
        Response response = getClientWithCookie( client).newCall(request).execute();
        String result =  response.body().string();
        if( AppSetting.LOG_TYPE == true){
            Log.d("#############" , "####################");
            Log.d( "callGEOGetAPI" , "RESULT: " + result);
            Log.d("#############" , "####################");
        }
        return result;
    }




}
