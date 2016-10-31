package com.hoomanlogic.mealhub;

import android.content.Context;
import android.os.Environment;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {
    private static DatabaseReference _Db = FirebaseDatabase.getInstance().getReference();
    private static List<GroceryModel> _GroceryList = new ArrayList<>();
    private static List<MealModel> _MealList = new ArrayList<>();

    public static void setGroceries(List<GroceryModel> list) {
        _GroceryList = list;
    }

    public static void setMeals(List<MealModel> list) {
        _MealList = list;
    }

    public static GroceryModel getGrocery(String groceryId) throws Exception {
        // Open an existing grocery to edit
        for (GroceryModel groceryModel : _GroceryList) {
            if (groceryModel.Id.equals(groceryId)) {
                return groceryModel;
            }
        }
        throw new Exception("Grocery does not exist");
    }

    public static void saveGrocery(GroceryModel model) {
        _Db.child("data").child("groceries").child(model.Id).setValue(model);
    }

    public static void saveMeal(MealModel model) {
        _Db.child("data").child("meals").child(model.Id).setValue(model);
    }

}
