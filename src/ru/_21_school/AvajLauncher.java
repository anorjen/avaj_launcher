package ru._21_school;

import ru._21_school.aircraft.AircraftFactory;
import ru._21_school.aircraft.Flyable;
import ru._21_school.exception.AvajException;
import ru._21_school.logger.Logger;
import ru._21_school.tower.WeatherTower;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AvajLauncher {

    public static void main(String[] args) {


        WeatherTower weatherTower = new WeatherTower();
        try {

            if (args.length < 1) {
                throw new AvajException("No scenario file!");
            }

            Logger.start();

            ScenarioReader reader = new ScenarioReader(args[0]);
            String line;

            while ((line = reader.getLine()) != null) {
                Flyable flyable = getAircraft(line);
                flyable.registerTowel(weatherTower);
            }
            int counter = reader.getChangeCounter();

            for (int i = 0; i < counter; i++) {
                weatherTower.conditionsChanged();
            }
            Logger.stop();
        } catch (AvajException e) {
            System.err.println(e.getMessage());
        }

    }

    public static Flyable getAircraft(String line) throws AvajException {
        Pattern pattern = Pattern.compile(ScenarioReader.SCENARIO_REGEX);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {

            return AircraftFactory.newAircraft(matcher.group(1), matcher.group(2), Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)));
        }
        throw new AvajException("Wrong line!");
    }
}
