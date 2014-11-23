package com.android.sravnilka.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.sravnilka.R;

/**
 * Created by dka on 23.11.2014.
 */
public class DndView extends LinearLayout {

    public DndView(Context context, ViewGroup parent){
        super(context);
        initw(context, parent);
    }

    public DndView(Context context) {
        super(context);
        initw(context, null);
    }

    public DndView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initw(context, null);
    }

    public DndView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initw(context, null);
    }

    public DndView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initw(context, null);
    }

    private void initw(Context context, ViewGroup parent){
        View view = LayoutInflater.from(context).inflate(R.layout.v_drag_drop_item, parent, false);
        this.addView(view);
    }

    @Override
    public void setVisibility(int visibility) {
        if(visibility != View.INVISIBLE) {
            super.setVisibility(visibility);
        } else {
            findViewById(R.id.draganddrop_textview).setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }
}
