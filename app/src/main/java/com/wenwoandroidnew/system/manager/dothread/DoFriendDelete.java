package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CommonListResult;
import com.wenwoandroidnew.system.model.JsonNULL;
import com.wenwoandroidnew.system.model.ModelUser;
import com.wenwoandroidnew.system.model.query.ModelFriendDeleteQuery;
import com.wenwoandroidnew.system.model.query.ModelLoginQuery;
import com.wenwoandroidnew.system.util.UtilAPIControll;

import org.json.JSONException;
import org.json.JSONObject;


public class DoFriendDelete extends AsyncTask<ModelFriendDeleteQuery, String , String>{ // extends AsyncTask<String, String, String> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(ModelFriendDeleteQuery... params) {

        JSONObject obj = new JSONObject();
        try {
            obj.put("me",  params[0].me);
            obj.put("myfriend", params[0].myfriend);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String postjson = obj.toString();

        String url = UtilAPIControll.makePostRESTURL(UtilAPIControll.API_FRIEND_DELETE);
        return UtilAPIControll.callPostServerData(url, postjson, UtilAPIControll.FRIEND_DELETE);
    }

    @Override
    protected void onPostExecute(String s) {
        Log.i("dd",s);
        super.onPostExecute(s);
        if( s != null){
            CommonListResult<JsonNULL> pojo = new Gson().fromJson(s, new TypeToken<CommonListResult<JsonNULL>>() {}.getType());

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