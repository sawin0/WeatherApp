package com.weather.app.data.model;

public class Daily {
    private long[] time;
    private long[] weather_code;
    private double[] temperature_2m_max;
    private double[] temperature_2m_min;
    private double[] apparentTemperatureMax;
    private double[] apparentTemperatureMin;
    private String[] sunrise;
    private String[] sunset;
    private double[] daylightDuration;
    private double[] sunshineDuration;
    private double[] uvIndexMax;
    private double[] uvIndexClearSkyMax;

    public long[] getTime() {
        return time;
    }

    public void setTime(long[] value) {
        this.time = value;
    }

    public long[] getWeather_code() {
        return weather_code;
    }

    public void setWeather_code(long[] value) {
        this.weather_code = value;
    }

    public double[] getTemperature_2m_max() {
        return temperature_2m_max;
    }

    public void setTemperature_2m_max(double[] value) {
        this.temperature_2m_max = value;
    }

    public double[] getTemperature_2m_min() {
        return temperature_2m_min;
    }

    public void setTemperature_2m_min(double[] value) {
        this.temperature_2m_min = value;
    }

    public double[] getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    public void setApparentTemperatureMax(double[] value) {
        this.apparentTemperatureMax = value;
    }

    public double[] getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    public void setApparentTemperatureMin(double[] value) {
        this.apparentTemperatureMin = value;
    }

    public String[] getSunrise() {
        return sunrise;
    }

    public void setSunrise(String[] value) {
        this.sunrise = value;
    }

    public String[] getSunset() {
        return sunset;
    }

    public void setSunset(String[] value) {
        this.sunset = value;
    }

    public double[] getDaylightDuration() {
        return daylightDuration;
    }

    public void setDaylightDuration(double[] value) {
        this.daylightDuration = value;
    }

    public double[] getSunshineDuration() {
        return sunshineDuration;
    }

    public void setSunshineDuration(double[] value) {
        this.sunshineDuration = value;
    }

    public double[] getUvIndexMax() {
        return uvIndexMax;
    }

    public void setUvIndexMax(double[] value) {
        this.uvIndexMax = value;
    }

    public double[] getUvIndexClearSkyMax() {
        return uvIndexClearSkyMax;
    }

    public void setUvIndexClearSkyMax(double[] value) {
        this.uvIndexClearSkyMax = value;
    }
}
