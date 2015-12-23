package com.wenwoandroidnew.discover;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.model.ModelCurrency;
import com.wenwoandroidnew.system.model.ModelGeocoding;
import com.wenwoandroidnew.system.model.ModelWeather;
import com.wenwoandroidnew.system.model.query.ModelGeocodingQuery;
import com.wenwoandroidnew.system.module.ModuleWeather;

public class WeatherActivity extends AppCompatActivity implements CallResult<ModelWeather> {

    TextView addressView, dateView, todayContitionView, todayHighTempView, todayLowTempView, yesdayConditionView,
            yesdayHighTempView, yesdayLowTempView, tommConditionView, tommHighTempView, tommLowTempView, afterConditionView, afterHighTempView, afterLowTempView;
    ImageView todayView, yesdayView, tommView, afterView;
    static final String YES_WEATHER_CODE[] = {"SKY_Y01","SKY_Y02","SKY_Y03","SKY_Y04","SKY_Y05","SKY_Y06","SKY_Y07","SKY_Y08","SKY_Y09","SKY_Y10","SKY_Y11","SKY_Y12","SKY_Y13","SKY_Y14"};
    static final String TOMM_WEATHER_CODE[] = {"SKY_M01","SKY_M02","SKY_M03","SKY_M04","SKY_M05","SKY_M06","SKY_M07","SKY_M08","SKY_M09","SKY_M10","SKY_M11","SKY_M12","SKY_M13","SKY_M14"};
    static final String WEATHER_CODE[] = {"SKY_D01","SKY_D02","SKY_D03","SKY_D04","SKY_D05","SKY_D06","SKY_D07","SKY_D08","SKY_D09","SKY_D10","SKY_D11","SKY_D12","SKY_D13","SKY_D14"};
    static final int WEATHER_ICON[]= {R.drawable.sky_01,R.drawable.sky_02,R.drawable.sky_03,R.drawable.sky_04,R.drawable.sky_05,R.drawable.sky_06,R.drawable.sky_07,
            R.drawable.sky_08,R.drawable.sky_09,R.drawable.sky_10,R.drawable.sky_11,R.drawable.sky_12,R.drawable.sky_13,R.drawable.sky_14};
    String temp ="℃";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getSupportActionBar().setTitle("Weather");
        addressView = (TextView)findViewById(R.id.weather_aderess);
        dateView = (TextView)findViewById(R.id.weather_date);
        todayContitionView = (TextView)findViewById(R.id.weather_today_condition);
        todayHighTempView = (TextView)findViewById(R.id.weather_today_high_temp);
        todayLowTempView = (TextView)findViewById(R.id.weather_today_low_temp);
        yesdayConditionView = (TextView)findViewById(R.id.weather_yesday_condition);
        yesdayHighTempView = (TextView)findViewById(R.id.weather_yesday_high_temp);
        yesdayLowTempView = (TextView)findViewById(R.id.weather_yesday_low_temp);
        tommConditionView = (TextView)findViewById(R.id.weather_tomm_condition);
        tommHighTempView = (TextView)findViewById(R.id.weather_tomm_high_temp);
        tommLowTempView = (TextView)findViewById(R.id.weather_tomm_low_temp);
        afterConditionView = (TextView)findViewById(R.id.weather_after_condition);
        afterHighTempView = (TextView)findViewById(R.id.weather_after_high_temp);
        afterLowTempView = (TextView)findViewById(R.id.weather_after_low_temp);
        todayView = (ImageView)findViewById(R.id.iv_today);
        yesdayView = (ImageView)findViewById(R.id.iv_yesday);
        tommView = (ImageView)findViewById(R.id.iv_tomm);
        afterView = (ImageView)findViewById(R.id.iv_after);


        MyLocation.LocationResult locationResult = new MyLocation.LocationResult() {
            @Override
            public void gotLocation(Location location) {
                ModelGeocodingQuery a = new ModelGeocodingQuery(location.getLatitude(),location.getLongitude());
                ModuleWeather.ModuleWeather(WeatherActivity.this,a);
            }
        };

        MyLocation myLocation = new MyLocation();
        if(!myLocation.getLocation(getApplicationContext(), locationResult)){
            Toast.makeText(getApplicationContext(),"Location null", Toast.LENGTH_SHORT).show();
            ModelGeocodingQuery a = new ModelGeocodingQuery(37.56, 126.97);
            ModuleWeather.ModuleWeather(WeatherActivity.this,a);
        }

    }

    @Override
    public void callResult(ModelWeather modelWeather) {
        ModelWeather.WeatherInfo mq = modelWeather.getWeather();
        addressView.setText(mq.getSummary().get(0).getGrid().getCity()+" "+mq.getSummary().get(0).getGrid().getCounty()+" "+mq.getSummary().get(0).getGrid().getVillage());
        String date = mq.getSummary().get(0).getTimeRelease().split(" ")[0];
        String year = date.split("-")[0];
        String month = date.split("-")[1];
        String day = date.split("-")[2];
        dateView.setText(year+" 년 "+month+" 월 "+day+" 일");
        todayContitionView.setText(mq.getSummary().get(0).getToday().getSky().getName());
        todayHighTempView.setText(mq.getSummary().get(0).getToday().getTemperature().getTmax()+temp);
        todayLowTempView.setText(mq.getSummary().get(0).getToday().getTemperature().getTmin()+temp);
        yesdayConditionView.setText(mq.getSummary().get(0).getYesterday().getSky().getName());
        yesdayHighTempView.setText(mq.getSummary().get(0).getYesterday().getTemperature().getTmax()+temp);
        yesdayLowTempView.setText(mq.getSummary().get(0).getYesterday().getTemperature().getTmin()+temp);
        tommConditionView.setText(mq.getSummary().get(0).getTomorrow().getSky().getName());
        tommHighTempView.setText(mq.getSummary().get(0).getTomorrow().getTemperature().getTmax()+temp);
        tommLowTempView.setText(mq.getSummary().get(0).getTomorrow().getTemperature().getTmin()+temp);
        afterConditionView.setText(mq.getSummary().get(0).getDayAfterTomorrow().getSky().getName());
        afterHighTempView.setText(mq.getSummary().get(0).getDayAfterTomorrow().getTemperature().getTmax()+temp);
        afterLowTempView.setText(mq.getSummary().get(0).getDayAfterTomorrow().getTemperature().getTmin()+temp);
        todayView.setImageDrawable(getResources().getDrawable(setDrawble(mq.getSummary().get(0).getToday().getSky().getCode(),0)));
        yesdayView.setImageDrawable(getResources().getDrawable(setDrawble(mq.getSummary().get(0).getYesterday().getSky().getCode(),1)));
        tommView.setImageDrawable(getResources().getDrawable(setDrawble(mq.getSummary().get(0).getTomorrow().getSky().getCode(),2)));
        afterView.setImageDrawable(getResources().getDrawable(setDrawble(mq.getSummary().get(0).getDayAfterTomorrow().getSky().getCode(),2)));
    }

    public int setDrawble(String code,int day){
        int b = 0;
        if(day==0) {
            for (int a = 0; a < WEATHER_CODE.length; a++) {
                if (code.equals(WEATHER_CODE[a])) {
                    b = a;
                    break;
                }
            }
        }
        if(day==1) {
            for (int a = 0; a < WEATHER_CODE.length; a++) {
                if (code.equals(YES_WEATHER_CODE[a])) {
                    b = a;
                    break;
                }
            }
        }
        if(day==2) {
            for (int a = 0; a < WEATHER_CODE.length; a++) {
                if (code.equals(TOMM_WEATHER_CODE[a])) {
                    b = a;
                    break;
                }
            }
        }
        return WEATHER_ICON[b];
    }
}
