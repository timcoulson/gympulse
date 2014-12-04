package com.autodidact.gympulse.entity;

import java.util.List;

/**
 * Created by timcoulson on 04/12/14.
 */
public class Session {
    private List<Exercise> exercises;
    private String name;

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
