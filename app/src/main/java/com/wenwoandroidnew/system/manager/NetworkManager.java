package com.wenwoandroidnew.system.manager;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.wenwoandroidnew.system.manager.dothread.DoAnswerList;
import com.wenwoandroidnew.system.manager.dothread.DoAnswerPick;
import com.wenwoandroidnew.system.manager.dothread.DoAnswererInfo;
import com.wenwoandroidnew.system.manager.dothread.DoCurrency;
import com.wenwoandroidnew.system.manager.dothread.DoEmailcheck;
import com.wenwoandroidnew.system.manager.dothread.DoFriendAdd;
import com.wenwoandroidnew.system.manager.dothread.DoFriendDelete;
import com.wenwoandroidnew.system.manager.dothread.DoFriendQuestion;
import com.wenwoandroidnew.system.manager.dothread.DoFriendList;
import com.wenwoandroidnew.system.manager.dothread.DoBestAnswerList;
import com.wenwoandroidnew.system.manager.dothread.DoGeocoding;
import com.wenwoandroidnew.system.manager.dothread.DoMagazineList;
import com.wenwoandroidnew.system.manager.dothread.DoMyInfo;
import com.wenwoandroidnew.system.manager.dothread.DoQuestionList;
import com.wenwoandroidnew.system.manager.dothread.DoJoin;
import com.wenwoandroidnew.system.manager.dothread.DoLogin;
import com.wenwoandroidnew.system.manager.dothread.DoNicknamecheck;
import com.wenwoandroidnew.system.manager.dothread.DoQuestionRegister;
import com.wenwoandroidnew.system.manager.dothread.DoWeather;
import com.wenwoandroidnew.system.model.JsonNULL;
import com.wenwoandroidnew.system.model.query.ModelAnswerPickQuery;
import com.wenwoandroidnew.system.model.query.ModelFriendDeleteQuery;
import com.wenwoandroidnew.system.model.query.ModelNickNameCheckQuery;
import com.wenwoandroidnew.system.model.query.ModelQuestionRegisterQuery;
import com.wenwoandroidnew.system.model.query.ModelLoginQuery;
import com.wenwoandroidnew.system.model.query.ModelBestAnswerQuery;
import com.wenwoandroidnew.system.model.query.ModelGeocodingQuery;
import com.wenwoandroidnew.system.model.query.ModelQuestionQuery;
import com.wenwoandroidnew.system.model.query.ModelMagazineQuery;
import com.wenwoandroidnew.system.model.query.ModelJoinQuery;
import com.wenwoandroidnew.system.model.query.ModelemailQuery;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ThreadPoolExecutor;

public class NetworkManager {

    private static NetworkManager networkManager = null;
    private NetworkManager() {}

    public static NetworkManager getInstance() {
        if (networkManager == null) {
            networkManager = new NetworkManager();
        }
        return networkManager;
    }

    ThreadPoolExecutor mExecutor;
    Handler mHandler = new Handler(Looper.getMainLooper());

    public void doLogin( ModelLoginQuery query) {
        new DoLogin().execute( query);
    }

    public void doJoin( ModelJoinQuery ModelJoinQuery){
        new DoJoin().execute(ModelJoinQuery);
    }

    public void doNickNameCheck(ModelNickNameCheckQuery nickname){
        new DoNicknamecheck().execute(nickname);
    }

    public  void doEmailCheck(ModelemailQuery email){
        new DoEmailcheck().execute(email);
    }

    public void doQuestionList( ModelQuestionQuery query){
        new DoQuestionList().execute( query);
    }

    public void doAnswerPick(ModelAnswerPickQuery query){
        new DoAnswerPick().execute(query);
    }

    public void doAnswerList( int qnum, int pick){
        new DoAnswerList().execute(qnum,pick);
    }

    public void doGeocoding(ModelGeocodingQuery query1){
        new DoGeocoding().execute(query1);
    }

    public void doWeather(ModelGeocodingQuery query){
        new DoWeather().execute(query);
    }

    public void doCurrency(JsonNULL model){new DoCurrency().execute(model);}

    public void doMagazineList(ModelMagazineQuery query2){
        new DoMagazineList().execute(query2);
    }

    public void doMyInfo(ModelMagazineQuery query3){
        new DoMyInfo().execute(query3);
    }

    public void doBestAnswerList(ModelBestAnswerQuery query4){
        new DoBestAnswerList().execute(query4);
    }

    public void doFriendDelete(ModelFriendDeleteQuery query5){
        new DoFriendDelete().execute(query5);
    }

    public void doFriendList( String email){ new DoFriendList().execute( email);}

    public void doFriendQuestion( ModelemailQuery emailQuery){ new DoFriendQuestion().execute( emailQuery);}



    public void doQuestionRegister( ModelQuestionRegisterQuery register){
        new DoQuestionRegister().execute( register);
    }

    public void doFriendAdd( String friendEmail){
        new DoFriendAdd().execute( friendEmail);
    }

    public void doAnswererInfo( ModelemailQuery email){
        new DoAnswererInfo().execute( email);
    }





}

