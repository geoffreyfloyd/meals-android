package com.hoomanlogic.mealhub;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MealsAdapter extends ArrayAdapter<MealModel> {
    public MealsAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public MealsAdapter(Context context, int resource, List<MealModel> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        // Build view if it doesn't yet exist
        if (view == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.meal_row, null);
        }
        // Get item from list
        MealModel item = this.getItem(position);
        if (item != null) {
            TextView nameText = (TextView)view.findViewById(R.id.name);
            nameText.setText(item.Name);
            Pie pie = (Pie)view.findViewById(R.id.minutes);
            pie.setData(new float[] { item.MinutesToPrepare });
        }
        return view;
    }
}
