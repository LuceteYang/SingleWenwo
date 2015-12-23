package com.wenwoandroidnew.system.module;

import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.manager.NetworkManager;
import com.wenwoandroidnew.system.model.query.ModelQuestionQuery;
import com.wenwoandroidnew.system.model.query.ModelQuestionRegisterQuery;

/**
 * Created by User on 2015-11-13.
 */
public class ModuleQuestion {
    public static void getQuestionList(CallResult callResult,ModelQuestionQuery query){

        AppGlobalSetting.callResult = callResult;

        NetworkManager.getInstance().doQuestionList(query);


    }

    // 회원가입
    public static void register( CallResult callResult,  ModelQuestionRegisterQuery register){

        // 이벤트 처리 결과를 받을( 반드시 세팅해야 함)
        AppGlobalSetting.callResult = callResult;

        NetworkManager.getInstance().doQuestionRegister(register);

    }

}
