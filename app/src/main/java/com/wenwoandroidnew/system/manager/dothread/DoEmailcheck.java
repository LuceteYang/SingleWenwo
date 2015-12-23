package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CommonListResult;
import com.wenwoandroidnew.system.model.JsonNULL;
import com.wenwoandroidnew.system.model.query.ModelNickNameCheckQuery;
import com.wenwoandroidnew.system.model.query.ModelemailQuery;
import com.wenwoandroidnew.system.util.UtilAPIControll;

import org.json.JSONException;
import org.json.JSONObject;


public class DoEmailcheck extends AsyncTask<ModelemailQuery, String , String>{ // extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(ModelemailQuery... params) {

        JSONObject obj = new JSONObject();

        String email = params[0].email;

        try {
            obj.put("qemail",  email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String postjson = obj.toString();
        Log.i("dd",postjson);

        String url = UtilAPIControll.makePostRESTURL(UtilAPIControll.API_EMAILCHECK);
        return UtilAPIControll.callPostServerData(url, postjson, UtilAPIControll.EMAIL_JSON);
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