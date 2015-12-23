package com.wenwoandroidnew.system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 2015-12-18.
 */
public class ModelCurrency {

    @SerializedName("rates")
    @Expose
    RateInfo rates;

    public RateInfo getRates() {
        return rates;
    }

    public void setRates(RateInfo rates) {
        this.rates = rates;
    }

    public class RateInfo {
        @SerializedName("CNY")
        @Expose
        double CNY;

        @SerializedName("KRW")
        @Expose
        double KRW;

        public double getCNY() {
            return CNY;
        }

        public void setCNY(double CNY) {
            this.CNY = CNY;
        }

        public double getKRW() {
            return KRW;
        }

        public void setKRW(double KRW) {
            this.KRW = KRW;
        }

        @Override
        public String toString() {
            return "RateInfo{" +
                    "CNY=" + Double.toString(CNY) +
                    ", KRW=" + Double.toString(KRW) +
                    '}';
        }
    }

}

