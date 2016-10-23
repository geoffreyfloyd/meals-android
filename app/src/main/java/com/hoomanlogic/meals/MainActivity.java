package com.hoomanlogic.meals;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Build Meals List
        ArrayList<MealModel> meals = new ArrayList<>();
        meals.add(new MealModel("Smoothie", 7));
        meals.add(new MealModel("Vegetable Noodle Soup", 15));
        meals.add(new MealModel("PB&J Sammy", 5));

        // Setup adapter for list view
        MealsAdapter adapter = new MealsAdapter(this, R.layout.meal_row, (List)meals);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}
