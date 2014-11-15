package com.android.sravnilka.dao;

import java.util.ArrayList;

/**
 * Created by Dzmitry_Balash on 11/14/2014.
 */
public class ComparatorItem {
    private String name = "" ;
    private ArrayList<CheckItem> mChecks;

    public ComparatorItem(String name, ArrayList<CheckItem> mChecks) {
        this.name = name;
        this.mChecks = mChecks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<CheckItem> getChecks() {
        return mChecks;
    }

    public void setChecks(ArrayList<CheckItem> mChecks) {
        this.mChecks = mChecks;
    }
}
