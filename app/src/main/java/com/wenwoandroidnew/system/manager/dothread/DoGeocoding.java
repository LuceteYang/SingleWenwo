package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.wenwoandroidnew.system.model.ModelGeocoding;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.model.query.ModelGeocodingQuery;
import com.wenwoandroidnew.system.util.UtilAPIControll;


public class DoGeocoding extends AsyncTask<ModelGeocodingQuery, String , String> { // extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(ModelGeocodingQuery... params) {
        double latitude = params[0].latitude;
        double longitude = params[0].longitude;

        //String url = UtilAPIControll.makeGeocodingRESTURL(UtilAPIControll.GEOCODDING_URL, latitude, longitude);
        //return UtilAPIControll.callGEOServerData(url, UtilAPIControll.GEOCODING_JSON);
        //String juso = UtilAPIControll.callGEOServerData(url, UtilAPIControll.GEOCODING_JSON);
        return "";
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            Gson gson = new Gson();
            ModelGeocoding result = gson.fromJson(s, ModelGeocoding.class);
            AppGlobalSetting.callResultOnemore.CallResultOnemore(result);
        } else {
            AppGlobalSetting.callResultOnemore.CallResultOnemore(null);
        }
    }
}