package com.example.fitnessandnutritionappfinal;

public class ExerciseItem {
    private String name;
    private int reps, sets, weight;

    public ExerciseItem(String _name, int _reps, int _sets, int _weight){
        this.name = _name;
        this.reps = _reps;
        this.sets = _sets;
        this.weight = _weight;
    }
    //GETTERS AND SETTERS
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getReps() {
        return reps;
    }
    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }
    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    //==================

    public String toString(){
        String rtn = name + ": ";
        rtn += "reps: " + reps + " sets: " + sets + " weight: " + weight;
        return rtn;
    }
}
