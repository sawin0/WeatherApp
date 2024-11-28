package com.weather.app.utils;

import java.util.Calendar;

public class WeatherDecisionMaker {

    public static String evaluateWeather(double temp, String weatherStatus) {
        // Get current hour
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        // Determine time of day
        if (hour >= 6 && hour < 12) { // Morning
            if (temp < 10) {
                return "Morning: It's cold. Wear warm clothes.";
            } else if (weatherStatus.equalsIgnoreCase("Rainy")) {
                return "Morning: It's rainy. Don't forget your umbrella.";
            }
            return "Morning: The weather looks good. Have a great day!";
        } else if (hour >= 12 && hour < 18) { // Afternoon
            if (temp > 30) {
                return "Afternoon: It's hot. Stay hydrated!";
            } else if (weatherStatus.equalsIgnoreCase("Clear")) {
                return "Afternoon: Clear skies. Perfect for outdoor activities.";
            }
            return "Afternoon: The weather seems fine. Enjoy your day!";
        } else { // Evening/Night
            if (temp < 15) {
                return "Evening: It's chilly. Wear a jacket.";
            } else if (weatherStatus.equalsIgnoreCase("Snowy")) {
                return "Evening: Snowfall expected. Drive carefully.";
            }
            return "Evening: Have a relaxing night!";
        }
    }
}
