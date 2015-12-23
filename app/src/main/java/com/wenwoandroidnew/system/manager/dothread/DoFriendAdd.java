package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CommonListResult;
import com.wenwoandroidnew.system.model.ModelUser;
import com.wenwoandroidnew.system.model.query.ModelLoginQuery;
import com.wenwoandroidnew.system.util.UtilAPIControll;
import com.wenwoandroidnew.system.util.UtilCommon;

import org.json.JSONException;
import org.json.JSONObject;


public class DoFriendAdd extends AsyncTask< String, String , String>{ // extends AsyncTask<String, String, String> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        JSONObject obj = new JSONObject();
        try {
            obj.put("myfriend",  params[0]);
        } catch (JSONException e) {
            UtilCommon.showLog("DoFriendAdd.java" , e.getMessage());
        }

        String url = UtilAPIControll.makePostRESTURL(UtilAPIControll.API_FRIEND_ADD);
        return UtilAPIControll.callPostServerData(url, obj.toString(), UtilAPIControll.LOGIN_JSON);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if( s != null){

            CommonListResult<ModelUser> pojo = new Gson().fromJson(s, new TypeToken<CommonListResult<ModelUser>>() {}.getType());

            if( "200".equals( pojo.getCode()) == true){

                AppGlobalSetting.callResult.callResult( Boolean.TRUE);
            }
            else{
                //로그인 실패
                AppGlobalSetting.callResult.callResult(  Boolean.FALSE);
            }
        }
        else{
            //로그인 실패
            AppGlobalSetting.callResult.callResult( Boolean.FALSE);
        }
    }

}