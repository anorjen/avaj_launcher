package ru._21_school.aircraft;

import ru._21_school.Coordinates;
import ru._21_school.Offset;
import ru._21_school.exception.AvajException;
import ru._21_school.logger.Logger;
import ru._21_school.tower.WeatherTower;

import java.util.HashMap;
import java.util.Map;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    private static final Map<String, String> messages = new HashMap<>();
    private static final Map<String, Offset> offsets = new HashMap<>();

    static {
        messages.put("RAIN", "Damn you rain! You messed up my baloon.");
        messages.put("FOG", "Don`t smoky, please.");
        messages.put("SUN", "Let's enjoy the good weather and take some pics.");
        messages.put("SNOW", "It's snowing. We're gonna crash.");

        offsets.put("RAIN", new Offset(0, 0, -5));
        offsets.put("FOG", new Offset(0, 0, -3));
        offsets.put("SUN", new Offset(2, 0, 4));
        offsets.put("SNOW", new Offset(0, 0, -15));
    }

    Baloon(String name, Coordinates coordinates) {
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
