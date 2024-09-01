package com.weather.app.data.model;

import java.util.List;

public class WeatherApiResponse {
    private double latitude;
    private double longitude;
    private double generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private double elevation;
    private Units current_units;
    private Current current;
    private Units hourly_units;
    private Hourly hourly;
    private Units daily_units;
    private Daily daily;

    // Main class getters and setters
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getGenerationtime_ms() {
        return generationtime_ms;
    }

    public void setGenerationtime_ms(double generationtime_ms) {
        this.generationtime_ms = generationtime_ms;
    }

    public int getUtc_offset_seconds() {
        return utc_offset_seconds;
    }

    public void setUtc_offset_seconds(int utc_offset_seconds) {
        this.utc_offset_seconds = utc_offset_seconds;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone_abbreviation() {
        return timezone_abbreviation;
    }

    public void setTimezone_abbreviation(String timezone_abbreviation) {
        this.timezone_abbreviation = timezone_abbreviation;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public Units getCurrent_units() {
        return current_units;
    }

    public void setCurrent_units(Units current_units) {
        this.current_units = current_units;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Units getHourly_units() {
        return hourly_units;
    }

    public void setHourly_units(Units hourly_units) {
        this.hourly_units = hourly_units;
    }

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    public Units getDaily_units() {
        return daily_units;
    }

    public void setDaily_units(Units daily_units) {
        this.daily_units = daily_units;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public static class Units {
        private String time;
        private String interval;
        private String temperature_2m;
        private String is_day;
        private String weather_code;
        private String temperature_2m_max;
        private String temperature_2m_min;
        private String apparent_temperature_max;
        private String apparent_temperature_min;
        private String sunrise;
        private String sunset;
        private String daylight_duration;
        private String sunshine_duration;
        private String uv_index_max;
        private String uv_index_clear_sky_max;

        // Getters and Setters
        // ...


        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getInterval() {
            return interval;
        }

        public void setInterval(String interval) {
            this.interval = interval;
        }

        public String getTemperature_2m() {
            return temperature_2m;
        }

        public void setTemperature_2m(String temperature_2m) {
            this.temperature_2m = temperature_2m;
        }

        public String getIs_day() {
            return is_day;
        }

        public void setIs_day(String is_day) {
            this.is_day = is_day;
        }

        public String getWeather_code() {
            return weather_code;
        }

        public void setWeather_code(String weather_code) {
            this.weather_code = weather_code;
        }

        public String getTemperature_2m_max() {
            return temperature_2m_max;
        }

        public void setTemperature_2m_max(String temperature_2m_max) {
            this.temperature_2m_max = temperature_2m_max;
        }

        public String getTemperature_2m_min() {
            return temperature_2m_min;
        }

        public void setTemperature_2m_min(String temperature_2m_min) {
            this.temperature_2m_min = temperature_2m_min;
        }

        public String getApparent_temperature_max() {
            return apparent_temperature_max;
        }

        public void setApparent_temperature_max(String apparent_temperature_max) {
            this.apparent_temperature_max = apparent_temperature_max;
        }

        public String getApparent_temperature_min() {
            return apparent_temperature_min;
        }

        public void setApparent_temperature_min(String apparent_temperature_min) {
            this.apparent_temperature_min = apparent_temperature_min;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getDaylight_duration() {
            return daylight_duration;
        }

        public void setDaylight_duration(String daylight_duration) {
            this.daylight_duration = daylight_duration;
        }

        public String getSunshine_duration() {
            return sunshine_duration;
        }

        public void setSunshine_duration(String sunshine_duration) {
            this.sunshine_duration = sunshine_duration;
        }

        public String getUv_index_max() {
            return uv_index_max;
        }

        public void setUv_index_max(String uv_index_max) {
            this.uv_index_max = uv_index_max;
        }

        public String getUv_index_clear_sky_max() {
            return uv_index_clear_sky_max;
        }

        public void setUv_index_clear_sky_max(String uv_index_clear_sky_max) {
            this.uv_index_clear_sky_max = uv_index_clear_sky_max;
        }
    }

    public static class Current {
        private String time;
        private int interval;
        private double temperature_2m;
        private int is_day;
        private int weather_code;

        // Getters and Setters
        // ...


        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getInterval() {
            return interval;
        }

        public void setInterval(int interval) {
            this.interval = interval;
        }

        public double getTemperature_2m() {
            return temperature_2m;
        }

        public void setTemperature_2m(double temperature_2m) {
            this.temperature_2m = temperature_2m;
        }

        public int getIs_day() {
            return is_day;
        }

        public void setIs_day(int is_day) {
            this.is_day = is_day;
        }

        public int getWeather_code() {
            return weather_code;
        }

        public void setWeather_code(int weather_code) {
            this.weather_code = weather_code;
        }
    }

    public static class Hourly {
        private List<String> time;
        private List<Double> temperature_2m;
        private List<Integer> weather_code;

        // Getters and Setters
        // ...


        public List<String> getTime() {
            return time;
        }

        public void setTime(List<String> time) {
            this.time = time;
        }

        public List<Double> getTemperature_2m() {
            return temperature_2m;
        }

        public void setTemperature_2m(List<Double> temperature_2m) {
            this.temperature_2m = temperature_2m;
        }

        public List<Integer> getWeather_code() {
            return weather_code;
        }

        public void setWeather_code(List<Integer> weather_code) {
            this.weather_code = weather_code;
        }
    }

}
