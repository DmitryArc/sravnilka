package com.android.sravnilka.dao;

import java.util.ArrayList;

/**
 * Created by Dzmitry_Balash on 11/14/2014.
 */
public class Camparator{
    private String name = "" ;
    private ArrayList<ComparatorItem> mChecks;

    public Camparator(String name, ArrayList<ComparatorItem> mChecks) {
        this.name = name;
        this.mChecks = mChecks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ComparatorItem> getChecks() {
        return mChecks;
    }

    public void setChecks(ArrayList<ComparatorItem> mChecks) {
        this.mChecks = mChecks;
    }
}
