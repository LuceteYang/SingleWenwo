package com.wenwoandroidnew.system.module;

import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.common.CallResultOnemore;
import com.wenwoandroidnew.system.manager.NetworkManager;
import com.wenwoandroidnew.system.model.query.ModelMagazineQuery;

/**
 * Created by User on 2015-11-13.
 */
public class ModuleMyInfo {
    public static void getMyInfo(CallResultOnemore CallResultOnemore, ModelMagazineQuery query){

        AppGlobalSetting.callResultOnemore = CallResultOnemore;

            NetworkManager.getInstance().doMyInfo(query);


    }
}
