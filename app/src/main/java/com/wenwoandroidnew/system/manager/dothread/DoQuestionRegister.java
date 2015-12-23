package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wenwoandroidnew.system.model.query.ModelQuestionRegisterQuery;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CommonListResult;
import com.wenwoandroidnew.system.model.JsonNULL;
import com.wenwoandroidnew.system.util.UtilAPIControll;

import java.util.Hashtable;


public class DoQuestionRegister extends AsyncTask<ModelQuestionRegisterQuery, String , String>{ // extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(ModelQuestionRegisterQuery... params) {


        Hashtable<String, String> obj = new Hashtable<>();
//        obj.put("qemail",  params[0].getQemail());
        obj.put("title", params[0].getTitle());
        obj.put("category",  params[0].getCategory());
        obj.put("text", params[0].getText());
        if(params[0].getSi()!=null){
            obj.put("si", params[0].getSi());
            obj.put("gu", params[0].getGu());
            obj.put("dong", params[0].getDong());
        }
        obj.put("dueTime", params[0].getDueTile());
        obj.put("open", params[0].getOpen());
        obj.put("type" , params[0].getType());
        obj.put("spentSeed",params[0].getSpentSeed());
        Log.d("dd",obj.toString());

        return UtilAPIControll.callPostFileServerData(
                        UtilAPIControll.makePostRESTURL(UtilAPIControll.API_QUESTION),
                        obj,
                        params[0].getImage(),
                        params[0].getRecording() ,
                        UtilAPIControll.QUESTION_REGISTER_JSON);
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

                AppGlobalSetting.callResult.callResult( Boolean.FALSE);
            }
        }
    }

}