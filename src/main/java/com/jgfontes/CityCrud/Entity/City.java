package com.jgfontes.CityCrud.Entity;

public final class City {
    private final String name;
    private final String state;

    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }
}
