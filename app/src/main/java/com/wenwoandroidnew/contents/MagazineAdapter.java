package com.wenwoandroidnew.contents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.contents.magazineDetail.MagazineDetailActivity;
import com.wenwoandroidnew.me.ContentsItem;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.BasicAdater;
import com.wenwoandroidnew.system.util.AppSetting;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ModelLoginQuery on 2015-11-05.
 */
public class MagazineAdapter extends BasicAdater<Magazineitem> {

    private String contentDirectory = "";

    boolean bType = false;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MagazineView magazineView = null;

        if( contentDirectory.equals(items.get(position).getDirectoy()) == false){
            bType = !bType;
            contentDirectory = items.get(position).getDirectoy();
        }
        magazineView = new MagazineView( parent.getContext(), items.get(position), bType);
        //내용 클릭시 매거진디테일페이지 창띠우기
        magazineView.getClickView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Magazineitem item = items.get(position);
                Intent i = new Intent(v.getContext(), MagazineDetailActivity.class);
                i.putExtra("tiger",item);
                if( AppSetting.LOG_TYPE == true) {
                    Log.d("magazineadpater", item.toString());
                }
                v.getContext().startActivity(i);
            }
        });
        //사진 클릭시 매거진디테일페이지 창띠우기
        magazineView.getIvMagazineImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Magazineitem item = items.get(position);
                Intent i = new Intent(v.getContext(), MagazineDetailActivity.class);
                i.putExtra("tiger",item);
                if( AppSetting.LOG_TYPE == true) {
                    Log.d("magazineadpater", item.toString());
                }
                v.getContext().startActivity(i);
            }
        });
        final MagazineView finalMagazineView = magazineView;
        //좋아요 누를시
        magazineView.getLikeView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Magazineitem item = items.get(position);
                if(item.isLike.equals("false")){
                    item.isLike="true";
                    item.setLike(Integer.toString(Integer.parseInt(item.getLike()) + 1));
                    finalMagazineView.getLikeIcon().setImageDrawable(AppGlobalSetting.context.getResources().getDrawable(R.drawable.contents_like_onclick));
                    finalMagazineView.getTvLike().setText(item.getLike());
                }else{
                    item.isLike="false";
                    item.setLike(Integer.toString(Integer.parseInt(item.getLike()) - 1));
                    finalMagazineView.getLikeIcon().setImageDrawable(AppGlobalSetting.context.getResources().getDrawable(R.drawable.contents_like));
                    finalMagazineView.getTvLike().setText(item.getLike());
                }
            }
        });
        //포스트 누를시
        magazineView.getShareView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Magazineitem item = items.get(position);
                if(item.isSave.equals("false")){
                    item.isSave="true";
                    item.setSave(Integer.toString(Integer.parseInt(item.getSave()) + 1));
                    finalMagazineView.getShareIcon().setImageDrawable(AppGlobalSetting.context.getResources().getDrawable(R.drawable.contents_save_onclick));
                    finalMagazineView.getTvSave().setText(item.getSave());
                }else{
                    item.isSave="false";
                    item.setSave(Integer.toString(Integer.parseInt(item.getSave()) - 1));
                    finalMagazineView.getShareIcon().setImageDrawable(AppGlobalSetting.context.getResources().getDrawable(R.drawable.contents_save));
                    finalMagazineView.getTvSave().setText(item.getSave());

                }
            }
        });
        return magazineView;
    }
}
