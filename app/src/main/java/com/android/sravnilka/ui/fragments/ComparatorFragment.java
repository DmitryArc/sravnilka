package com.android.sravnilka.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.sravnilka.R;
import com.android.sravnilka.ui.adapter.ComparatorAdapter;

import java.util.ArrayList;

/**
 * Created by dka on 12.11.2014.
 */
public class ComparatorFragment extends Fragment {

    private ListView mListComparatorView;
    private ComparatorAdapter mAdapter;

    //Test Data
    private ArrayList<String> mNames;
    private ArrayList<String> mChecks;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fr_comparator, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListComparatorView = (ListView) view.findViewById(R.id.comparator_list);
        initList();
        mAdapter = new ComparatorAdapter(getActivity(), mNames, mChecks);
        mListComparatorView.setAdapter(mAdapter);
    }

    private void initList(){
        mNames = new ArrayList<String>();
        mNames.add("One");
        mNames.add("Two");
        mNames.add("Three");
        mNames.add("Four");
        mNames.add("Five");
        mNames.add("Six");
        mNames.add("Seven");
        mChecks = new ArrayList<String>();
        mChecks.add("Abube 1");
        mChecks.add("Abube 2");
        mChecks.add("Abube 3");
        mChecks.add("Abube 4");
    }
}
