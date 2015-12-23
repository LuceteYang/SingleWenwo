package com.wenwoandroidnew.friends;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wenwoandroidnew.R;

/**
 * Created by ModelLoginQuery on 2015-11-04.
 */
public class FriendHeaderView extends LinearLayout {
    EditText etView;

    public FriendHeaderView(Context context) {
        super(context);
        init();
    }

    public FriendHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.view_friend_header, this);
        this.etView = (EditText) findViewById( R.id.editText);
    }

}
