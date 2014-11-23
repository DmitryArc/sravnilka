package com.android.sravnilka.utils;

import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.DragAndDropListViewWrapper;

/**
 * Created by dka on 23.11.2014.
 */
public class DragAndDropHandler extends com.nhaarman.listviewanimations.itemmanipulation.dragdrop.DragAndDropHandler {
    public DragAndDropHandler(DynamicListView dynamicListView) {
        super(dynamicListView);
    }

    public DragAndDropHandler(DragAndDropListViewWrapper dragAndDropListViewWrapper) {
        super(dragAndDropListViewWrapper);
    }
}
