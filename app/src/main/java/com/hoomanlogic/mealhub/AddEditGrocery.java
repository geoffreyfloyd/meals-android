package com.hoomanlogic.mealhub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

public class AddEditGrocery extends AppCompatActivity {
    // CONSTANTS
    final int ADD_GROCERY_REQUEST = 1;
    // PRIVATE
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

        Button buttonSave = (Button)findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });
    }

    private void Load() {
        // Get refs to edit fields
        EditText editName = (EditText)findViewById(R.id.editName);
        EditText editCategory = (EditText)findViewById(R.id.editCategory);
        EditText editPerishes = (EditText)findViewById(R.id.editPerishes);
        // Set edit fields with values from model
        editName.setText(_Model.getName());
        editCategory.setText(_Model.Category);
        if (_Model.DaysToPerish > 0) {
            editPerishes.setText(String.valueOf(_Model.DaysToPerish));
        }
    }

    private void Save() {
        // Get refs to edit fields
        EditText editName = (EditText)findViewById(R.id.editName);
        EditText editCategory = (EditText)findViewById(R.id.editCategory);
        EditText editPerishes = (EditText)findViewById(R.id.editPerishes);
        // Set model with values from edit fields
        _Model.setName(editName.getText().toString());
        _Model.Category = editCategory.getText().toString();
        String daysToPerish = editPerishes.getText().toString();
        if (daysToPerish.isEmpty()) {
            daysToPerish = "0";
        }
        _Model.DaysToPerish = Integer.parseInt(daysToPerish);
        // Persist model to db
        DataAccess.saveGrocery(_Model);
        // Update Activity Result and Finish
        setResult(RESULT_OK);
        finishActivity(ADD_GROCERY_REQUEST);
        finish();
    }

}
