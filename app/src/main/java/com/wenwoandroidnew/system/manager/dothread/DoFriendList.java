package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CommonListResult;
import com.wenwoandroidnew.system.model.ModelFriendList;
import com.wenwoandroidnew.system.util.UtilAPIControll;

import java.util.Hashtable;

/**
 * Created by SeungJin on 2015-11-16.
 */
public class DoFriendList extends AsyncTask<String, String, String> { // extends AsyncTask<String, String, String> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        Hashtable<String, String> tableParam = new Hashtable<>();
        //tableParam.put("qemail", params[0]);

        String url = UtilAPIControll.makeRESTURL(UtilAPIControll.API_FRIEND_LIST, tableParam);
        return UtilAPIControll.callServerData(url, UtilAPIControll.FRIEND_LIST_JSON);
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("ddd",s);
        super.onPostExecute(s);
        if (s != null) {

            CommonListResult<ModelFriendList> pojo = new Gson().fromJson(s, new TypeToken<CommonListResult<ModelFriendList>>() {
            }.getType());

            if ("200".equals(pojo.getCode()) == true) {

                AppGlobalSetting.callResult.callResult(pojo.getResult());
            } else {
                //로그인 실패
                AppGlobalSetting.callResult.callResult(null);
            }
        }
    }

}