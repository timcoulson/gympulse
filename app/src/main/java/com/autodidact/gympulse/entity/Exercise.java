package com.autodidact.gympulse.entity;

/**
 * Created by timcoulson on 04/12/14.
 */
public class Exercise {
    private String name;
    private int sets;
    private int reps;
    private int rest;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }
}
