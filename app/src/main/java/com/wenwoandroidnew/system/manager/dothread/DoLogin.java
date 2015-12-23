package com.wenwoandroidnew.system.manager.dothread;

import android.os.AsyncTask;
import android.provider.Settings;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CommonListResult;
import com.wenwoandroidnew.system.model.query.ModelLoginQuery;
import com.wenwoandroidnew.system.model.ModelUser;
import com.wenwoandroidnew.system.util.UtilAPIControll;

import org.json.JSONException;
import org.json.JSONObject;


public class DoLogin extends AsyncTask<ModelLoginQuery, String , String>{ // extends AsyncTask<String, String, String> {

    String regId = "";
    String password = "";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(ModelLoginQuery... params) {

        JSONObject obj = new JSONObject();
        String deviceId = Settings.Secure.getString(AppGlobalSetting.context.getContentResolver(), Settings.Secure.ANDROID_ID);
        try {
            obj.put("qemail",  params[0].email);
            obj.put("pw", params[0].passworrd);
            obj.put("token",params[0].registerationId);
            obj.put("deviceID",deviceId);
            regId = params[0].registerationId;
            password = params[0].passworrd;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String postjson = obj.toString();
        String url = UtilAPIControll.makePostRESTURL(UtilAPIControll.API_LOGIN);
        return UtilAPIControll.callPostServerData(url, postjson, UtilAPIControll.LOGIN_JSON);
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        if( s != null){
            CommonListResult<ModelUser> pojo = new Gson().fromJson(s, new TypeToken<CommonListResult<ModelUser>>() {}.getType());

            if( "200".equals( pojo.getCode()) == true) {

                pojo.getResult().setPassword(password);
                AppGlobalSetting.callResult.callResult(pojo.getResult());
            }
            else{
                AppGlobalSetting.callResult.callResult(null);
            }
                /*LocalLoginUser modelUser = new LocalLoginUser();
                if(     pojo.getResult().getProfileImage().size()>0
                        && pojo.getResult().getProfileImage().get(0).getOriginalPath()!=null
                        && (pojo.getResult().getProfileImage().get(0).getOriginalPath().trim().length()>0)){

                    modelUser.setProfileImage(pojo.getResult().getProfileImage().get(0).getOriginalPath());
                    modelUser.setThProfileImage(pojo.getResult().getProfileImage().get(0).getTh_path());
                }
                else{
                    modelUser.setProfileImage("");
                    modelUser.setThProfileImage("");
                }
                modelUser.setName(pojo.getResult().getNickname());
                modelUser.setEmail(pojo.getResult().getQemail());
                modelUser.setPassword( password); // 로그인 성공시 패스워드 저장
                modelUser.setLastLoginDate(UtilCommon.now());
                DataManager.getInstance().insertLoginCheck(modelUser);
                AppGlobalSetting.myProfileImage = AppGlobalSetting.context.getResources().getDrawable(R.drawable.boyoung);
                AppGlobalSetting.myThProfileImage = AppGlobalSetting.context.getResources().getDrawable(R.drawable.boyoung_th);
                try {
                    if((pojo.getResult().getProfileImage().get(0).getOriginalPath()!=null
                            && (pojo.getResult().getProfileImage().get(0).getOriginalPath().trim().length()>0))) {
                        AppGlobalSetting.myProfileImage = UtilCommon.urlToDrawableProfileImage(modelUser.getProfileImage());
                        AppGlobalSetting.myThProfileImage = UtilCommon.urlToDrawableProfileImage(modelUser.getThProfileImage());
                    }
                } catch (Exception e) {
                    Log.d("doLogin",e.toString());
                }
                AppGlobalSetting.setLocalLoginUser(modelUser);
                AppGlobalSetting.callResult.callResult( pojo.getResult());*/

        }
    }

}