package com.hoomanlogic.mealhub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GroceriesAdapter extends ArrayAdapter<GroceryModel> {
//    public static final int CATEGORY_PRODUCE_BAKERY = 0;
//    public static final int CATEGORY_FROZEN = 1;
//    public static final int CATEGORY_MEAT_DAIRY = 2;
//    public static final int CATEGORY_SHELF_MISC = 3;

    public static final Comparator<GroceryModel> CATEGORY_ORDER = new Comparator<GroceryModel>() {
        @Override
        public int compare(GroceryModel lhs, GroceryModel rhs) {
            String lhsCategory = lhs.Category == null ? "" : lhs.Category;
            String rhsCategory = rhs.Category == null ? "" : rhs.Category;
            return Integer.compare(toCategoryGroupOrder(lhsCategory), toCategoryGroupOrder(rhsCategory));
        }
    };

    public static final Comparator<GroceryModel> NAME_ORDER = new Comparator<GroceryModel>() {
        @Override
        public int compare(GroceryModel lhs, GroceryModel rhs) {
            return lhs.getName().compareTo(rhs.getName());
        }
    };

    public static final int toCategoryGroupOrder(String category) {
        switch (category) {
            case "Bakery":
            case "Produce":
                return 0;
            case "Frozen":
                return 1;
            case "Meat":
            case "Dairy":
                return 2;
            default:
                return 3;
        }
    }

    static List<GroceryModel> SortList(List<GroceryModel> items) {
        Collections.sort(items, NAME_ORDER);
        Collections.sort(items, CATEGORY_ORDER);
        return items;
    }

    public GroceriesAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public GroceriesAdapter(Context context, int resource, List<GroceryModel> items) {
        super(context, resource, SortList(items));
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
            nameText.setText(item.getName());
            TextView daysToPerishText = (TextView)view.findViewById(R.id.daysToPerish);
            daysToPerishText.setText(String.valueOf(item.DaysToPerish));
        }
        return view;
    }
}
