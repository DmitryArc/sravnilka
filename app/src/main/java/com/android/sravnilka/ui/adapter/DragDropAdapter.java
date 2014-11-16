package com.android.sravnilka.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.sravnilka.R;
import com.nhaarman.listviewanimations.ArrayAdapter;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Dzmitry_Balash on 11/12/2014.
 */
public class DragDropAdapter extends ArrayAdapter<String> {
    private final Context mContext;
    public DragDropAdapter(final Context context, ArrayList<String> data) {
        mContext = context;
        for (String item : data) {
            add(item);
        }
    }
    @Override
    public long getItemId(final int position) {
        return getItem(position).hashCode();
    }
    @Override
    public boolean hasStableIds() {
        return true;
    }
    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.v_drag_drop_item, parent, false);
        }
        ((TextView) view.findViewById(R.id.draganddrop_textview)).setText(getItem(position));
        return view;
    }

}
