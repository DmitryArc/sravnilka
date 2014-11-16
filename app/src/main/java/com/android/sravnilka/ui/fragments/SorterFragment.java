package com.android.sravnilka.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.sravnilka.R;
import com.android.sravnilka.ui.adapter.DragDropAdapter;
import com.android.sravnilka.ui.listeners.DragDropItemMovedListener;
import com.android.sravnilka.ui.listeners.DragDropLongClickListener;
import com.nhaarman.listviewanimations.ArrayAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.TouchViewDraggableManager;

/**
 * Created by dka on 12.11.2014.
 */
public class SorterFragment extends Fragment implements View.OnClickListener{

    private DynamicListView mListView;
    private ArrayAdapter<String> mAdapter;
    private Button mNextButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fr_sorter, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getActivity(), R.string.drag_drop, Toast.LENGTH_LONG).show();
        mListView = (DynamicListView) view.findViewById(R.id.dynamiclistview);
        mAdapter = new DragDropAdapter(getActivity());
        mListView.enableDragAndDrop();
        mListView.setDraggableManager(new TouchViewDraggableManager(R.id.draganddrop_touchview));
        mListView.setOnItemMovedListener(new DragDropItemMovedListener(mAdapter, getActivity()));
//        mListView.setOnItemLongClickListener(new DragDropLongClickListener(mListView));
        mListView.setAdapter(mAdapter);
        FrameLayout footerLayout = (FrameLayout) getActivity().getLayoutInflater().inflate(R.layout.v_btn_next,null);
        mNextButton = (Button) footerLayout.findViewById(R.id.btn_next);
        mNextButton.setText("Result");
        mListView.addFooterView(footerLayout);
        mNextButton.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            getActivity().getActionBar().setTitle(getResources().getString(R.string.title_f_sort));
        }
    }

    @Override
    public void onClick(View v) {

    }
}
