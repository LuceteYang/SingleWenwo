package com.wenwoandroidnew.system.module;


import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResultOnemore;
import com.wenwoandroidnew.system.manager.NetworkManager;
import com.wenwoandroidnew.system.model.query.ModelGeocodingQuery;

/**
 * Created by SeungJin on 2015-10-29.
 */
public class ModuleGeocoding {

    public static void ModuleGeocoding( CallResultOnemore callResultonemore,  ModelGeocodingQuery ModelGeocodingQuery){
        // 이벤트 처리 결과를 받을( 반드시 세팅해야 함)
        AppGlobalSetting.callResultOnemore = callResultonemore;
        NetworkManager.getInstance().doGeocoding(ModelGeocodingQuery);
    }
}
