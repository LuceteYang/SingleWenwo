package com.wenwoandroidnew.system.module;

import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.common.CallResultOnemore;
import com.wenwoandroidnew.system.manager.NetworkManager;
import com.wenwoandroidnew.system.model.JsonNULL;
import com.wenwoandroidnew.system.model.query.ModelAnswerPickQuery;
import com.wenwoandroidnew.system.model.query.ModelBestAnswerQuery;

/**
 * Created by User on 2015-11-13.
 */
public class ModuleCurrency {
    public static void getCurrency(CallResult callResult, JsonNULL model){

        AppGlobalSetting.callResult = callResult;

            NetworkManager.getInstance().doCurrency(model);

    }

}
