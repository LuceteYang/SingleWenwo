package com.wenwoandroidnew.system.common;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by ModelLoginQuery on 2015-11-07.
 */
public class BackPressCloseHandler {
    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;


    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish();
            toast.cancel();
        }
    }
    //ToDo 라ㅏㄹ랑날니ㅏ
    public void showGuide() {
        toast = Toast.makeText(activity,
                "\'뒤로\' 한번 더 누르시면 종료되요.", Toast.LENGTH_SHORT);
        toast.show();
    }
}
