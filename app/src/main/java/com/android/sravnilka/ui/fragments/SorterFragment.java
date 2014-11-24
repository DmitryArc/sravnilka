package com.android.sravnilka.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.android.sravnilka.IFlowController;
import com.android.sravnilka.R;
import com.android.sravnilka.dnd.DynamicListView;
import com.android.sravnilka.ui.adapter.DragDropAdapter;
import com.android.sravnilka.ui.listeners.DragDropItemMovedListener;
import com.nhaarman.listviewanimations.ArrayAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.TouchViewDraggableManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by dka on 12.11.2014.
 */
public class SorterFragment extends Fragment implements View.OnClickListener{

    private final static String SORTER = "sorter";

    private com.nhaarman.listviewanimations.itemmanipulation.DynamicListView mListView;
    private ArrayAdapter<String> mAdapter;
    private Button mNextButton;
    private Set<String> mData;
    private ArrayList<String> mSortetList;

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
        initList();
        mListView = (DynamicListView) view.findViewById(R.id.dynamiclistview);
        mAdapter = new DragDropAdapter(getActivity(), mSortetList);
        mListView.enableDragAndDrop();
        mListView.setDraggableManager(new TouchViewDraggableManager(R.id.draganddrop_touchview));
        mListView.setOnItemMovedListener(new DragDropItemMovedListener(mAdapter, getActivity()));
        FrameLayout footerLayout = (FrameLayout) getActivity().getLayoutInflater().inflate(R.layout.v_btn_next,null);
        mNextButton = (Button) footerLayout.findViewById(R.id.btn_next);
        mNextButton.setText(R.string.result);
        mListView.addFooterView(footerLayout);
        mNextButton.setOnClickListener(this);
        mListView.setAdapter(mAdapter);
    }

    private void initList(){
        if(mSortetList == null) {
            mSortetList = new ArrayList<String>();
            for (String item : mData) {
                mSortetList.add(item);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            getActivity().getActionBar().setTitle(getResources().getString(R.string.title_f_sort));
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
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
        Map<String, Integer> data = new LinkedHashMap<String, Integer>();
        mSortetList.clear();
        for(int i = 0; i < mAdapter.getCount(); i++){
            data.put(mAdapter.getItem(i), mAdapter.getCount() - i);
            mSortetList.add(mAdapter.getItem(i));
        }
        return data;
    }
}
