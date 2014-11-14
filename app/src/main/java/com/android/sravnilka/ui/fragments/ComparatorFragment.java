package com.android.sravnilka.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.sravnilka.R;
import com.android.sravnilka.dao.Camparator;
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
    private ArrayList<Camparator> mNames;

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
        mNames = new ArrayList<Camparator>();
        ArrayList<ComparatorItem> checks = new ArrayList<ComparatorItem>();
        checks.add(new ComparatorItem("Abube 1", false));
        checks.add(new ComparatorItem("Abube 2",false));
        checks.add(new ComparatorItem("Abube 3",false));
        checks.add(new ComparatorItem("Abube 4",false));
        ArrayList<ComparatorItem> checks1 = new ArrayList<ComparatorItem>();
        checks1.add(new ComparatorItem("Abube 1", false));
        checks1.add(new ComparatorItem("Abube 2",false));
        checks1.add(new ComparatorItem("Abube 3",false));
        checks1.add(new ComparatorItem("Abube 4",false));
        ArrayList<ComparatorItem> checks2 = new ArrayList<ComparatorItem>();
        checks2.add(new ComparatorItem("Abube 1", false));
        checks2.add(new ComparatorItem("Abube 2",false));
        checks2.add(new ComparatorItem("Abube 3",false));
        checks2.add(new ComparatorItem("Abube 4",false));
        ArrayList<ComparatorItem> checks3 = new ArrayList<ComparatorItem>();
        checks3.add(new ComparatorItem("Abube 1", false));
        checks3.add(new ComparatorItem("Abube 2",false));
        checks3.add(new ComparatorItem("Abube 3",false));
        checks3.add(new ComparatorItem("Abube 4",false));
        ArrayList<ComparatorItem> checks4 = new ArrayList<ComparatorItem>();
        checks4.add(new ComparatorItem("Abube 1", false));
        checks4.add(new ComparatorItem("Abube 2",false));
        checks4.add(new ComparatorItem("Abube 3",false));
        checks4.add(new ComparatorItem("Abube 4",false));
        ArrayList<ComparatorItem> checks5 = new ArrayList<ComparatorItem>();
        checks5.add(new ComparatorItem("Abube 1", false));
        checks5.add(new ComparatorItem("Abube 2",false));
        checks5.add(new ComparatorItem("Abube 3",false));
        checks5.add(new ComparatorItem("Abube 4",false));
        mNames.add(new Camparator("One",checks));
        mNames.add(new Camparator("Two", checks1));
        mNames.add(new Camparator("Three", checks2));
        mNames.add(new Camparator("Four", checks3));
        mNames.add(new Camparator("Five", checks4));
        mNames.add(new Camparator("Six", checks5));
    }
}
