package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.model.JsonNULL;
import com.wenwoandroidnew.system.model.ModelCurrency;
import com.wenwoandroidnew.system.util.AppSetting;
import com.wenwoandroidnew.system.util.UtilAPIControll;


public class DoCurrency extends AsyncTask<JsonNULL, String , String> { // extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(JsonNULL... params) {

        String url = UtilAPIControll.makeCurrencyRESTURL(UtilAPIControll.CURRENCY_URL, AppSetting.CURRENCY_KEY);
        return UtilAPIControll.callCurrencyServerData(url, UtilAPIControll.CURRENCY_JSON);
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            Gson gson = new Gson();
            ModelCurrency result = gson.fromJson(s, ModelCurrency.class);
            AppGlobalSetting.callResult.callResult(result);
        } else {
            AppGlobalSetting.callResult.callResult(null);
        }
    }
}