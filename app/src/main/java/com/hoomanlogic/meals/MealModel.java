package com.hoomanlogic.meals;

/**
 * Created by owner on 10/22/16.
 */

public class MealModel {
    public String Name;
    public int MinutesToPrepare;

    public MealModel(String name, int minutesToPrepare) {
        this.Name = name;
        this.MinutesToPrepare = minutesToPrepare;
    }
}
