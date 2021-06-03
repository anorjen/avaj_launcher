package ru._21_school;

import java.util.Arrays;
import java.util.List;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private static final List<String> weather = Arrays.asList("RAIN", "FOG", "SUN", "SNOW");

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return weather.get((coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude()) % 4);
    }

}
