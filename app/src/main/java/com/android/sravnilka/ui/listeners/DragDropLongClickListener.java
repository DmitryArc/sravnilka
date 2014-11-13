package com.android.sravnilka.ui.listeners;

import android.view.View;
import android.widget.AdapterView;

import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;

/**
 * Created by Dzmitry_Balash on 11/12/2014.
 */
public class DragDropLongClickListener implements AdapterView.OnItemLongClickListener {
    private final DynamicListView mListView;
    public DragDropLongClickListener(final DynamicListView listView) {
        mListView = listView;
    }
    @Override
    public boolean onItemLongClick(final AdapterView<?> parent, final View view, final int position, final long id) {
        if (mListView != null) {
            mListView.startDragging(position - mListView.getHeaderViewsCount());
        }
        return true;
    }
}
