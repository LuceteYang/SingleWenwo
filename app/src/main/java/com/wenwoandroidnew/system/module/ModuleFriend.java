package com.wenwoandroidnew.system.module;

import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.common.CallResultOnemore;
import com.wenwoandroidnew.system.manager.NetworkManager;
import com.wenwoandroidnew.system.model.query.ModelFriendDeleteQuery;
import com.wenwoandroidnew.system.model.query.ModelemailQuery;

/**
 * Created by SeungJin on 2015-11-16.
 */
public class ModuleFriend {

    public static void getFriendList( CallResult callResult , String email){
        AppGlobalSetting.callResult = callResult;
        NetworkManager.getInstance().doFriendList( email);

    }

    //친구등록
    public static void addFriend( CallResult callResult, String email){
        AppGlobalSetting.callResult = callResult;
        NetworkManager.getInstance().doFriendAdd(email);
    }

    public static void friendDelete( CallResultOnemore callResultOnemore,  ModelFriendDeleteQuery ModelFriendDeleteQuery){
        // 이벤트 처리 결과를 받을( 반드시 세팅해야 함)
        AppGlobalSetting.callResultOnemore = callResultOnemore;
        NetworkManager.getInstance().doFriendDelete(ModelFriendDeleteQuery);
    }

    public static void getFriendQuestion( CallResult callResult ,ModelemailQuery emailQuery){
        AppGlobalSetting.callResult = callResult;
        NetworkManager.getInstance().doFriendQuestion(emailQuery);

    }


}
