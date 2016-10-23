package com.hoomanlogic.meals;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MealListFragment extends Fragment {
    static MealListFragment newInstance() {
        MealListFragment fragment = new MealListFragment();
        return fragment;
    }

    MealsAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Build Meals List
        ArrayList<MealModel> meals = new ArrayList<>();
        meals.add(new MealModel("Smoothie", 7));
        meals.add(new MealModel("Vegetable Noodle Soup", 15));
        meals.add(new MealModel("PB&J Sammy", 5));
        // Setup adapter for list view
        mAdapter = new MealsAdapter(getActivity(), R.layout.meal_row, (List)meals);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_list, container, false);
        ListView listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(mAdapter);
        return view;
    }
}
