package com.example.fitnessandnutritionappfinal;

import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RoutineItem{
    String name;
    ArrayList<ExerciseItem> exerciseItems = new ArrayList<>();
    int numOfEx, numOfExTemp;
    int idNum;
    Button addEx;


    public RoutineItem(String name) {
        this.name = name;
        this.numOfEx = 0;
        this.numOfExTemp = 0;
        this.idNum = -1;
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

    //Getter and setter
    public int getIdNum() {
        return idNum;
    }
    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public void addExerciseItem(String name, int reps, int sets, int weight){
        exerciseItems.add(new ExerciseItem(name, reps, sets, weight));
        numOfEx++;
    }
    //Returns 1 for valid removal -1 for invalid
    public int rmvExerciseItem(int index){
        String debugMsg = String.valueOf(index) + " | " + String.valueOf(numOfEx-1);
        Log.d("exRmv: ", debugMsg);
        if(index > (numOfEx - 1) || index < 0){
            return -1;
        }
        else {
            exerciseItems.remove(index);
            numOfEx--;
            numOfExTemp--;
            return 1;
        }
    }

    public void reset(){
        name = "";
        numOfEx = 0;
        numOfExTemp = 0;
        idNum = -1;
        exerciseItems = new ArrayList<>();
    }

    public String getExercisesAsStrings(int index){
        return exerciseItems.get(index).toString();
    }

}
