package com.wenwoandroidnew.system;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.google.zxing.WriterException;
import com.wenwoandroidnew.qrcode.QRCodeController;
import com.wenwoandroidnew.system.common.CallResultOnemore;
import com.wenwoandroidnew.system.model.LocalLoginUser;
import com.wenwoandroidnew.system.common.CallResult;

import java.io.IOException;
import java.net.CookieManager;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by SeungJin on 2015-10-29.
 */
public class AppGlobalSetting {

    // HomeActivity
    public static Context context;

    // 현재 Activity
    public static CallResult callResult;

    public static CookieManager cookieManager;
    //임시 한번더
    public static CallResultOnemore callResultOnemore;

    public static Drawable myProfileImage;
    public static Drawable myThProfileImage;
    public static Drawable myEmailQRCode;
    public static String answerImage;
    public static String answerName;

    // 로그인 사용자 로컬객체
    private static LocalLoginUser localLoginUser = null;

    // 로그인 사용자 확인
    public static boolean isLocalLogin(){
        if ( localLoginUser == null){
            return false;
        }
        else{
            return true;
        }
    }


    public static Drawable getQREmailImage() throws WriterException {
        if( isLocalLogin() == false){
            return null;
        }
        return new BitmapDrawable( new QRCodeController().createQRCode( localLoginUser.getEmail()));
    }
    //로그인 사용자 설정
    public static void setLocalLoginUser( LocalLoginUser _localLoginUser){
        localLoginUser = _localLoginUser;

    }

    // 로그인 사용자 획득
    public static LocalLoginUser getLocalLoginUser(){
        return localLoginUser;
    }

    //로그인 사용자 아웃처리
    public static void localLogout(){
        localLoginUser = null;
    }

    public static String WRITER_EMAIL = "answer5@naver.com";
}
