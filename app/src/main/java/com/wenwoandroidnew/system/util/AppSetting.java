package com.wenwoandroidnew.system.util;

/**
 * Created by SeungJin on 2015-11-13.
 */
public class AppSetting {

    public final static int RECORD_TIME = 30;
    public final static String SK_DEVELOPMENT_KEY = "5bd485bf-8e57-34ad-85e4-adbcf365c188";
    public final static String CURRENCY_KEY ="0ad9c781fd8f495480597a5c1e0760e8";
    public static boolean NET_TYPE = true; // false:local , true:network
    public static boolean LOG_TYPE = true; // false: 로그 안찍기

    public static boolean SHOW_JSON_RESULT = true; // true:로그출력 ,false:출력안함
    public enum FEED_CALL_TYPE { ALL, MY , My_WAIT, MY_YET, MY_ACCEPT }

    public static String QUESTION_TYPE_TEXT = "0";
    public static String QUESTION_TYPE_PICTURE = "1";
    public static String QUESTION_TYPE_VOICE = "2";


    public static String QUESTION_TYPE_TEXT_TITLE = "Text Question";
    public static String QUESTION_TYPE_PICTURE_TITLE = "Picture Question";
    public static String QUESTION_TYPE_VOICE_TITLE = "Voice Question";

    public enum MAGAZINE_CALL_TYPE {ALL, ANSWERER, QUESTIONER}


}
