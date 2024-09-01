package com.weather.app.data.model;

public class DailyUnits {
    private String time;
    private String weatherCode;
    private String temperature2MMax;
    private String temperature2MMin;
    private String apparentTemperatureMax;
    private String apparentTemperatureMin;
    private String sunrise;
    private String sunset;
    private String daylightDuration;
    private String sunshineDuration;
    private String uvIndexMax;
    private String uvIndexClearSkyMax;

    public String getTime() {
        return time;
    }

    public void setTime(String value) {
        this.time = value;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String value) {
        this.weatherCode = value;
    }

    public String getTemperature2MMax() {
        return temperature2MMax;
    }

    public void setTemperature2MMax(String value) {
        this.temperature2MMax = value;
    }

    public String getTemperature2MMin() {
        return temperature2MMin;
    }

    public void setTemperature2MMin(String value) {
        this.temperature2MMin = value;
    }

    public String getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    public void setApparentTemperatureMax(String value) {
        this.apparentTemperatureMax = value;
    }

    public String getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    public void setApparentTemperatureMin(String value) {
        this.apparentTemperatureMin = value;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String value) {
        this.sunrise = value;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String value) {
        this.sunset = value;
    }

    public String getDaylightDuration() {
        return daylightDuration;
    }

    public void setDaylightDuration(String value) {
        this.daylightDuration = value;
    }

    public String getSunshineDuration() {
        return sunshineDuration;
    }

    public void setSunshineDuration(String value) {
        this.sunshineDuration = value;
    }

    public String getUvIndexMax() {
        return uvIndexMax;
    }

    public void setUvIndexMax(String value) {
        this.uvIndexMax = value;
    }

    public String getUvIndexClearSkyMax() {
        return uvIndexClearSkyMax;
    }

    public void setUvIndexClearSkyMax(String value) {
        this.uvIndexClearSkyMax = value;
    }
}
