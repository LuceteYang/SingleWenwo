package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CommonListResult;
import com.wenwoandroidnew.system.model.query.ModelBestAnswerQuery;
import com.wenwoandroidnew.system.model.ModelQuestionList;
import com.wenwoandroidnew.system.util.UtilAPIControll;

import java.util.Hashtable;


public class DoBestAnswerList extends AsyncTask<ModelBestAnswerQuery, String , String>{ // extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(ModelBestAnswerQuery... params) {
        Hashtable<String, String>  tableParam = new Hashtable<>();

        tableParam.put("aemail" ,params[0].aemail);

        String url = UtilAPIControll.makeRESTURL(UtilAPIControll.API_BESTANSWER_LIST, tableParam);
        return UtilAPIControll.callServerData( url, UtilAPIControll.BEST_ANSWER_LIST_JSON);
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i("dobestAnswer", s);
        if( s != null){
            CommonListResult<ModelQuestionList> pojo = new Gson().fromJson(s, new TypeToken<CommonListResult<ModelQuestionList>>() {}.getType());

            if( "200".equals( pojo.getCode()) == true){
                ModelQuestionList u = pojo.result;

                AppGlobalSetting.callResult.callResult( u);
            }
            else{
                AppGlobalSetting.callResult.callResult( null);
            }
        }
    }

}