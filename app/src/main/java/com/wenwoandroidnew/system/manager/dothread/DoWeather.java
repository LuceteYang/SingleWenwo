package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.model.ModelGeocoding;
import com.wenwoandroidnew.system.model.ModelWeather;
import com.wenwoandroidnew.system.model.query.ModelGeocodingQuery;
import com.wenwoandroidnew.system.util.UtilAPIControll;


public class DoWeather extends AsyncTask<ModelGeocodingQuery, String , String> { // extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(ModelGeocodingQuery... params) {
        String lon = Double.toString(params[0].longitude);
        String lat = Double.toString(params[0].latitude);

        String url = UtilAPIControll.makeGeocodingRESTURL(UtilAPIControll.WEATHER_URL, lat, lon);
        return UtilAPIControll.callGEOServerData(url, UtilAPIControll.WEATHER_JSON);
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            Gson gson = new Gson();
            ModelWeather result = gson.fromJson(s, ModelWeather.class);
            AppGlobalSetting.callResult.callResult(result);
        } else {
            AppGlobalSetting.callResult.callResult(null);
        }
    }
}