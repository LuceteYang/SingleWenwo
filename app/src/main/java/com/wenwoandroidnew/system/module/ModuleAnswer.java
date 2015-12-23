package com.wenwoandroidnew.system.module;

import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.common.CallResultOnemore;
import com.wenwoandroidnew.system.manager.NetworkManager;
import com.wenwoandroidnew.system.model.query.ModelAnswerPickQuery;
import com.wenwoandroidnew.system.model.query.ModelBestAnswerQuery;

/**
 * Created by User on 2015-11-13.
 */
public class ModuleAnswer {
    public static void getAnswerList(CallResult callResult,int query,int pick){

        AppGlobalSetting.callResult = callResult;

            NetworkManager.getInstance().doAnswerList(query,pick);


    }

    public static void doAnswerPick(CallResultOnemore callResultOnemore,ModelAnswerPickQuery query){

        AppGlobalSetting.callResultOnemore = callResultOnemore;

        NetworkManager.getInstance().doAnswerPick(query);


    }

    public static void bestAnswer( CallResult callResult,  ModelBestAnswerQuery ModelBestAnswerQuery){
        // 이벤트 처리 결과를 받을( 반드시 세팅해야 함)
        AppGlobalSetting.callResult = callResult;
        NetworkManager.getInstance().doBestAnswerList(ModelBestAnswerQuery);
    }

}
