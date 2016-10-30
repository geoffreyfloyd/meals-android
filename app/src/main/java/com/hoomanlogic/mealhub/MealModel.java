package com.hoomanlogic.mealhub;

import com.hoomanlogic.util.Format;

import java.util.ArrayList;
import java.util.List;

public class MealModel {

    public String Id;
    public String Name;
    public int MinutesToPrepare;
    public int MinutesToWait;
    public List<GroceryModel> Groceries;

    public MealModel() {}

    public MealModel(String name, int minutesToPrepare) {
        this.Id = Format.toSlug(name);
        this.Name = name;
        this.MinutesToPrepare = minutesToPrepare;
        this.MinutesToWait = 0;
        this.Groceries = new ArrayList<>();
    }
}
