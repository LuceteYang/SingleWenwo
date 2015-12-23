package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CommonListResult;
import com.wenwoandroidnew.system.model.ModelAnswererInfo;
import com.wenwoandroidnew.system.model.ModelMyInfo;
import com.wenwoandroidnew.system.model.query.ModelMagazineQuery;
import com.wenwoandroidnew.system.model.query.ModelemailQuery;
import com.wenwoandroidnew.system.util.UtilAPIControll;

import java.util.Hashtable;


public class DoAnswererInfo extends AsyncTask<ModelemailQuery, String , String>{ // extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(ModelemailQuery... params) {

        Hashtable<String, String>  tableParam = new Hashtable<>();
        tableParam.put("aemail", params[0].email);
        String url = UtilAPIControll.makeRESTURL(UtilAPIControll.API_ANSWERER_INFO,tableParam);
        return UtilAPIControll.callServerData(url, UtilAPIControll.ANSWERER_INFO_JSON);
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if( s != null){
            CommonListResult<ModelAnswererInfo> pojo = new Gson().fromJson(s, new TypeToken<CommonListResult<ModelAnswererInfo>>() {}.getType());

            if( "200".equals( pojo.getCode()) == true){
                ModelAnswererInfo u = pojo.result;

                AppGlobalSetting.callResult.callResult(u);
            }
            else{
                AppGlobalSetting.callResult.callResult( null);
            }
        }
    }

}