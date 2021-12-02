package com.example.fitnessandnutritionappfinal;

import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RoutineItem{
    String name;
    ArrayList<ExerciseItem> exerciseItems = new ArrayList<>();
    int numOfEx, numOfExTemp;
    Button addEx;


    public RoutineItem(String name) {
        this.name = name;
        this.numOfEx = 0;
        this.numOfExTemp = 0;
    }

    //Getter and setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //Getter and setter
    public int getNumOfEx() {
        return numOfEx;
    }
    public void setNumOfEx(int numOfEx) {
        this.numOfEx = numOfEx;
    }

    //Getter and setter
    public int getNumOfExTemp() {
        return numOfExTemp;
    }
    public void setNumOfExTemp(int numOfExTemp) {
        this.numOfExTemp = numOfExTemp;
    }

    public void addExerciseItem(String name, int reps, int sets, int weight){
        exerciseItems.add(new ExerciseItem(name, reps, sets, weight));
        numOfEx++;
    }

    public String getExercisesAsStrings(int index){
        return exerciseItems.get(index).toString();
    }

}
