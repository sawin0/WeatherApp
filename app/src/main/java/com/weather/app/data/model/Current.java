package com.weather.app.data.model;

public class Current {
    private String time;
    private long interval;
    private double temperature2M;
    private long isDay;
    private long weatherCode;

    public String getTime() {
        return time;
    }

    public void setTime(String value) {
        this.time = value;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long value) {
        this.interval = value;
    }

    public double getTemperature2M() {
        return temperature2M;
    }

    public void setTemperature2M(double value) {
        this.temperature2M = value;
    }

    public long getIsDay() {
        return isDay;
    }

    public void setIsDay(long value) {
        this.isDay = value;
    }

    public long getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(long value) {
        this.weatherCode = value;
    }
}
