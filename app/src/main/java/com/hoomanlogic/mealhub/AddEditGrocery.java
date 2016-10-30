package com.hoomanlogic.mealhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class AddEditGrocery extends AppCompatActivity {
    GroceryModel _Model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_grocery);

        // Get intent args
        Intent intent = getIntent();
        String groceryId = intent.getStringExtra("id");
        String groceryName = intent.getStringExtra("name");

        // Decide how to proceed based on intent
        if (groceryId != null && !groceryId.isEmpty()) {
            try {
                _Model = DataAccess.getGrocery(groceryId);
            }
            catch (Exception ex) {
                // Grocery didn't exist, start a new one, generating named based on the id
                if (groceryName == null || groceryName.isEmpty()) {
                    String[] words = groceryId.replace('_', ' ').split(" ");
                    groceryName = "";
                    for (int i = 0; i < words.length; i++) {
                        groceryName += words[i].substring(0, 1).toUpperCase() + words[i].substring(1, words[i].length());
                    }
                }
                _Model = new GroceryModel(groceryName);
            }
        }
        else if (groceryName != null && !groceryName.isEmpty()) {
            // Create a grocery with passed name, save, and exit activity
            _Model = new GroceryModel(groceryName);
        }
        else {
            // Start a new grocery from scratch
            _Model = new GroceryModel();
        }

        // Load form from model
        Load();
    }

    private void Load() {
        EditText editName = (EditText)findViewById(R.id.editName);
        EditText editPerishes = (EditText)findViewById(R.id.editPerishes);
        editName.setText(_Model.Name);
        editPerishes.setText(String.valueOf(_Model.DaysToPerish));
    }

    private void Save() {
        // Persist to internal database

    }

}
