package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CommonListResult;

import com.wenwoandroidnew.system.model.JsonNULL;
import com.wenwoandroidnew.system.model.query.ModelJoinQuery;
import com.wenwoandroidnew.system.util.UtilAPIControll;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;


public class DoJoin extends AsyncTask<ModelJoinQuery, String , String>{ // extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(ModelJoinQuery... params) {

        Hashtable<String, String> tableParam = new Hashtable<>();
        tableParam.put(params[0].getType() , params[0].getQemail());
        tableParam.put("birthDate", params[0].getBirthDate());
        tableParam.put("nickname", params[0].getNickname());
        tableParam.put("sex", params[0].getSex());
        tableParam.put("pw", params[0].getPw());

        String url = UtilAPIControll.makePostRESTURL(UtilAPIControll.API_JOIN);

        return UtilAPIControll.callPostFileServerData(url, tableParam , params[0].getProfileImage() , null , UtilAPIControll.JOIN_JSON);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if( s != null){
            CommonListResult<JsonNULL> pojo = new Gson().fromJson(s, new TypeToken<CommonListResult<JsonNULL>>() {}.getType());

            if( "200".equals( pojo.getCode()) == true){

                AppGlobalSetting.callResult.callResult( Boolean.TRUE);
            }
            else{

                AppGlobalSetting.callResult.callResult( Boolean.FALSE);
            }
        }
    }

}