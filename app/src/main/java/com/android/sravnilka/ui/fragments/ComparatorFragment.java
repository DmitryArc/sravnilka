package com.android.sravnilka.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.sravnilka.R;
import com.android.sravnilka.dao.CheckItem;
import com.android.sravnilka.dao.ComparatorItem;
import com.android.sravnilka.ui.adapter.ComparatorAdapter;

import java.util.ArrayList;

/**
 * Created by dka on 12.11.2014.
 */
public class ComparatorFragment extends Fragment {

    private ListView mListComparatorView;
    private ComparatorAdapter mAdapter;

    //Test Data
    private ArrayList<ComparatorItem> mNames;

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
        mAdapter = new ComparatorAdapter(getActivity(), mNames);
        mListComparatorView.setAdapter(mAdapter);
    }

    private void initList(){
        mNames = new ArrayList<ComparatorItem>();
        ArrayList<CheckItem> checks = new ArrayList<CheckItem>();
        checks.add(new CheckItem("Abube 1", false));
        checks.add(new CheckItem("Abube 2",false));
        checks.add(new CheckItem("Abube 3",false));
        checks.add(new CheckItem("Abube 4",false));
        ArrayList<CheckItem> checks1 = new ArrayList<CheckItem>();
        checks1.add(new CheckItem("Abube 1", false));
        checks1.add(new CheckItem("Abube 2",false));
        checks1.add(new CheckItem("Abube 3",false));
        checks1.add(new CheckItem("Abube 4",false));
        ArrayList<CheckItem> checks2 = new ArrayList<CheckItem>();
        checks2.add(new CheckItem("Abube 1", false));
        checks2.add(new CheckItem("Abube 2",false));
        checks2.add(new CheckItem("Abube 3",false));
        checks2.add(new CheckItem("Abube 4",false));
        ArrayList<CheckItem> checks3 = new ArrayList<CheckItem>();
        checks3.add(new CheckItem("Abube 1", false));
        checks3.add(new CheckItem("Abube 2",false));
        checks3.add(new CheckItem("Abube 3",false));
        checks3.add(new CheckItem("Abube 4",false));
        ArrayList<CheckItem> checks4 = new ArrayList<CheckItem>();
        checks4.add(new CheckItem("Abube 1", false));
        checks4.add(new CheckItem("Abube 2",false));
        checks4.add(new CheckItem("Abube 3",false));
        checks4.add(new CheckItem("Abube 4",false));
        ArrayList<CheckItem> checks5 = new ArrayList<CheckItem>();
        checks5.add(new CheckItem("Abube 1", false));
        checks5.add(new CheckItem("Abube 2",false));
        checks5.add(new CheckItem("Abube 3",false));
        checks5.add(new CheckItem("Abube 4",false));
        mNames.add(new ComparatorItem("One",checks));
        mNames.add(new ComparatorItem("Two", checks1));
        mNames.add(new ComparatorItem("Three", checks2));
        mNames.add(new ComparatorItem("Four", checks3));
        mNames.add(new ComparatorItem("Five", checks4));
        mNames.add(new ComparatorItem("Six", checks5));
    }
}
