package com.android.sravnilka.ui.listeners;

import android.content.Context;
import android.widget.Toast;

import com.android.sravnilka.R;
import com.nhaarman.listviewanimations.ArrayAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.OnItemMovedListener;

/**
 * Created by Dzmitry_Balash on 11/12/2014.
 */
public class DragDropItemMovedListener implements OnItemMovedListener {
    private final ArrayAdapter<String> mAdapter;
    private Toast mToast;
    private final Context vContext;
    public DragDropItemMovedListener(final ArrayAdapter<String> adapter, Context context) {
        mAdapter = adapter;
        vContext = context;
    }
    @Override
    public void onItemMoved(final int originalPosition, final int newPosition) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(vContext, vContext.getString(R.string.moved, mAdapter.getItem(newPosition), newPosition), Toast.LENGTH_SHORT);
        mToast.show();
    }
}
