package com.weather.app.data.model;

public class Hourly {
    private String[] time;
    private double[] temperature2M;
    private long[] weatherCode;

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] value) {
        this.time = value;
    }

    public double[] getTemperature2M() {
        return temperature2M;
    }

    public void setTemperature2M(double[] value) {
        this.temperature2M = value;
    }

    public long[] getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(long[] value) {
        this.weatherCode = value;
    }
}
