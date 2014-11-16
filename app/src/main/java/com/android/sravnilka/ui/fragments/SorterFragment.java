package com.android.sravnilka.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.sravnilka.IFlowController;
import com.android.sravnilka.R;
import com.android.sravnilka.ui.adapter.DragDropAdapter;
import com.android.sravnilka.ui.listeners.DragDropItemMovedListener;
import com.android.sravnilka.ui.listeners.DragDropLongClickListener;
import com.nhaarman.listviewanimations.ArrayAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.TouchViewDraggableManager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by dka on 12.11.2014.
 */
public class SorterFragment extends Fragment implements View.OnClickListener{

    private final static String SORTER = "sorter";

    private DynamicListView mListView;
    private ArrayAdapter<String> mAdapter;
    private Button mNextButton;
    private Set<String> mData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            mData = (Set<String>)args.getSerializable(SORTER);
        }
    }

    public static SorterFragment newInstance(Set<String> data){
        SorterFragment fragment = new SorterFragment();
        Bundle args = new Bundle();
        args.putSerializable(SORTER, (Serializable) data);
        fragment.setArguments(args);
        return fragment;
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
        mAdapter = new DragDropAdapter(getActivity(), mData);
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
        switch (v.getId()){
            case R.id.btn_next:
                if(validateData()){
                    next();
                }
                break;
            default:
                break;
        }
    }

    private boolean validateData() {
        return true;
    }

    private void next() {
        if(getActivity() instanceof IFlowController){
            ((IFlowController)getActivity()).onImportanceScaleReady(getData());
        }
    }
    private Map<String, Integer> getData(){
        Map<String, Integer> data = new HashMap<String, Integer>();
        for(int i = 0; i < mAdapter.getCount(); i++){
            data.put(mAdapter.getItem(i), i);
        }
        return data;
    }
}
