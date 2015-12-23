package com.wenwoandroidnew.question.voice;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

public class VoiceRecordPlayer {

    private MediaRecorder mr;
    private MediaPlayer mp;

    private String fileName;

    private static final String AUDIO_RECORDER_FILE_EXT_MP3 = ".mp3";
    private static final String AUDIO_RECORDER_FOLDER = "Wenwo";

    public VoiceRecordPlayer() {

        init();
    }

    private void init(){
        mr = new MediaRecorder();
    }

    private String getFilename() {
        String filepath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(filepath, AUDIO_RECORDER_FOLDER);
        if (!file.exists()) {
            file.mkdirs();
        }
        return (file.getAbsolutePath() + "/" + System.currentTimeMillis() + AUDIO_RECORDER_FILE_EXT_MP3);
    }


    // 레코드 시작
    public void recordStart(){
        mr.setAudioSource(MediaRecorder.AudioSource.MIC);
        mr.setOutputFormat(0);
        mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        fileName = getFilename();
        mr.setOutputFile(fileName);

        try {
            mr.prepare();
            mr.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
        }

    }

    // 레코드 종료, 생성 파일명 반환.
    public String recordStop(){
        if (null != this) {
            mr.stop();
            mr.reset();
            mr.release();
            return fileName;
        }
        return "";
    }

    public void playStop(){

        if( mp == null){
            return ;
        }
        mp.stop();
    }
    // 녹음된 음성파일 재생
    public boolean play( String file){
        mp = new MediaPlayer();

        if( new File( file).exists() == false){
            return false;
        }

        try {
            if ( mp.isPlaying()){
                mp.reset();
            }
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setDataSource(file);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    // 녹음된 음성파일 재생
    public boolean play(){
        mp = new MediaPlayer();

        if( new File( fileName).exists() == false){
            return false;
        }

        try {
            if ( mp.isPlaying()){
                mp.reset();
            }
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setDataSource(fileName);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            return false;
        }

        return true;
    }


}
