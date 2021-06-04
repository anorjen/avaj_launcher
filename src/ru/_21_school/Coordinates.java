package ru._21_school;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    public Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        if (height > 100) {
            this.height = 100;
        } else {
            this.height = Math.max(height, 0);
        }
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public Coordinates add(Offset offset) {
        return new Coordinates(this.getLongitude() + offset.getLongitude(),
                                this.getLatitude() + offset.getLatitude(),
                                this.getHeight() + offset.getHeight());
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", height=" + height +
                '}';
    }
}