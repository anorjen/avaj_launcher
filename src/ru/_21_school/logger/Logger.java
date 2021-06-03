package ru._21_school.logger;

import ru._21_school.exception.AvajException;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Logger {
    private static final String outfile = "simulation.txt";
    private static BufferedWriter writer;

    public static void start() throws AvajException {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outfile), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            throw new AvajException("File open ERROR!");
        }
    }

    public static void log(String message) {
        try {
            writer.write(message + "\n");
        } catch (IOException e) {
            System.err.println("File write ERROR!");
        }
    }

    public static void stop() throws AvajException {
        try {
            writer.close();
        } catch (IOException e) {
            throw new AvajException("File close ERROR!");
        }
    }
}
