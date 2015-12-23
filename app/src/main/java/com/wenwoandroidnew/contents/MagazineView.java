package com.wenwoandroidnew.contents;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.system.util.UtilCommon;

import java.io.IOException;

/**
 * Created by ModelLoginQuery on 2015-11-05.
 */
public class MagazineView extends LinearLayout {
    private ImageView IvMagazineImage, likeIcon, shareIcon;
    private TextView tvContent, tvWriter, tvLike, tvSave,tvMagazineCategory;
    private String directory;
    private Magazineitem magazineInfo;
    private boolean bContentType;
    private RelativeLayout clickView, shareView, likeView;

    public MagazineView(Context context) {
        super(context);
        init();
    }
    public MagazineView(Context context , Magazineitem item , boolean bContentType) {
        super(context);
        magazineInfo = item;
        this.bContentType = bContentType;
        init();
    }
    public MagazineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){

        if( bContentType == true){
            inflate( getContext(), R.layout.view_magazine_left, this);
            this.IvMagazineImage = (ImageView) findViewById( R.id.iv_ltmagazine);
            this.tvContent = (TextView) findViewById( R.id.tv_ltmagazine_content);
            this.tvLike = (TextView) findViewById( R.id.tv_ltmagazine_like);
            this.tvSave = (TextView) findViewById( R.id.tv_ltmagazine_save);
            this.tvMagazineCategory = (TextView)findViewById(R.id.tv_ltmagazine_category);
            this.clickView = (RelativeLayout)findViewById(R.id.lt_magazine_clickview);
            this.shareView = (RelativeLayout)findViewById(R.id.lt_shareview);
            this.likeView = (RelativeLayout)findViewById(R.id.lt_likeview);
            this.likeIcon = (ImageView)findViewById(R.id.lt_likeicon);
            this.shareIcon = (ImageView)findViewById(R.id.lt_shareicon);
        }
        else{
            inflate( getContext(), R.layout.view_magazine_right, this);
            this.IvMagazineImage = (ImageView) findViewById( R.id.iv_rtmagazine);
            this.tvContent = (TextView) findViewById( R.id.tv_rtmagazine_content);
            this.tvLike = (TextView) findViewById( R.id.tv_rtmagazine_like);
            this.tvSave = (TextView) findViewById( R.id.tv_rtmagazine_save);
            this.tvMagazineCategory = (TextView)findViewById(R.id.tv_rtmagazine_category);
            this.clickView = (RelativeLayout)findViewById(R.id.rt_magazine_clickview);
            this.shareView = (RelativeLayout)findViewById(R.id.rt_shareview);
            this.likeView = (RelativeLayout)findViewById(R.id.rt_likeview);
            this.likeIcon = (ImageView)findViewById(R.id.rt_likeicon);
            this.shareIcon = (ImageView)findViewById(R.id.rt_shareicon);
        }

        // 이미지가 있을 경우만 처리함
        if( magazineInfo.getMagazineImageList() != null && magazineInfo.getMagazineImageList().size()>0){
            try {
                // 이미지 뷰를 같이 넘겨서 그곳에 다운로드 받은 이미지 설정함
                UtilCommon.urlToDrawableProfileImage(magazineInfo.getMagazineImageList().get(0).getTh(), IvMagazineImage);
            } catch (IOException e) {
                Log.d("MagazineView : 에러" , e.toString());
                Toast.makeText( getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        tvMagazineCategory.setText(magazineInfo.getDirectoy());
        tvContent.setText(magazineInfo.getContent());
        tvLike.setText(magazineInfo.getLike());
        tvSave.setText(magazineInfo.getSave());
        if(magazineInfo.getIsLike().equals("true")){
            likeIcon.setImageDrawable(getResources().getDrawable(R.drawable.contents_like_onclick));
        }else{
            likeIcon.setImageDrawable(getResources().getDrawable(R.drawable.contents_like));
        }

        if(magazineInfo.getIsSave().equals("true")){
            shareIcon.setImageDrawable(getResources().getDrawable(R.drawable.contents_save_onclick));
        }else{
            shareIcon.setImageDrawable(getResources().getDrawable(R.drawable.contents_save));
        }

    }

    public ImageView getLikeIcon() {
        return likeIcon;
    }

    public void setLikeIcon(ImageView likeIcon) {
        this.likeIcon = likeIcon;
    }

    public ImageView getShareIcon() {
        return shareIcon;
    }

    public void setShareIcon(ImageView shareIcon) {
        this.shareIcon = shareIcon;
    }

    public TextView getTvMagazineCategory() {
        return tvMagazineCategory;
    }

    public void setTvMagazineCategory(TextView tvMagazineCategory) {
        this.tvMagazineCategory = tvMagazineCategory;
    }

    public RelativeLayout getClickView() {
        return clickView;
    }

    public void setClickView(RelativeLayout clickView) {
        this.clickView = clickView;
    }

    public RelativeLayout getShareView() {
        return shareView;
    }

    public void setShareView(RelativeLayout shareView) {
        this.shareView = shareView;
    }

    public RelativeLayout getLikeView() {
        return likeView;
    }

    public void setLikeView(RelativeLayout likeView) {
        this.likeView = likeView;
    }

    public ImageView getIvMagazineImage() {
        return IvMagazineImage;
    }

    public void setIvMagazineImage(ImageView ivMagazineImage) {
        IvMagazineImage = ivMagazineImage;
    }

    public TextView getTvContent() {
        return tvContent;
    }

    public void setTvContent(TextView tvContent) {
        this.tvContent = tvContent;
    }

    public TextView getTvWriter() {
        return tvWriter;
    }

    public void setTvWriter(TextView tvWriter) {
        this.tvWriter = tvWriter;
    }

    public TextView getTvLike() {
        return tvLike;
    }

    public void setTvLike(TextView tvLike) {
        this.tvLike = tvLike;
    }

    public TextView getTvSave() {
        return tvSave;
    }

    public void setTvSave(TextView tvSave) {
        this.tvSave = tvSave;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public Magazineitem getMagazineInfo() {
        return magazineInfo;
    }

    public void setMagazineInfo(Magazineitem magazineInfo) {
        this.magazineInfo = magazineInfo;
    }

    public boolean isbContentType() {
        return bContentType;
    }

    public void setbContentType(boolean bContentType) {
        this.bContentType = bContentType;
    }
/*    public void setMagazineInfo(Magazineitem magazineInfo) {
        this.magazineInfo = magazineInfo;
        this.directory = magazineInfo.getDirectoy();
        this.IvMagazineImage.setImageDrawable(magazineInfo.getMagazineimage());
        this.tvContent.setText(magazineInfo.getContent());
        this.tvLike.setText(magazineInfo.getLike());
        this.tvSave.setText(magazineInfo.getSave());
        this.tvWriter.setText(magazineInfo.getWriter());
    }*/

}
