package com.weather.app.data.model;

public class HourlyUnits {
    private String time;
    private String temperature2M;
    private String weatherCode;

    public String getTime() {
        return time;
    }

    public void setTime(String value) {
        this.time = value;
    }

    public String getTemperature2M() {
        return temperature2M;
    }

    public void setTemperature2M(String value) {
        this.temperature2M = value;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String value) {
        this.weatherCode = value;
    }
}
