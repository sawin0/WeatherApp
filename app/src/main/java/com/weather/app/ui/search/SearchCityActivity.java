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

        etSearchCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filteredCityList = fuzzySearch(cityList, s.toString());
                adapter.clear();
                adapter.addAll(filteredCityList.stream().map(City::getName).collect(Collectors.toList()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

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

    private List<City> fuzzySearch(List<City> cities, String query) {
        return cities.stream()
                .filter(city -> fuzzyMatch(city.getName(), query))
                .collect(Collectors.toList());
    }

    private boolean fuzzyMatch(String text, String query) {
        int textIndex = 0;
        int queryIndex = 0;

        while (textIndex < text.length() && queryIndex < query.length()) {
            if (Character.toLowerCase(text.charAt(textIndex)) == Character.toLowerCase(query.charAt(queryIndex))) {
                queryIndex++;
            }
            textIndex++;
        }

        return queryIndex == query.length();
    }
}