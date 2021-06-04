package ru._21_school;

import ru._21_school.exception.AvajException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ScenarioReader {

    public static String SCENARIO_REGEX = "^([A-Za-z]+) ([A-Za-z0-9]+) (\\d+) (\\d+) (\\d+)";
    private List<String> lines = new ArrayList<>();
    private int changeCounter;
    private int lineCounter = 0;

    public ScenarioReader(String file) throws AvajException {
        readLines(file);
        checkLines(lines);
    }

    public String getLine() {
        if (lineCounter < lines.size()) {
            return lines.get(lineCounter++);
        }
        return null;
    }

    private void checkLines(List<String> lines) throws AvajException {
        for (String l : lines) {
            if (!l.matches(SCENARIO_REGEX)) {
                throw new AvajException("File format ERROR!");
            }
        }
    }

    public int getChangeCounter() {
        return changeCounter;
    }

    private void readLines(String file) throws AvajException {

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {

                lines.add(line);
            }
        } catch (IOException e) {
            throw new AvajException("File read ERROR!");
        }

        try {
            changeCounter = Integer.parseInt(lines.get(0));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new AvajException("File format ERROR!");
        }
        lines.remove(0);
        if (lines.isEmpty()) {
            throw new AvajException("File format ERROR (no aircrafts)!");
        }
    }
}
