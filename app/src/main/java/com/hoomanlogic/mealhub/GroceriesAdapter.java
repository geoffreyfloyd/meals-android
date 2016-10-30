package com.hoomanlogic.mealhub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class GroceriesAdapter extends ArrayAdapter<GroceryModel> {
    public GroceriesAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public GroceriesAdapter(Context context, int resource, List<GroceryModel> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        // Build view if it doesn't yet exist
        if (view == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.grocery_row, null);
        }
        // Get item from list
        GroceryModel item = this.getItem(position);
        if (item != null) {
            TextView nameText = (TextView)view.findViewById(R.id.name);
            nameText.setText(item.Name);
            TextView daysToPerishText = (TextView)view.findViewById(R.id.daysToPerish);
            daysToPerishText.setText(String.valueOf(item.DaysToPerish));
        }
        return view;
    }
}
