package com.autodidact.gympulse.entity;

import com.autodidact.gympulse.GymPulse;
import com.autodidact.gympulse.entity.Exercise;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
/**
 * Created by timcoulson on 04/12/14.
 */
public class Session implements Serializable {
    private List<Exercise> exercises;
    private String name = "Default";
    private Date date;
    private int id;

    public Session(){

    }

    public Session(String name, List<Exercise> exercises){
        this.exercises = exercises;
        this.name = name;
        this.id = 3 * name.hashCode() + 5 * exercises.toString().hashCode();
    }

    public Session(String name, List<Exercise> exercises, Date date){
        this.exercises = exercises;
        this.name = name;
        this.date = date;
        this.id = 3 * name.hashCode() + 5 * exercises.toString().hashCode();
    }

    public void clearSession() {
        for(Exercise e : exercises){
           e.clearExercise();
           e.applyIncrement();
        }
    }

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

    public Date getDate() {
        return date;
    }

    //TODO rename to timestamp
    public void setDate(){
        Calendar cal = Calendar.getInstance();
        this.date = cal.getTime();
    }

}
