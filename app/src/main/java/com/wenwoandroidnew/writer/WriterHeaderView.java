package com.wenwoandroidnew.writer;

import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.system.util.UtilCommon;

import java.io.IOException;

/**
 * Created by ModelLoginQuery on 2015-11-07.
 */
public class WriterHeaderView extends LinearLayout {
    WriterHeaderItem writerHeaderInfo;
    ImageView ivWriterProfile;
    TextView tvWriterName, tvWriterCoupon, tvWriterGrade, tvWriterAccept;

    public WriterHeaderView(Context context) {
        super(context);
        init();
    }

    public WriterHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setTempHeader( String profile, String name){

        this.tvWriterName.setText(name);
        try {
            UtilCommon.urlToDrawableProfileImage( profile, this.ivWriterProfile);
        } catch (IOException e) {
            Log.d("Error" , e.toString());
        }

        this.refreshDrawableState();
    }

    private View view = null;
    private void init() {
        view = LayoutInflater.from( getContext()).inflate( R.layout.view_writer_header, this);
        this.ivWriterProfile = (ImageView) view.findViewById(R.id.iv_writer_header_profile);
        this.tvWriterName = (TextView) view.findViewById(R.id.tv_writer_header_name);
        this.tvWriterCoupon = (TextView) view.findViewById(R.id.tv_writer_coupon);
        this.tvWriterGrade = (TextView) view.findViewById(R.id.tv_writer_grade);
        this.tvWriterAccept = (TextView) view.findViewById(R.id.tv_writer_accept);
    }

    public void setWriterHeaderInfo(WriterHeaderItem writerHeaderInfo) {
        this.writerHeaderInfo = writerHeaderInfo;
        if(writerHeaderInfo.getWriterProfile()!=null){
            if( writerHeaderInfo.getWriterProfile().size()>0){
                try {
                    // 이미지 뷰를 같이 넘겨서 그곳에 다운로드 받은 이미지 설정함
                    UtilCommon.urlToDrawableProfileImage(writerHeaderInfo.getWriterProfile().get(0).getTh(), this.ivWriterProfile);
                }  catch (IOException e) {
                    Log.d("MagazineView : 에러", e.getMessage());
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }else{
                this.ivWriterProfile.setImageDrawable(getResources().getDrawable(R.drawable.default121));
            }
        }else{
            this.ivWriterProfile.setImageDrawable(getResources().getDrawable(R.drawable.default121));
        }


        this.tvWriterName.setText(writerHeaderInfo.getWriterName());
        this.tvWriterCoupon.setText(writerHeaderInfo.getWriterCoupon());
        this.tvWriterGrade.setText(writerHeaderInfo.getWriterGrade());
        this.tvWriterAccept.setText(writerHeaderInfo.getWriterAccept());
    }

}
