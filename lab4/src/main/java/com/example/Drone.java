package com.example;

public class Drone {
    private String name;
    private Location lastLocation;
    private Location location;

    public Drone() {
    }

    public Drone(String name, Location location) {
        this.name = name;
        this.location = location;

    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    public void move(Location location) {
        lastLocation = this.location;
        this.location = location;

    }

}
