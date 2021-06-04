package ru._21_school.aircraft;

import ru._21_school.Coordinates;
import ru._21_school.Offset;
import ru._21_school.exception.AvajException;
import ru._21_school.logger.Logger;
import ru._21_school.tower.WeatherTower;

import java.util.HashMap;
import java.util.Map;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    private static final Map<String, String> messages = new HashMap<>();
    private static final Map<String, Offset> offsets = new HashMap<>();

    static {
        messages.put("RAIN", "Wipers is broken!");
        messages.put("FOG", "Low visibility.");
        messages.put("SUN", "This is hot.");
        messages.put("SNOW", "My rotor is going to freeze!");

        offsets.put("RAIN", new Offset(5, 0, 0));
        offsets.put("FOG", new Offset(1, 0, 0));
        offsets.put("SUN", new Offset(10, 0, 2));
        offsets.put("SNOW", new Offset(0, 0, -12));
    }

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);

        Logger.log(this + ": " + messages.get(weather));
        Offset offset = offsets.get(weather);

        if (offset == null) {
            throw new AvajException("Wrong weather type!");
        }

        coordinates = coordinates.add(offset);

        if (isGround()) {
            landing();
        }
    }

    @Override
    public void registerTowel(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
    }

    private void landing() {
        Logger.log(this + ": landing.");
        weatherTower.unregister(this);
    }
}
