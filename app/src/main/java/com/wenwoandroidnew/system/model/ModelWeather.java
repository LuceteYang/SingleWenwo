package com.wenwoandroidnew.system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by User on 2015-12-18.
 */
public class ModelWeather {

    @SerializedName("weather")
    @Expose
    WeatherInfo weather;

    public WeatherInfo getWeather() {
        return weather;
    }

    public void setWeather(WeatherInfo weather) {
        this.weather = weather;
    }

    public class WeatherInfo {
        @SerializedName("summary")
        @Expose
        List<summaryList> summary;

        public List<summaryList> getSummary() {
            return summary;
        }

        public void setSummary(List<summaryList> summary) {
            this.summary = summary;
        }
    }
    public class summaryList{
        @SerializedName("grid")
        @Expose
        Grid grid;

        @SerializedName("yesterday")
        @Expose
        Yesterday yesterday;

        @SerializedName("today")
        @Expose
        Today today;

        @SerializedName("tomorrow")
        @Expose
        Tommorrow tomorrow;

        @SerializedName("timeRelease")
        @Expose
        String timeRelease;

        @SerializedName("dayAfterTomorrow")
        @Expose
        DayAfterTommorrow dayAfterTomorrow;

        public Grid getGrid() {
            return grid;
        }

        public void setGrid(Grid grid) {
            this.grid = grid;
        }

        public Yesterday getYesterday() {
            return yesterday;
        }

        public void setYesterday(Yesterday yesterday) {
            this.yesterday = yesterday;
        }

        public Today getToday() {
            return today;
        }

        public void setToday(Today today) {
            this.today = today;
        }

        public Tommorrow getTomorrow() {
            return tomorrow;
        }

        public void setTomorrow(Tommorrow tomorrow) {
            this.tomorrow = tomorrow;
        }

        public String getTimeRelease() {
            return timeRelease;
        }

        public void setTimeRelease(String timeRelease) {
            this.timeRelease = timeRelease;
        }

        public DayAfterTommorrow getDayAfterTomorrow() {
            return dayAfterTomorrow;
        }

        public void setDayAfterTomorrow(DayAfterTommorrow dayAfterTomorrow) {
            this.dayAfterTomorrow = dayAfterTomorrow;
        }

        public class Grid {
            @SerializedName("city")
            @Expose
            String city;

            @SerializedName("county")
            @Expose
            String county;

            @SerializedName("village")
            @Expose
            String village;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCounty() {
                return county;
            }

            public void setCounty(String county) {
                this.county = county;
            }

            public String getVillage() {
                return village;
            }

            public void setVillage(String village) {
                this.village = village;
            }
        }

        public class Yesterday {
            @SerializedName("sky")
            @Expose
            Sky sky;

            @SerializedName("temperature")
            @Expose
            Temperature temperature;

            public Sky getSky() {
                return sky;
            }

            public void setSky(Sky sky) {
                this.sky = sky;
            }

            public Temperature getTemperature() {
                return temperature;
            }

            public void setTemperature(Temperature temperature) {
                this.temperature = temperature;
            }
        }

        public class Today {
            @SerializedName("sky")
            @Expose
            Sky sky;

            @SerializedName("temperature")
            @Expose
            Temperature temperature;

            public Sky getSky() {
                return sky;
            }

            public void setSky(Sky sky) {
                this.sky = sky;
            }

            public Temperature getTemperature() {
                return temperature;
            }

            public void setTemperature(Temperature temperature) {
                this.temperature = temperature;
            }
        }

        public class Tommorrow {
            @SerializedName("sky")
            @Expose
            Sky sky;

            @SerializedName("temperature")
            @Expose
            Temperature temperature;

            public Sky getSky() {
                return sky;
            }

            public void setSky(Sky sky) {
                this.sky = sky;
            }

            public Temperature getTemperature() {
                return temperature;
            }

            public void setTemperature(Temperature temperature) {
                this.temperature = temperature;
            }
        }

        public class DayAfterTommorrow {
            @SerializedName("sky")
            @Expose
            Sky sky;

            @SerializedName("temperature")
            @Expose
            Temperature temperature;

            public Sky getSky() {
                return sky;
            }

            public void setSky(Sky sky) {
                this.sky = sky;
            }

            public Temperature getTemperature() {
                return temperature;
            }

            public void setTemperature(Temperature temperature) {
                this.temperature = temperature;
            }
        }

        public class Sky{
            @SerializedName("name")
            @Expose
            String name;

            @SerializedName("code")
            @Expose
            String code;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }

        public class Temperature{
            @SerializedName("tmax")
            @Expose
            String tmax;

            @SerializedName("tmin")
            @Expose
            String tmin;

            public String getTmax() {
                return tmax;
            }

            public void setTmax(String tmax) {
                this.tmax = tmax;
            }

            public String getTmin() {
                return tmin;
            }

            public void setTmin(String tmin) {
                this.tmin = tmin;
            }
        }
    }
    }


