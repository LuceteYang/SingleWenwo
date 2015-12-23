package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CommonListResult;
import com.wenwoandroidnew.system.model.JsonNULL;
import com.wenwoandroidnew.system.model.query.ModelAnswerPickQuery;
import com.wenwoandroidnew.system.model.query.ModelNickNameCheckQuery;
import com.wenwoandroidnew.system.util.UtilAPIControll;

import org.json.JSONException;
import org.json.JSONObject;


public class DoAnswerPick extends AsyncTask<ModelAnswerPickQuery, String , String>{ // extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(ModelAnswerPickQuery... params) {

        JSONObject obj = new JSONObject();

        String qOra = params[0].anum;

        try {
            obj.put("anum",  qOra);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String postjson = obj.toString();

        String url = UtilAPIControll.makePostRESTURL(UtilAPIControll.API_ANSWER_PICK);
        //
        return UtilAPIControll.callPostServerData(url, postjson, UtilAPIControll.ANSWER_PICK_JSON);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if( s != null){
            CommonListResult<JsonNULL> pojo = new Gson().fromJson(s, new TypeToken<CommonListResult<JsonNULL>>() {
            }.getType());

            if( "200".equals( pojo.getCode()) == true){
                AppGlobalSetting.callResultOnemore.CallResultOnemore(Boolean.TRUE);
            }
            else{
                //로그인 실패
                AppGlobalSetting.callResultOnemore.CallResultOnemore( Boolean.FALSE);
            }
        }
    }

}