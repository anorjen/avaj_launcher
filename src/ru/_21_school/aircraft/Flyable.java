package ru._21_school.aircraft;

import ru._21_school.tower.WeatherTower;

public interface Flyable {

    void updateConditions();

    void registerTowel(WeatherTower weatherTower);
}
