# Weather App

This is a Weather App for Android that allows users to search for and select cities, view weather information, and get weather descriptions and icons based on weather codes.

## Description

The Weather App provides users with real-time weather information for cities across Nepal. By utilizing a fuzzy search algorithm, users can quickly find and select cities to view current weather data, including temperature, weather descriptions, and corresponding icons.

## Features

- **City Search**: Search and select cities using a fuzzy search algorithm for partial matches.
- **Weather Information**: Display current weather details, including temperature and weather conditions.
- **Weather Icons**: Show weather icons based on weather codes.
- **Localized City Data**: Includes a list of cities specifically from Nepal.

## Project Structure

- `app/src/main/java/com/weather/app/ui/search/SearchCityActivity.java`: Activity for searching and selecting a city.
- `app/src/main/java/com/weather/app/utils/AppUtils.java`: Utility class for getting weather descriptions and icons.
- `app/src/main/java/com/weather/app/utils/NepalCities.java`: Utility class for getting a list of cities in Nepal.
- `app/src/main/java/com/weather/app/data/model/City.java`: Model class representing a city.
- `app/src/main/java/com/weather/app/data/model/CurrentUnits.java`: Model class representing current weather units.

## Algorithms

### Fuzzy Search Algorithm

- **Purpose**: To filter the list of cities based on the user's input in a way that allows for partial matches.
- **Implementation**: The `fuzzySearch` method filters the list of cities by checking if the characters in the query appear in the same order in the city name, but not necessarily consecutively.

### Linear Search Algorithm

- **Purpose**: To find the correct city from the filtered list when an item is clicked.
- **Implementation**: The `lvCities.setOnItemClickListener` method retrieves the selected city from the filtered list based on the position of the clicked item.

### Java's built-in sorting algorithm (TimSort)

- **Purpose**: To sort the list of cities alphabetically by their names when the floating action button (FAB) is clicked, providing users with an organized view of the cities.
- **Implementation**: The sorting logic is triggered by the FAB's OnClickListener. The list of cities is sorted using Java's List.sort() method, which applies TimSort (a hybrid sorting algorithm). Sorting is performed lexicographically (case-insensitive) by comparing city names using the compareToIgnoreCase method: `filteredCityList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));` When toggled off, the original city order is restored by resetting filteredCityList to a copy of the unsorted cityList.

### Decision Tree Algorithm

- **Purpose**: To determine the appropriate toast message based on the weather code provided by the API.
- **Implementation**: The `getWeatherDescription` method in the `DecisionTree` class uses a decision tree to map weather codes to corresponding message.

## Prerequisites

- Android Studio 4.0 or higher
- Minimum SDK version: 21
- Internet connection for fetching real-time weather data
- API key for accessing the weather data (if required)

## Usage

1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the app on an Android device or emulator.

## Dependencies

- Retrofit for API requests
- Glide for loading images
- Gson for JSON parsing

## Code Examples

### SearchCityActivity

```java
public class SearchCityActivity extends AppCompatActivity {
    // Example method to handle city search
    private void searchCity(String query) {
        List<City> filteredCities = fuzzySearch(query);
        // Update UI with filtered list
    }
}
