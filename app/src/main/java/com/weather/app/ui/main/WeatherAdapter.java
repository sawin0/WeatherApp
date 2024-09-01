package com.weather.app.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.app.R;
import com.weather.app.data.model.Daily;
import com.weather.app.utils.AppUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private final List<Daily> dailyData;

    public WeatherAdapter(List<Daily> dailyData) {
        this.dailyData = dailyData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Daily daily = dailyData.get(0);

        // Example: Setting date (adjust as per your JSON structure)
        if (daily.getTime().length != 0) {
            long unixTime = daily.getTime()[position]; // Assuming the first entry is the date
            Date date = new Date(unixTime * 1000); // Convert Unix timestamp to milliseconds
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
            String formattedDate = sdf.format(date);
            holder.tvDate.setText(formattedDate);
        }

        // Example: Setting weather icon (adjust as per your JSON structure)
        if (daily.getWeather_code() != null && daily.getWeather_code().length != 0) {
            int weatherCode = (int) daily.getWeather_code()[position];// Assuming the first entry is the weather code
            holder.ivWeatherIcon.setImageResource(AppUtils.getWeatherIconResource(weatherCode));
        }

        // Example: Setting max and min temperature (adjust as per your JSON structure)
        if (daily.getTemperature_2m_max() != null && daily.getTemperature_2m_min() != null && daily.getTemperature_2m_max().length != 0 && daily.getTemperature_2m_min().length != 0) {
            double maxTemp = daily.getTemperature_2m_max()[position]; // Assuming the first entry is max temperature
            double minTemp = daily.getTemperature_2m_min()[0]; // Assuming the first entry is min temperature
            holder.tvTemperature.setText(String.format("%s° / %s°", maxTemp, minTemp));
        }
    }

    @Override
    public int getItemCount() {
        return dailyData.get(0).getSunrise().length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate;
        ImageView ivWeatherIcon;
        TextView tvTemperature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            ivWeatherIcon = itemView.findViewById(R.id.ivWeatherIcon);
            tvTemperature = itemView.findViewById(R.id.tvTemperature);
        }
    }

}
