package com.wenwoandroidnew;


import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfrvr.rubberloader.RubberLoaderView;
import com.wenwoandroidnew.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogLoadingFragment extends DialogFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        setStyle(STYLE_NO_TITLE, R.style.custom_full_dialog);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_loading, container, false);

        RubberLoaderView rubberLoaderView = (RubberLoaderView)view.findViewById(R.id.loaderview);
        rubberLoaderView.startLoading();

        return view;
    }



}
