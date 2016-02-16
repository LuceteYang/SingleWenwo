package com.wenwoandroidnew.discover;

import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wenwoandroidnew.R;

import java.util.Locale;

public class MapActivity extends FragmentActivity{

    private GoogleMap map;
    static final LatLng SEOUL = new LatLng( 37.56, 126.97);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        map = mapFragment.getMap();
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 15));

        MyLocation.LocationResult locationResult = new MyLocation.LocationResult() {
            @Override
            public void gotLocation(Location location) {
//                if(location==null){
//                    Toast.makeText(getApplicationContext(),"Location null", Toast.LENGTH_SHORT).show();
//                }
                String msg = "lon: "+location.getLongitude()+" -- lat: "+location.getLatitude();
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                onMapReady(location);
            }
        };


        MyLocation myLocation = new MyLocation();
        if(!myLocation.getLocation(getApplicationContext(), locationResult)){
            Toast.makeText(getApplicationContext(),"Location null", Toast.LENGTH_SHORT).show();
        }
    }

    public void onMapReady(Location location) {


        LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());

        //currentPosition 위치로 카메라 중심을 옮기고 화면 줌을 조정한다. 줌범위는 2~21, 숫자클수록 확대
        map.moveCamera(CameraUpdateFactory.newLatLngZoom( currentPosition, 17));
        map.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);

        //마커 추가
        map.addMarker(new MarkerOptions()
                .position(currentPosition)
                .snippet("Lat:" + location.getLatitude() + "Lng:" + location.getLongitude())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title("현재위치"));
    }
}
