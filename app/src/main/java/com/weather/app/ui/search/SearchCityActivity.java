package com.weather.app.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.weather.app.R;
import com.weather.app.data.model.City;
import com.weather.app.utils.NepalCities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchCityActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private List<City> cityList;
    private List<City> filteredCityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);

        EditText etSearchCity = findViewById(R.id.etSearchCity);
        ListView lvCities = findViewById(R.id.lvCities);

        // Initialize city list and adapter
        cityList = NepalCities.getCities();
        filteredCityList = new ArrayList<>(cityList);
        List<String> cityNames = cityList.stream().map(City::getName).collect(Collectors.toList());
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityNames) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setTextColor(ContextCompat.getColor(getContext(), android.R.color.black));
                return view;
            }

            @Override
            public int getCount() {
                return filteredCityList.size();
            }

            @Override
            public String getItem(int position) {
                return filteredCityList.get(position).getName();
            }

            @Override
            public boolean isEnabled(int position) {
                return true;
            }
        };
        lvCities.setAdapter(adapter);

        // Add text watcher for search functionality
        etSearchCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString().trim();
                if (searchText.isEmpty()) {
                    // Show all cities when search field is empty
                    filteredCityList = new ArrayList<>(cityList);
                } else {
                    // Perform fuzzy search when there's text
                    filteredCityList = fuzzySearch(cityList, searchText);
                }
                adapter.clear();
                adapter.addAll(filteredCityList.stream().map(City::getName).collect(Collectors.toList()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Handle city selection
        lvCities.setOnItemClickListener((parent, view, position, id) -> {
            if (!filteredCityList.isEmpty()) {
                City selectedCity = filteredCityList.get(position);
                Log.d("SearchCityActivity", "Selected City: " + selectedCity.getName() +
                        ", Lat: " + selectedCity.getLatitude() +
                        ", Lng: " + selectedCity.getLongitude());

                Intent resultIntent = new Intent();
                resultIntent.putExtra("city", selectedCity.getName());
                resultIntent.putExtra("latitude", selectedCity.getLatitude());
                resultIntent.putExtra("longitude", selectedCity.getLongitude());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    // Improved fuzzy search using Levenshtein distance
    private List<City> fuzzySearch(List<City> cities, String query) {
        if (query == null || query.trim().isEmpty()) {
            return new ArrayList<>(cities);
        }
        return cities.stream()
                .filter(city -> similarity(city.getName(), query) > 0.4) // Adjust threshold as needed
                .sorted((c1, c2) -> Double.compare(
                        similarity(c2.getName(), query), similarity(c1.getName(), query))) // Sort by similarity
                .collect(Collectors.toList());
    }

    // Calculate similarity using normalized Levenshtein distance
    private double similarity(String text, String query) {
        int maxLength = Math.max(text.length(), query.length());
        if (maxLength == 0) return 1.0;
        return (maxLength - levenshteinDistance(text.toLowerCase(), query.toLowerCase())) / (double) maxLength;
    }

    // Levenshtein distance implementation
    private int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Math.min(
                            Math.min(
                                    dp[i - 1][j] + 1,       // Deletion
                                    dp[i][j - 1] + 1        // Insertion
                            ),
                            dp[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1) // Substitution
                    );
                }
            }
        }
        return dp[a.length()][b.length()];
    }
}
