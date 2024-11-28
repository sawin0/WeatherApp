package com.weather.app.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.app.R;
import com.weather.app.data.model.Daily;
import com.weather.app.data.model.WeatherApiResponse;
import com.weather.app.ui.search.SearchCityActivity;
import com.weather.app.utils.AppUtils;
import com.weather.app.utils.DecisionTree;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private MainViewModel weatherViewModel;
    private TextView tvLocation, tvTemperature, tvWeatherDescription, tvSunrise, tvSunset;
    private ImageView ivWeatherIcon;
    private RecyclerView rvWeatherForecast;
    private double selectedLatitude;
    private double selectedLongitude;
    private String selectCity;

    private final ActivityResultLauncher<Intent> searchCityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        selectCity = data.getStringExtra("city");
                        selectedLatitude = data.getDoubleExtra("latitude", 0);
                        selectedLongitude = data.getDoubleExtra("longitude", 0);
                        Log.d("MainActivity", "Selected City Lat: " + selectedLatitude + ", Lng: " + selectedLongitude);
                        fetchWeatherForSelectedCity();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        tvLocation = findViewById(R.id.tvLocation);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvWeatherDescription = findViewById(R.id.tvWeatherDescription);
        tvSunrise = findViewById(R.id.tvSunrise);
        tvSunset = findViewById(R.id.tvSunset);
        ivWeatherIcon = findViewById(R.id.ivWeatherIcon);

        ImageButton btnSearchCity = findViewById(R.id.btnSearchCity);
        btnSearchCity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SearchCityActivity.class);
            searchCityLauncher.launch(intent);
        });

        // Initialize RecyclerView
        rvWeatherForecast = findViewById(R.id.rvWeatherForecast);
        rvWeatherForecast.setLayoutManager(new LinearLayoutManager(this));

        // Initialize ViewModel with factory
        MainViewModelFactory factory = new MainViewModelFactory(getApplication());
        weatherViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        // Observe LiveData from ViewModel
        weatherViewModel.getLocation().observe(this, location -> {
            if (location != null) {
                Log.d("MainActivity", "Location updated");
                weatherViewModel.getWeatherData(location.getLatitude(), location.getLongitude()).observe(this, this::updateUI);
            }
        });

        weatherViewModel.getAddress().observe(this, address -> {
            if (address != null) {
                tvLocation.setText(address);
            }
        });

        weatherViewModel.getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Log.e("MainActivity", errorMessage);
            }
        });

        weatherViewModel.getWeatherData(selectedLatitude, selectedLongitude).observe(this, this::updateUI);

        checkPermissionsAndFetchWeather();
    }

    private void checkPermissionsAndFetchWeather() {
        if (hasLocationPermissions()) {
            weatherViewModel.fetchLocation();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private boolean hasLocationPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            weatherViewModel.fetchLocation();
        } else {
            Log.e("Permissions", "Location permission denied");
        }
    }

    private void fetchWeatherForSelectedCity() {
        weatherViewModel.getWeatherData(selectedLatitude, selectedLongitude).observe(this, this::updateUI);
        tvLocation.setText(selectCity);
    }

    private void updateUI(WeatherApiResponse currentWeather) {
        if (currentWeather == null) return;

        tvTemperature.setText(String.format(Locale.getDefault(), "%.1f°C", currentWeather.getCurrent().getTemperature_2m()));
        tvWeatherDescription.setText(AppUtils.getWeatherDescription(currentWeather.getCurrent().getWeather_code()));
        tvSunrise.setText(convertUnixToLocalDateTime(Long.parseLong(currentWeather.getDaily().getSunrise()[1])));
        tvSunset.setText(convertUnixToLocalDateTime(Long.parseLong(currentWeather.getDaily().getSunset()[1])));
        ivWeatherIcon.setImageResource(AppUtils.getWeatherIconResource(currentWeather.getCurrent().getWeather_code()));

        List<Daily> dailyList = new ArrayList<>();
        dailyList.add(currentWeather.getDaily());
        WeatherAdapter weatherForecastAdapter = new WeatherAdapter(dailyList);
        rvWeatherForecast.setAdapter(weatherForecastAdapter);

        // Using Decision Tree to predict activity based on weather conditions
        DecisionTree decisionTree = new DecisionTree();
        String recommendation = decisionTree.predictActivity(currentWeather.getCurrent().getTemperature_2m(), 0, 10, 60, AppUtils.getWeatherDescription(currentWeather.getCurrent().getWeather_code()));
        Toast.makeText(this, "Recommendation: " + recommendation, Toast.LENGTH_SHORT).show();
    }

    private String convertUnixToLocalDateTime(long unixTime) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(unixTime), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return localDateTime.format(formatter);
    }
}