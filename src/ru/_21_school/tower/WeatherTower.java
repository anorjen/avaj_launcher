package ru._21_school.tower;

import ru._21_school.Tower;
import ru._21_school.WeatherProvider;
import ru._21_school.Coordinates;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    void changeWeather() {
        conditionsChanged();

    }
}
