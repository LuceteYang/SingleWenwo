package com.wenwoandroidnew.system.module;

import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.manager.NetworkManager;
import com.wenwoandroidnew.system.model.query.ModelQuestionQuery;
import com.wenwoandroidnew.system.model.query.ModelQuestionRegisterQuery;
import com.wenwoandroidnew.system.model.query.ModelemailQuery;

/**
 * Created by User on 2015-11-13.
 */
public class ModuleAnswerer {

    // 답변자정보보기
    public static void answerInfo( CallResult callResult,  ModelemailQuery email){

        // 이벤트 처리 결과를 받을( 반드시 세팅해야 함)
        AppGlobalSetting.callResult = callResult;

        NetworkManager.getInstance().doAnswererInfo(email);

    }

}
