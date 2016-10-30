package com.hoomanlogic.mealhub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MealListFragment extends Fragment {
    static MealListFragment newInstance() {
        MealListFragment fragment = new MealListFragment();
        return fragment;
    }

    MealsAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setup adapter for list view
        mAdapter = new MealsAdapter(getActivity(), R.layout.meal_row, DataAccess.getMeals());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_list, container, false);
        ListView listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(mAdapter);
        return view;
    }
}
