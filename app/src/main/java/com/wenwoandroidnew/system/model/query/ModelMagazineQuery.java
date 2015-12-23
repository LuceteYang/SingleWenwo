package com.wenwoandroidnew.system.model.query;

import com.wenwoandroidnew.system.util.AppSetting;

/**
 * Created by SeungJin on 2015-11-05.
 */
public class ModelMagazineQuery {

    public String aemail;
    public String qemail;
    public boolean isFirstStart;

    public AppSetting.MAGAZINE_CALL_TYPE call_type;

    @Override
    public String toString() {
        return aemail;
    }
}
