package com.wenwoandroidnew.system.model.query;

/**
 * Created by SeungJin on 2015-11-05.
 */
public class ModelGeocodingQuery {

    public Double latitude;

    public Double longitude;

    public ModelGeocodingQuery(){}

    public ModelGeocodingQuery(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
