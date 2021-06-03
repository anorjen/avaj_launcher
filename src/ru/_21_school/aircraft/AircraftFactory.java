package ru._21_school.aircraft;

import ru._21_school.Coordinates;
import ru._21_school.exception.AvajException;

public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws AvajException {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        Flyable flyable;

        switch (type) {
            case ("Helicopter"):
                flyable = new Helicopter(name, coordinates);
                break ;
            case ("JetPlane"):
                flyable = new JetPlane(name, coordinates);
                break;
            case ("Baloon"):
                flyable = new Baloon(name, coordinates);
                break ;
            default:
                throw new AvajException("Wrong aircraft type!");
        }
        return flyable;
    }
}
