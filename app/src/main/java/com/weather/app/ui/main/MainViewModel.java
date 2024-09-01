package com.weather.app.ui.main;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.weather.app.data.api.RetrofitClient;
import com.weather.app.data.api.WeatherApiService;
import com.weather.app.data.model.WeatherApiResponse;
import com.weather.app.data.repository.WeatherRepository;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class MainViewModel extends AndroidViewModel {
    private final FusedLocationProviderClient fusedLocationClient;
    private final WeatherRepository weatherRepository;

    private final MutableLiveData<Location> location = new MutableLiveData<>();
    private final MutableLiveData<String> address = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        WeatherApiService weatherApiService = RetrofitClient.getClient().create(WeatherApiService.class);
        weatherRepository = new WeatherRepository(weatherApiService);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(application);
    }

    public LiveData<Location> getLocation() {
        return location;
    }

    public LiveData<WeatherApiResponse> getWeatherData(double latitude, double longitude) {
        return weatherRepository.getWeather(latitude, longitude);
    }

    public LiveData<String> getAddress() {
        return address;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            errorMessage.setValue("Location permission not granted");
            return;
        }

        fusedLocationClient.getLastLocation().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                location.setValue(task.getResult());
                fetchAddress(task.getResult());
            } else {
                errorMessage.setValue("Failed to get location");
            }
        });
    }

    private void fetchAddress(Location location) {
        Geocoder geocoder = new Geocoder(getApplication(), Locale.getDefault());
        try {
            Address address = Objects.requireNonNull(geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1))
                    .stream().findFirst().orElse(null);
            this.address.setValue(address != null && address.getSubLocality() != null ? address.getLocality() : "Location not found");
        } catch (IOException e) {
            errorMessage.setValue("Unable to get address: " + e.getMessage());
        }
    }
}
