package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CommonListResult;
import com.wenwoandroidnew.system.model.ModelGeocoding;
import com.wenwoandroidnew.system.model.ModelMagazineList;
import com.wenwoandroidnew.system.model.ModelMyInfo;
import com.wenwoandroidnew.system.model.query.ModelMagazineQuery;
import com.wenwoandroidnew.system.model.query.ModelQuestionQuery;
import com.wenwoandroidnew.system.util.AppSetting;
import com.wenwoandroidnew.system.util.UtilAPIControll;

import java.util.Hashtable;


public class DoMagazineList extends AsyncTask<ModelMagazineQuery, String , String>{ // extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(ModelMagazineQuery... params) {

        String url;
        Hashtable<String, String>  tableParam = new Hashtable<>();
        boolean isFirstStart = params[0].isFirstStart;

        if( AppSetting.MAGAZINE_CALL_TYPE.ALL == params[0].call_type  ){
            tableParam.put("aemail" ,"all");
            if(isFirstStart){
                tableParam.put("isFirstStart",Boolean.toString(isFirstStart));
            }
            url = UtilAPIControll.makeRESTURL(UtilAPIControll.API_MAGAZINE_LIST, tableParam);
            return UtilAPIControll.callServerData( url, UtilAPIControll.QUESTIONER_MAGAZINE_LIST_JSON);
        }
        else if(AppSetting.MAGAZINE_CALL_TYPE.ANSWERER == params[0].call_type){
            tableParam.put("aemail" ,params[0].aemail);
            if(isFirstStart){
                tableParam.put("isFirstStart",Boolean.toString(isFirstStart));
            }
            url = UtilAPIControll.makeRESTURL(UtilAPIControll.API_MAGAZINE_LIST, tableParam);
            return UtilAPIControll.callServerData( url, UtilAPIControll.QUESTIONER_MAGAZINE_LIST_JSON);
        }
        else if(AppSetting.MAGAZINE_CALL_TYPE.QUESTIONER == params[0].call_type){
//            tableParam.put("qemail" ,params[0].qemail);
//            url = UtilAPIControll.makeRESTURL(UtilAPIControll.API_MY_INFO,tableParam);
//            String myinfo = UtilAPIControll.callServerData(url, UtilAPIControll.MY_INFO_JSON);
//
//            Gson gson = new Gson();
//            ModelMyInfo result = gson.fromJson(myinfo, ModelMyInfo.class);
            if(isFirstStart){
                tableParam.put("isFirstStart",Boolean.toString(isFirstStart));
            }
            url = UtilAPIControll.makeRESTURL(UtilAPIControll.API_MAGAZINE_LIST, tableParam);
            return UtilAPIControll.callServerData( url, UtilAPIControll.QUESTIONER_MAGAZINE_LIST_JSON);
        }



        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if( s != null){
            CommonListResult<ModelMagazineList> pojo = new Gson().fromJson(s, new TypeToken<CommonListResult<ModelMagazineList>>() {}.getType());

            if( "200".equals( pojo.getCode()) == true){
                ModelMagazineList u = pojo.result;

                AppGlobalSetting.callResult.callResult( u);
            }
            else{
                AppGlobalSetting.callResult.callResult( null);
            }
        }
    }

}