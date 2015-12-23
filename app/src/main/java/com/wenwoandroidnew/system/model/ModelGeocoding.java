package com.wenwoandroidnew.system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wenwoandroidnew.system.common.ModelBase;

/**
 * Created by User on 2015-11-16.
 */
public class ModelGeocoding  implements ModelBase {

    @SerializedName("addressInfo")
    @Expose
    AddressInfo addressInfo;

    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    @Override
    public String toString() {
        return "ModelGeocoding{" +
                "addressInfo=" + addressInfo +
                '}';
    }
    public class AddressInfo{
        @SerializedName("city_do")
        @Expose
        String city_do;

        @SerializedName("gu_gun")
        @Expose
        String gu_gun;

        @SerializedName("legalDong")
        @Expose
        String legalDong;

        public String getCity_do() {
            return city_do;
        }

        public void setCity_do(String city_do) {
            this.city_do = city_do;
        }

        public String getGu_gun() {
            return gu_gun;
        }

        public void setGu_gun(String gu_gun) {
            this.gu_gun = gu_gun;
        }

        public String getLegalDong() {
            return legalDong;
        }

        public void setLegalDong(String legalDong) {
            this.legalDong = legalDong;
        }

        @Override
        public String toString() {
            return "AddressInfo{" +
                    "city_do='" + city_do + '\'' +
                    ", gu_gun='" + gu_gun + '\'' +
                    ", legalDong='" + legalDong + '\'' +
                    '}';
        }
    }

}

