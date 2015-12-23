package com.wenwoandroidnew.system.module;


import android.util.Log;

import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.manager.DataManager;
import com.wenwoandroidnew.system.model.LocalLoginUser;
import com.wenwoandroidnew.system.manager.NetworkManager;
import com.wenwoandroidnew.system.model.query.ModelJoinQuery;
import com.wenwoandroidnew.system.model.query.ModelLoginQuery;
import com.wenwoandroidnew.system.model.query.ModelNickNameCheckQuery;
import com.wenwoandroidnew.system.model.query.ModelemailQuery;

/**
 * Created by SeungJin on 2015-10-29.
 */
public class ModuleUser {

    //  로그인 여부 확인
    public static LocalLoginUser doLocalLoginStatus(){
        LocalLoginUser localLoginUser = DataManager.getInstance().getLoginUser();
        AppGlobalSetting.setLocalLoginUser(localLoginUser);
        return localLoginUser;
    }

    // 로그인처리
    public static void login( CallResult callResult,  ModelLoginQuery query){

        // 이벤트 처리 결과를 받을( 반드시 세팅해야 함)
        AppGlobalSetting.callResult = callResult;

        NetworkManager.getInstance().doLogin(query);
    }

    // 로그아웃처리
    public static void logout( ){

/*        LocalLoginUser localLoginUser = AppGlobalSetting.getLocalLoginUser();
        if( localLoginUser != null){
            localLoginUser.setbLogin(false); // 로그실패값 입력
        }*/
        DataManager.getInstance().updateLoginCheck(); // DB에 업데이트 처리
        AppGlobalSetting.localLogout(); // 전역값 처리
    }

    // 회원가입
    public static void join( CallResult callResult,  ModelJoinQuery ModelJoinQuery){

        // 이벤트 처리 결과를 받을( 반드시 세팅해야 함)
        AppGlobalSetting.callResult = callResult;

        NetworkManager.getInstance().doJoin(ModelJoinQuery);

    }


    public static void nicknamecheck( CallResult callResult,  ModelNickNameCheckQuery a){

        // 이벤트 처리 결과를 받을( 반드시 세팅해야 함)
        AppGlobalSetting.callResult = callResult;

        NetworkManager.getInstance().doNickNameCheck(a);

    }

    public static void emailCheck(CallResult callResult, ModelemailQuery a){
        // 이벤트 처리 결과를 받을( 반드시 세팅해야 함)
        AppGlobalSetting.callResult = callResult;

        NetworkManager.getInstance().doEmailCheck(a);
    }

}
