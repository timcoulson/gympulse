package com.autodidact.gympulse.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by timcoulson on 04/12/14.
 */
public class Exercise implements Serializable {
    private String name;
    private int sets;
    private int reps;
    private int rest;
    private float weight;
    private ArrayList<Integer> loggedSets = new ArrayList<Integer>(sets);
    private float increment = 2.5f;

    public Exercise(String name, int sets, int reps, int rest, float weight, float increment){
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.rest = rest;
        this.weight = weight + increment;
        // Using an intialised array, because I fear null pointers
        for (int i = 0; i < sets; i++){
            this.loggedSets.add(-1);
        }
    }

    public void keepSameWeight(){
        increment = 0f;
    }

    public void reduceWeight(){
        increment = -2.5f;
    }

    public void increaseWeight(){
        increment = 2.5f;
    }

    public void applyIncrement(){
        weight = weight + increment;
    }

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
        this.loggedSets = new ArrayList<Integer>();
        this.sets = sets;
        for (int i = 0; i < sets; i++){
            this.loggedSets.add(-1);
        }

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

    public void setWeight(float weight){
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public void addLoggedSet(int reps){
        this.loggedSets.add(reps);
    }

    public int getLoggedReps(int setNumber){
        return this.loggedSets.get(setNumber);
    }

    public void resetLoggedReps(int setNumber){
        this.loggedSets.set(setNumber, 0);
    }


    public void clearExercise(){
        this.loggedSets= new ArrayList<Integer>(sets);
        for (int i = 0; i < sets; i++){
            this.loggedSets.add(-1);
        }
    }

    public int decrementRep(int setNumber){
        if(notStartedSet(setNumber) || this.loggedSets.get(setNumber) == 0) {
            this.loggedSets.set(setNumber, reps);
        } else {
            this.loggedSets.set(setNumber, this.loggedSets.get(setNumber) - 1);
        }
        return this.loggedSets.get(setNumber);
    }

    public boolean notStartedSet(int setNumber){
        return (this.loggedSets.get(setNumber)<0);
    }
}
