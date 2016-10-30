package com.hoomanlogic.mealhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class GroceryListFragment extends Fragment {
    static GroceryListFragment newInstance() {
        GroceryListFragment fragment = new GroceryListFragment();
        return fragment;
    }

    GroceriesAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setup adapter for list view
        mAdapter = new GroceriesAdapter(getActivity(), R.layout.grocery_row, DataAccess.getGroceries());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grocery_list, container, false);
        ListView listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(mAdapter);
        FloatingActionButton floatingActionButton = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddEditGrocery.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
