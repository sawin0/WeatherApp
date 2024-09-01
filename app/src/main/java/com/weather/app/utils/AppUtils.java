package com.weather.app.utils;

import android.util.Log;

import com.weather.app.R;

public class AppUtils {
    public static String getWeatherDescription(int weatherCode) {
        switch (weatherCode) {
            case 0:
                return "Clear sky";
            case 1:
                return "Mainly clear";
            case 2:
                return "Partly cloudy";
            case 3:
                return "Overcast";
            case 45:
                return "Fog";
            case 48:
                return "Deposition";
            case 51:
                return "Light drizzle";
            case 52:
                return "Moderate drizzle";
            case 53:
                return "Dense drizzle";
            case 55:
                return "Light freezing drizzle";
            case 56:
                return "Freezing drizzle";
            case 57:
                return "Dense freezing drizzle";
            case 61:
                return "Slight rain";
            case 63:
                return "Moderate rain";
            case 65:
                return "Heavy rain";
            case 66:
                return "Light freezing rain";
            case 67:
                return "Freezing rain";
            case 71:
                return "Slight snowfall";
            case 73:
                return "Moderate snowfall";
            case 75:
                return "Heavy snowfall";
            case 77:
                return "Ice pellets";
            case 80:
                return "Light rain showers";
            case 81:
                return "Moderate rain showers";
            case 82:
                return "Violent rain showers";
            case 85:
                return "Light snow showers";
            case 86:
                return "Moderate snow showers";
            case 95:
                return "Weak thunderstorm";
            case 96:
                return "Thunderstorm";
            case 99:
                return "Strong thunderstorm";
            default:
                return "Unknown";
        }
    }

    public static int getWeatherIconResource(int weatherCode) {
        Log.i("TAG", "getWeatherIconResource: " + weatherCode);
        if (weatherCode >= 0 && weatherCode <= 99) {
            if (weatherCode <= 1) {
                return R.drawable.clear_day; // Clear sky
            } else if (weatherCode <= 4) {
                return R.drawable.partly_cloudy_day; // Partly cloudy
            } else if (weatherCode <= 9) {
                return R.drawable.cloudy_day_night; // Cloudy
            } else if (weatherCode <= 19) {
                return R.drawable.fog_day_night; // Fog
            } else if (weatherCode <= 29) {
                return R.drawable.drizzle_day_night; // Drizzle
            } else if (weatherCode <= 39) {
                return R.drawable.rain_day_night; // Rain
            } else if (weatherCode <= 49) {
                return R.drawable.rain_day_night; // Shower rain
            } else if (weatherCode <= 59) {
                return R.drawable.snow_day_night; // Snow
            } else if (weatherCode <= 69) {
                return R.drawable.sleet_day_night; // Sleet
            } else if (weatherCode <= 79) {
                return R.drawable.sleet_day_night; // Shower snow
            } else {
                return R.drawable.thunderstorm_day_night; // Thunderstorm
            }
        }
        return R.drawable.error; // Default case for unspecified codes
    }
}
