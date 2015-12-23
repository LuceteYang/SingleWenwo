package com.wenwoandroidnew.system.module;


import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.common.CallResultOnemore;
import com.wenwoandroidnew.system.manager.NetworkManager;
import com.wenwoandroidnew.system.model.query.ModelGeocodingQuery;
import com.wenwoandroidnew.system.model.query.ModelQuestionQuery;

/**
 * Created by SeungJin on 2015-10-29.
 */
public class ModuleWeather {

    public static void ModuleWeather( CallResult callResult,ModelGeocodingQuery query){
        // 이벤트 처리 결과를 받을( 반드시 세팅해야 함)
        AppGlobalSetting.callResult = callResult;
        NetworkManager.getInstance().doWeather(query);
    }
}
