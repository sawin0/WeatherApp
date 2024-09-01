package com.weather.app.data.model;

public class CurrentUnits {
    private String time;
    private String interval;
    private String temperature2M;
    private String isDay;
    private String weatherCode;

    public String getTime() {
        return time;
    }

    public void setTime(String value) {
        this.time = value;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String value) {
        this.interval = value;
    }

    public String getTemperature2M() {
        return temperature2M;
    }

    public void setTemperature2M(String value) {
        this.temperature2M = value;
    }

    public String getIsDay() {
        return isDay;
    }

    public void setIsDay(String value) {
        this.isDay = value;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String value) {
        this.weatherCode = value;
    }
}
