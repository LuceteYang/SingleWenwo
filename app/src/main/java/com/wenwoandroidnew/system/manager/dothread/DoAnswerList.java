package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CommonListResult;
import com.wenwoandroidnew.system.model.ModelAnswerList;
import com.wenwoandroidnew.system.util.UtilAPIControll;

import java.util.Hashtable;


public class DoAnswerList extends AsyncTask<Integer, String , String>{ // extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(Integer... params) {

        Hashtable<String, String>  tableParam = new Hashtable<>();

        tableParam.put("qnum" ,String.valueOf(params[0]));
        tableParam.put("pick", String.valueOf(params[1]));

        String url = UtilAPIControll.makeRESTURL(UtilAPIControll.API_ANSWER_LIST, tableParam);
        return UtilAPIControll.callServerData( url, UtilAPIControll.ANSWER_LIST_JSON);
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if( s != null){
            CommonListResult<ModelAnswerList> pojo = new Gson().fromJson(s, new TypeToken<CommonListResult<ModelAnswerList>>() {}.getType());

            if( "200".equals( pojo.getCode()) == true){
                ModelAnswerList u = pojo.result;

                AppGlobalSetting.callResult.callResult( u);
            }
            else{
                AppGlobalSetting.callResult.callResult( null);
            }
        }
    }

}