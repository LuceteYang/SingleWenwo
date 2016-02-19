package com.wenwoandroidnew.system.model.query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SeungJin on 2015-11-18.
 */
public class ModelQuestionRegisterQuery implements Serializable{

    private String qemail = "";
    private String spentSeed = "";
    private String title = "";
    private String category = "";
    private String text = "";
    private List<String> image = null;
    private String recording = "";
    private String si;
    private String gu = "";
    private String dong;
    private String dueTile = "";
    private String open = "";
    private String type = "";
    private String lat;
    private String lon;
    private String myloc="false";

    private boolean success = false;
    private Error error;

    public class Error{
        public String message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess( boolean success){
        this.success = success;
    }

    public void setSuccess(boolean success , String msg) {
        this.success = success;
        if( success == false){
            error = new Error();
            error.message = msg;
        }
    }

    public String getErrorMessage(){
        if( error == null) return "";
        return error.message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQemail() {
        return qemail;
    }

    public void setQemail(String qemail) {
        this.qemail = qemail;
    }

    public String getSpentSeed() {
        return spentSeed;
    }

    public void setSpentSeed(String spentSeed) {
        this.spentSeed = spentSeed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }

    public String getSi() {
        return si;
    }

    public void setSi(String si) {
        this.si = si;
    }

    public String getGu() {
        return gu;
    }

    public void setGu(String gu) {
        this.gu = gu;
    }

    public String getDong() {
        return dong;
    }

    public void setDong(String dong) {
        this.dong = dong;
    }

    public String getDueTile() {
        return dueTile;
    }

    public void setDueTile(String dueTile) {
        this.dueTile = dueTile;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }


    public String getMyloc() {
        return myloc;
    }

    public void setMyloc(String myloc) {
        this.myloc = myloc;
    }
}