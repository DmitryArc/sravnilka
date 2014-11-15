package com.android.sravnilka.dao;

/**
 * Created by Dzmitry_Balash on 11/14/2014.
 */
public class CheckItem {
    private String name = "" ;
    private boolean checked = false ;
    public CheckItem() {}
    public CheckItem(String name) {
        this.name = name ;
    }
    public CheckItem(String name, boolean checked) {
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
