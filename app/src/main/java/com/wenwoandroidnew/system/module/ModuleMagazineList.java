package com.wenwoandroidnew.system.module;

import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.manager.NetworkManager;
import com.wenwoandroidnew.system.model.query.ModelMagazineQuery;

/**
 * Created by User on 2015-11-13.
 */
public class ModuleMagazineList {
    public static void getMagazineList(CallResult callResult, ModelMagazineQuery query){

        AppGlobalSetting.callResult = callResult;

            NetworkManager.getInstance().doMagazineList(query);


    }
}
