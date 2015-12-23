package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CommonListResult;
import com.wenwoandroidnew.system.common.CommonResult;
import com.wenwoandroidnew.system.model.ModelMagazineList;
import com.wenwoandroidnew.system.model.ModelMyInfo;
import com.wenwoandroidnew.system.model.query.ModelMagazineQuery;
import com.wenwoandroidnew.system.util.UtilAPIControll;

import java.util.Hashtable;


public class DoMyInfo extends AsyncTask<ModelMagazineQuery, String , String>{ // extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(ModelMagazineQuery... params) {

        Hashtable<String, String>  tableParam = new Hashtable<>();
        tableParam.put("qemail", params[0].qemail);
        String url = UtilAPIControll.makeRESTURL(UtilAPIControll.API_MY_INFO,tableParam);
        return UtilAPIControll.callServerData(url, UtilAPIControll.MY_INFO_JSON);
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if( s != null){
            CommonListResult<ModelMyInfo> pojo = new Gson().fromJson(s, new TypeToken<CommonListResult<ModelMyInfo>>() {}.getType());

            if( "200".equals( pojo.getCode()) == true){
                ModelMyInfo u = pojo.result;

                AppGlobalSetting.callResultOnemore.CallResultOnemore(u);
            }
            else{
                AppGlobalSetting.callResultOnemore.CallResultOnemore( null);
            }
        }
    }

}