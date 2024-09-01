package com.weather.app.data.api;

import com.weather.app.data.model.WeatherApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("v1/forecast")
    Call<WeatherApiResponse> getWeatherForecast(
            @Query("latitude") double latitude,
            @Query("longitude") double longitude,
            @Query("current") String current,
            @Query("hourly") String hourly,
            @Query("daily") String daily,
            @Query("timeformat") String timeFormat
    );
}
