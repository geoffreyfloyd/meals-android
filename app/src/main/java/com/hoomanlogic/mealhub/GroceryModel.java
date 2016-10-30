package com.hoomanlogic.mealhub;

import com.hoomanlogic.util.Format;

public class GroceryModel {
    public String Id;
    public String Name;
    public int DaysToPerish;
    public int Need;
    public String NeedUnit;
    public String DefaultUnit;

    public GroceryModel() {

    }

    public GroceryModel(String name) {
        this.setName(name);
    }

    public GroceryModel(String name, int daysToPerish) {
        this(name);
        this.DaysToPerish = daysToPerish;
    }

    public void setName(String name) {
        this.Id = Format.toSlug(name);
        this.Name = name;
    }
}
