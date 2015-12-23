package com.wenwoandroidnew.question.voice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.system.util.AppSetting;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tr.xip.markview.MarkView;

public class RecordActivity extends AppCompatActivity {

    private boolean bRecordStart = false;
    private VoiceRecordPlayer voiceRecordPlaye;

    @Bind(R.id.tv_record_time)
    TextView tvRecordTime;

    private Timer timer;
    private int recordCount = AppSetting.RECORD_TIME;

    public static final String RESULT_RECORD_FILENAME = "FILENAME";

    @Bind(R.id.v_progress_record)
    MarkView markView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        ButterKnife.bind(this);

        voiceRecordPlaye = new VoiceRecordPlayer();
    }

    void setShow( boolean bCheck){

        if( bCheck == true){
            findViewById( R.id.iv_record_run).setVisibility(View.GONE);
            findViewById( R.id.v_progress_record).setVisibility(View.VISIBLE);
            findViewById( R.id.tv_record_time).setVisibility(View.VISIBLE);
        }
        else{
            findViewById( R.id.iv_record_run).setVisibility(View.VISIBLE);
            findViewById( R.id.v_progress_record).setVisibility(View.GONE);
            findViewById( R.id.tv_record_time).setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.iv_record_run)
    void onRecord() {

        if (bRecordStart == false) {

            setShow( true);

            this.voiceRecordPlaye.recordStart();


            // 타이머동작
            timer = new Timer();
            timer.schedule(new WorkTask(), 1000, 1000);

            this.bRecordStart = true;
        } else if (bRecordStart == true) {
            setShow( true);
            stopReord();

        }
    }

    @OnClick(R.id.v_progress_record)
    void onStopRecord(){
        stopReord();
    }

    // 녹음 멈추기
    void stopReord(){
        timer.cancel();
        String recordFile = this.voiceRecordPlaye.recordStop();
        Toast.makeText(RecordActivity.this, "녹음이 완료되었습니다.", Toast.LENGTH_SHORT).show();

        Intent data = new Intent();
        data.putExtra(RESULT_RECORD_FILENAME , recordFile);
        setResult(Activity.RESULT_OK, data);
        finish();

    }

    // 시간화면 세팅
    void setTime( String time){
        tvRecordTime.setText(time);
    }

    final Handler handler = new Handler()
    {

        public void handleMessage(Message msg)
        {
            recordCount = recordCount - 1;
            StringBuilder sb = new StringBuilder("recoding");
            for (int i = (recordCount%5) ; i < 5 ; i++){
                sb.append(".");
            }

            //setTime("00:"+ (recordCount<10?"0"+recordCount:recordCount));
            setTime(sb.toString());
            markView.setMark( recordCount);
            if( recordCount == 0){
                stopReord();
            }

        }

    };

    public class WorkTask extends TimerTask {

        @Override
        public void run() {

            Message msg = handler.obtainMessage();
            handler.sendMessage(msg);

        }

    }
}
