package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CommonListResult;
import com.wenwoandroidnew.system.common.CommonResult;
import com.wenwoandroidnew.system.model.query.ModelNickNameCheckQuery;
import com.wenwoandroidnew.system.util.UtilAPIControll;
import com.wenwoandroidnew.system.model.JsonNULL;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;


public class DoNicknamecheck extends AsyncTask<ModelNickNameCheckQuery, String , String>{ // extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(ModelNickNameCheckQuery... params) {

        JSONObject obj = new JSONObject();

        String nickname = params[0].nickname;

        try {
            obj.put("nickname",  nickname);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String postjson = obj.toString();
        Log.i("dd",postjson);

        String url = UtilAPIControll.makePostRESTURL(UtilAPIControll.API_NICKNAMECHK);
        return UtilAPIControll.callPostServerData(url, postjson, UtilAPIControll.NICKNAME_JSON);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if( s != null){
            CommonListResult<JsonNULL> pojo = new Gson().fromJson(s, new TypeToken<CommonListResult<JsonNULL>>() {
            }.getType());

            if( "200".equals( pojo.getCode()) == true){
                AppGlobalSetting.callResult.callResult( Boolean.TRUE);
            }
            else{
                //로그인 실패
                AppGlobalSetting.callResult.callResult( Boolean.FALSE);
            }
        }
    }

}