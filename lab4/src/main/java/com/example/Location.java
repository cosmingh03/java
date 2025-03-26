package com.example;

public class Location implements Comparable<Location> {
    private String name;
    private Type type;

    public Location(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public Location() {
    }

    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public int compareTo(Location other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isEnemy() {
        return type == Type.ENEMY;
    }

    public boolean isFriendly() {
        return type == Type.FRIENDLY;
    }

    public boolean isNeutral() {
        return type == Type.NEUTRAL;
    }

}