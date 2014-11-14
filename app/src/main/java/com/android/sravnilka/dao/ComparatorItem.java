package com.android.sravnilka.dao;

/**
 * Created by Dzmitry_Balash on 11/14/2014.
 */
public class ComparatorItem {
    private String name = "" ;
    private boolean checked = false ;
    public ComparatorItem() {}
    public ComparatorItem(String name) {
        this.name = name ;
    }
    public ComparatorItem(String name, boolean checked) {
        this.name = name ;
        this.checked = checked ;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public String toString() {
        return name ;
    }
    public void toggleChecked() {
        checked = !checked ;
    }
}
