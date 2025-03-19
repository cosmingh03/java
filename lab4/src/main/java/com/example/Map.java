package com.example;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<Location> locations;
    private Location start;

    public Map() {
        start = null;
        locations = new ArrayList<>();

    }

    public Map(Location start, List<Location> locations) {
        this.start = start;
        this.locations = locations;
    }

    public Location getStart() {
        return start;
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public boolean isLocationInMapp(String name) {
        for (Location location : locations) {
            if (location.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean canMooveDireclty(Location A, Location B) {

        //

        return false;

    }

    public int timeNeededToMove(Location A, Location B) {

        //

        return 0;
    }

    public int safetyProbability(Location A, Location B) {

        //

        return 0;
    }

}