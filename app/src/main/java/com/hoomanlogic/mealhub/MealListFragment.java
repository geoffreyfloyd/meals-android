package com.hoomanlogic.mealhub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MealListFragment extends Fragment {
    MealsAdapter mAdapter;

    public static MealListFragment newInstance() {
        MealListFragment fragment = new MealListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_meal_list, container, false);
        // Populate listview from firebase db
        DatabaseReference _Db = FirebaseDatabase.getInstance().getReference();
        _Db.child("data").child("meals").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get list of meals
                List<MealModel> mealList = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    mealList.add(data.getValue(MealModel.class));
                }

                // Create adapter and assign to list view
                mAdapter = new MealsAdapter(getActivity(), R.layout.meal_row, mealList);
                ListView listView = (ListView)view.findViewById(R.id.listView);
                listView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }
}
