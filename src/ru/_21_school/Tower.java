package ru._21_school;

import ru._21_school.aircraft.Flyable;
import ru._21_school.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        Logger.log("Tower says: " + flyable + " registered to weather tower.");
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        Logger.log("Tower says: " + flyable + " unregistered from weather tower.");
        observers.remove(flyable);
    }

    protected void conditionsChanged(){
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
    }
}