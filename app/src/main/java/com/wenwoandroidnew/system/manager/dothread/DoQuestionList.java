package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wenwoandroidnew.system.common.CommonListResult;
import com.wenwoandroidnew.system.model.ModelGeocoding;
import com.wenwoandroidnew.system.model.ModelQuestionList;
import com.wenwoandroidnew.system.model.query.ModelQuestionQuery;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.util.AppSetting;
import com.wenwoandroidnew.system.util.UtilAPIControll;

import java.util.Hashtable;


public class DoQuestionList extends AsyncTask<ModelQuestionQuery, String , String>{ // extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(ModelQuestionQuery... params) {

        String latitude = params[0].a;
        String longitude = params[0].b;

        String url = UtilAPIControll.makeGeocodingRESTURL(UtilAPIControll.GEOCODDING_URL, latitude, longitude);
        String juso = UtilAPIControll.callGEOServerData(url, UtilAPIControll.GEOCODING_JSON);
        boolean isFirstStart = params[0].isFirstStart;

        Gson gson = new Gson();
        ModelGeocoding result = gson.fromJson(juso, ModelGeocoding.class);

        Hashtable<String, String>  tableParam = new Hashtable<>();

        // 전체 질문 목록
        if( AppSetting.FEED_CALL_TYPE.ALL == params[0].call_type  ){
//            tableParam.put("si","대전광역시");
//            tableParam.put("gu","유성규");
//            tableParam.put("dong","귱동");
            if(isFirstStart){
                tableParam.put("isFirstStart",Boolean.toString(isFirstStart));
            }
            url = UtilAPIControll.makeRESTURL(UtilAPIControll.API_ALL_QUESTION_LIST, tableParam);
            return UtilAPIControll.callServerData( url, UtilAPIControll.ALLFEED_QUESTION_LIST_JSON);
        }
        // 나의 모든 질문 목록
        else if(AppSetting.FEED_CALL_TYPE.MY == params[0].call_type){
            tableParam.put("status" ,params[0].status);
            if(isFirstStart){
                tableParam.put("isFirstStart",Boolean.toString(isFirstStart));
            }
            url = UtilAPIControll.makeRESTURL(UtilAPIControll.API_MY_QUESTION_LIST, tableParam);
            return UtilAPIControll.callServerData( url, UtilAPIControll.MYFEED_QUESTION_LIST_JSON);
        }
        // 나의 대기 목록
        else if(AppSetting.FEED_CALL_TYPE.My_WAIT == params[0].call_type){
            tableParam.put("status" ,params[0].status);
            if(isFirstStart){
                tableParam.put("isFirstStart",Boolean.toString(isFirstStart));
            }
            url = UtilAPIControll.makeRESTURL(UtilAPIControll.API_MY_QUESTION_LIST, tableParam);
            return UtilAPIControll.callServerData( url, UtilAPIControll.MYFEED_WAIT_QUESTION_LIST_JSON);
        }
        // 나의 마감 목록
        else if(AppSetting.FEED_CALL_TYPE.MY_YET == params[0].call_type){
            tableParam.put("status" ,params[0].status);
            if(isFirstStart){
                tableParam.put("isFirstStart",Boolean.toString(isFirstStart));
            }
            url = UtilAPIControll.makeRESTURL(UtilAPIControll.API_MY_QUESTION_LIST, tableParam);
            return UtilAPIControll.callServerData( url, UtilAPIControll.MYFEED_YET_QUESTION_LIST_JSON);
        }
        // 나의 채택 목록
        else if(AppSetting.FEED_CALL_TYPE.MY_ACCEPT == params[0].call_type){
            tableParam.put("status" ,params[0].status);
            if(isFirstStart){
                tableParam.put("isFirstStart",Boolean.toString(isFirstStart));
            }
            url = UtilAPIControll.makeRESTURL(UtilAPIControll.API_MY_QUESTION_LIST, tableParam);
            return UtilAPIControll.callServerData( url, UtilAPIControll.MYFEED_ACCEPT_QUESTION_LIST_JSON);
        }


        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if( s != null){
            CommonListResult<ModelQuestionList> pojo = new Gson().fromJson(s, new TypeToken<CommonListResult<ModelQuestionList>>() {}.getType());

            if( "200".equals( pojo.getCode()) == true){
                ModelQuestionList u = pojo.result;
                AppGlobalSetting.callResult.callResult( u);
            }
            else{
                AppGlobalSetting.callResult.callResult( null);
            }
        }
    }

}