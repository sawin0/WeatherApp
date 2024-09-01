package com.weather.app.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.weather.app.data.api.WeatherApiService;
import com.weather.app.data.model.WeatherApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    private final WeatherApiService weatherApiService;

    public WeatherRepository(WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }

    public LiveData<WeatherApiResponse> getWeather(double latitude, double longitude) {
        MutableLiveData<WeatherApiResponse> weatherData = new MutableLiveData<>();

        weatherApiService.getWeatherForecast(latitude, longitude,
                        "temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,rain,showers,snowfall,weather_code,cloud_cover,pressure_msl,surface_pressure,wind_speed_10m",
                        "temperature_2m,weather_code",
                        "weather_code,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,sunrise,sunset,daylight_duration,sunshine_duration,uv_index_max,uv_index_clear_sky_max", "unixtime")
                .enqueue(new Callback<WeatherApiResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherApiResponse> call, @NonNull Response<WeatherApiResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            weatherData.setValue(response.body());
                        } else {
                            weatherData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherApiResponse> call, @NonNull Throwable t) {
                        weatherData.setValue(null);
                    }
                });

        return weatherData;
    }
}
