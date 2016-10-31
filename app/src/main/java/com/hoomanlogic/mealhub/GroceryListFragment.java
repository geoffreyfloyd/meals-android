package com.hoomanlogic.mealhub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GroceryListFragment extends Fragment {
    // CONSTANTS
    final int ADD_GROCERY_REQUEST = 1;
    // PRIVATE
    private GroceriesAdapter _Adapter;
    private View _View;

    // CONSTRUCTOR
    public static GroceryListFragment newInstance() {
        GroceryListFragment fragment = new GroceryListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _View = inflater.inflate(R.layout.fragment_grocery_list, container, false);

        // Populate listview from firebase db
        updateList();

        // Set up add new action
        FloatingActionButton floatingActionButton = (FloatingActionButton)_View.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addGroceryIntent = new Intent(getActivity(), AddEditGrocery.class);
                startActivityForResult(addGroceryIntent, ADD_GROCERY_REQUEST);
            }
        });

        // Set up list item click
        ListView listView = (ListView)_View.findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GroceryModel model = _Adapter.getItem(position);
                Intent editGroceryIntent = new Intent(getActivity(), AddEditGrocery.class);
                editGroceryIntent.putExtra("id", model.Id);
                startActivity(editGroceryIntent);
            }
        });

        // Return inflated view
        return _View;
    }

    private void updateList () {
        // Populate listview from firebase db
        DatabaseReference _Db = FirebaseDatabase.getInstance().getReference();
        _Db.child("data").child("groceries").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get list of groceries
                List<GroceryModel> groceryList = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    groceryList.add(data.getValue(GroceryModel.class));
                }

                // Keep list around to be accessed by edit view
                DataAccess.setGroceries(groceryList);

                // Create adapter and assign to list view
                _Adapter = new GroceriesAdapter(getActivity(), R.layout.grocery_row, groceryList);
                ListView listView = (ListView)_View.findViewById(R.id.listView);
                listView.setAdapter(_Adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != ADD_GROCERY_REQUEST || resultCode != Activity.RESULT_OK) {
            return;
        }

        updateList();
    }
}
