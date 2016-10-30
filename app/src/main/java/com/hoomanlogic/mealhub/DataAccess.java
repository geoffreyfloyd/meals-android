package com.hoomanlogic.mealhub;

import java.util.ArrayList;
import java.util.List;

public class DataAccess {
    private static List<GroceryModel> _GroceryList = new ArrayList<>();
    private static List<MealModel> _MealList = new ArrayList<>();

    public static GroceryModel getGrocery(String groceryId) throws Exception {
        // Open an existing grocery to edit
        for (GroceryModel groceryModel : DataAccess.getGroceries()) {
            if (groceryModel.Id.equals(groceryId)) {
                return groceryModel;
            }
        }
        throw new Exception("Grocery does not exist");
    }

    public static List<GroceryModel> getGroceries() {
        // Pseudo-database
        if (_GroceryList.size() == 0) {
            _GroceryList.add(new GroceryModel("Bananas", 5));
            _GroceryList.add(new GroceryModel("Onion"));
            _GroceryList.add(new GroceryModel("Tomato"));
        }
        return _GroceryList;
    }

    public static List<MealModel> getMeals() {
        // Pseudo-database
        if (_MealList.size() == 0) {
            _MealList.add(new MealModel("Smoothie", 7));
            _MealList.add(new MealModel("Vegetable Noodle Soup", 15));
            _MealList.add(new MealModel("PB&J Sammy", 5));
        }
        return _MealList;
    }
}
