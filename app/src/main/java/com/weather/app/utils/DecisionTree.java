package com.weather.app.utils;

public class DecisionTree {
    public String predictActivity(double temperature, double precipitation, double windSpeed, double humidity, String condition) {
        if (condition.equalsIgnoreCase("Rainy") || precipitation > 5) {
            return "Stay Indoors";
        } else if (temperature > 20 && temperature < 30 && windSpeed < 15) {
            return "Go Hiking";
        } else if (temperature > 30 && humidity < 50) {
            return "Go Swimming";
        } else {
            return "Relax at Home";
        }
    }
}