package ru._21_school.aircraft;

import ru._21_school.Coordinates;
import ru._21_school.exception.AvajException;
import ru._21_school.logger.Logger;
import ru._21_school.tower.WeatherTower;

import java.util.HashMap;
import java.util.Map;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    private static final Map<String, String> messages = new HashMap<>();
    private static final Map<String, Coordinates> offsets = new HashMap<>();

    static {
        messages.put("RAIN", "It's raining. Better watch out for lightings.");
        messages.put("FOG", "Flying on the instruments.");
        messages.put("SUN", "Turn on air conditioning.");
        messages.put("SNOW", "OMG! Winter is coming!");

        offsets.put("RAIN", new Coordinates(5, 0, 0));
        offsets.put("FOG", new Coordinates(1, 0, 0));
        offsets.put("SUN", new Coordinates(10, 0, 2));
        offsets.put("SNOW", new Coordinates(0, 0, -7));
    }

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);

        Logger.log(this + ": " + messages.get(weather));
        Coordinates offset = offsets.get(weather);
        if (offset == null) {
            throw new AvajException("Wrong weather type!");
        }
        coordinates = new Coordinates(coordinates.getLongitude() + offset.getLongitude(),
                coordinates.getLatitude() + offset.getLatitude(),
                Math.min(coordinates.getHeight() + offset.getHeight(), 100));

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
        Logger.log("Current coordinates: Longtitude: [" + this.coordinates.getLongitude()
                + "] Latitude: [" + this.coordinates.getLatitude()
                + "] Height: [" + this.coordinates.getHeight() + "]");
        weatherTower.unregister(this);
    }
}
