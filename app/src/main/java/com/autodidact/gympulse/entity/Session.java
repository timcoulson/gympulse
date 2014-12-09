package com.autodidact.gympulse.entity;

import com.autodidact.gympulse.GymPulse;
import com.autodidact.gympulse.entity.Exercise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
/**
 * Created by timcoulson on 04/12/14.
 */
public class Session implements Serializable {
    private ArrayList<Exercise> exercises;
    private String name = "Default";
    private Date date;
    private int id;

    public Session(){

    }

    public Session(String name, ArrayList<Exercise> exercises){
        this.exercises = exercises;
        this.name = name;
        this.id = 3 * name.hashCode() + 5 * exercises.toString().hashCode();
    }

    public Session(String name, ArrayList<Exercise> exercises, Date date){
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

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
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

    public Exercise addExercise(){
        Exercise e = new Exercise("Exercise #", 5, 5, 90, 0, 0);
        exercises.add(e);
        return e;
    }

    public void deleteLastExercise(){
        exercises.remove(exercises.size()-1);
    }


    //TODO rename to timestamp
    public void setDate(){
        Calendar cal = Calendar.getInstance();
        this.date = cal.getTime();
    }

}
