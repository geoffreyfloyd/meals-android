package com.hoomanlogic.mealhub;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by owner on 10/30/16.
 */

public class GroceryGroupListAdapter extends BaseExpandableListAdapter {
    public static final int CATEGORY_PRODUCE_BAKERY = 0;
    public static final int CATEGORY_FROZEN = 1;
    public static final int CATEGORY_MEAT_DAIRY = 2;
    public static final int CATEGORY_SHELF_MISC = 3;

    private Context _Context;
    private List<String> _Groups;
    private HashMap<String, List<GroceryModel>> _GroupLists;

    public GroceryGroupListAdapter(Context context, int resource, List<GroceryModel> items) {
        this._Context = context;
        this._Groups = new ArrayList<>();
        this._GroupLists = new HashMap<>();
        Resources res = context.getResources();
        this._Groups.add(res.getString(R.string.produce_bakery));
        this._Groups.add(res.getString(R.string.frozen));
        this._Groups.add(res.getString(R.string.meat_dairy));
        this._Groups.add(res.getString(R.string.shelf_misc));
        this._GroupLists.put(res.getString(R.string.produce_bakery), getGroupChildren(items, CATEGORY_PRODUCE_BAKERY));
        this._GroupLists.put(res.getString(R.string.frozen), getGroupChildren(items, CATEGORY_FROZEN));
        this._GroupLists.put(res.getString(R.string.meat_dairy), getGroupChildren(items, CATEGORY_MEAT_DAIRY));
        this._GroupLists.put(res.getString(R.string.shelf_misc), getGroupChildren(items, CATEGORY_SHELF_MISC));
    }

    public static List<GroceryModel> getGroupChildren(List<GroceryModel> items, int groupId) {
        List<GroceryModel> groupChildren = new ArrayList<>();
        for (GroceryModel model : items) {
            if (toCategoryGroupOrder(model.Category) == groupId) {
                groupChildren.add(model);
            }
        }
        return sortList(groupChildren);
    }

    public static final Comparator<GroceryModel> NAME_ORDER = new Comparator<GroceryModel>() {
        @Override
        public int compare(GroceryModel lhs, GroceryModel rhs) {
            return lhs.getName().compareTo(rhs.getName());
        }
    };

    static List<GroceryModel> sortList(List<GroceryModel> items) {
        Collections.sort(items, NAME_ORDER);
        return items;
    }

    public static final int toCategoryGroupOrder(String category) {

        switch (category) {
            case "Bakery":
            case "Produce":
                return CATEGORY_PRODUCE_BAKERY;
            case "Frozen":
                return CATEGORY_FROZEN;
            case "Meat":
            case "Dairy":
                return CATEGORY_MEAT_DAIRY;
            default:
                return CATEGORY_SHELF_MISC;
        }
    }

    public static final int toCategoryColor(Resources res, int groupId) {
        switch (groupId) {
            case CATEGORY_PRODUCE_BAKERY:
                return res.getColor(R.color.color_produce_bakery);
            case CATEGORY_FROZEN:
                return res.getColor(R.color.color_frozen);
            case CATEGORY_MEAT_DAIRY:
                return res.getColor(R.color.color_meat_dairy);
            case CATEGORY_SHELF_MISC:
                return res.getColor(R.color.color_shelf_misc);
            default:
                return res.getColor(R.color.color_shelf_misc);
        }
    }

    @Override
    public int getGroupCount() {
        return _Groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return _GroupLists.get(_Groups.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return _Groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return _GroupLists.get(_Groups.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        // Build view if it doesn't yet exist
        if (view == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(_Context);
            view = vi.inflate(R.layout.list_group, null);
        }
        // Get item from list
        String item = (String) this.getGroup(groupPosition);
        if (item != null) {
            TextView nameText = (TextView)view.findViewById(R.id.labelHeader);
            nameText.setTypeface(null, Typeface.BOLD);
            nameText.setText(item);
            Resources res = _Context.getResources();
            view.setBackgroundColor(toCategoryColor(res, groupPosition));
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        // Build view if it doesn't yet exist
        if (view == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(_Context);
            view = vi.inflate(R.layout.grocery_row, null);
        }
        // Get item from list
        GroceryModel item = (GroceryModel) this.getChild(groupPosition, childPosition);
        if (item != null) {
            TextView nameText = (TextView)view.findViewById(R.id.name);
            nameText.setText(item.getName());
            TextView daysToPerishText = (TextView)view.findViewById(R.id.daysToPerish);
            daysToPerishText.setText(String.valueOf(item.DaysToPerish));
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
