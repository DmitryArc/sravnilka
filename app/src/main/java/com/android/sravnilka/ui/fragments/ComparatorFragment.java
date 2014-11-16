package com.android.sravnilka.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.android.sravnilka.IFlowController;
import com.android.sravnilka.R;
import com.android.sravnilka.dao.CheckItem;
import com.android.sravnilka.dao.ComparatorItem;
import com.android.sravnilka.ui.adapter.ComparatorAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by dka on 12.11.2014.
 */
public class ComparatorFragment extends Fragment implements View.OnClickListener{

    private final static String DATA = "data";
    private final static String CHECKS = "checks";
    private ListView mListComparatorView;
    private ComparatorAdapter mAdapter;
    private Button mNextButton;
    private Set<String> mItemsSet;
    private Set<String> mParamsSet;
    private ArrayList<ComparatorItem> mDataFinish;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            mItemsSet = (Set<String>)args.getSerializable(CHECKS);
            mParamsSet = (Set<String>) args.getSerializable(DATA);
        }
    }

    public static ComparatorFragment newInstance(Set<String> data, Set<String> checks){
        ComparatorFragment fragment = new ComparatorFragment();
        Bundle args = new Bundle();
        args.putSerializable(DATA, (Serializable) data);
        args.putSerializable(CHECKS, (Serializable) checks);
        fragment.setArguments(args);
        return fragment;
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
        mAdapter = new ComparatorAdapter(getActivity(), mDataFinish);
        mListComparatorView.setAdapter(mAdapter);
        FrameLayout footerLayout = (FrameLayout) getActivity().getLayoutInflater().inflate(R.layout.v_btn_next,null);
        mNextButton = (Button) footerLayout.findViewById(R.id.btn_next);
        mNextButton.setText(R.string.next);
        mListComparatorView.addFooterView(footerLayout);
        mNextButton.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            getActivity().getActionBar().setTitle(getResources().getString(R.string.title_f_compare));
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initList(){
        if(mDataFinish == null) {
            mDataFinish = new ArrayList<ComparatorItem>();
            for (String item : mItemsSet) {
                ArrayList<CheckItem> checks = new ArrayList<CheckItem>();
                for (String check : mParamsSet) {
                    checks.add(new CheckItem(check, false));
                }
                mDataFinish.add(new ComparatorItem(item, checks));
            }
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
            ((IFlowController)getActivity()).onCompareActionDone(getData());
        }
    }

    private Map<String, Set<String>> getData(){
        Map<String, Set<String>> data = new HashMap<String, Set<String>>();
        for(int i = 0; i < mDataFinish.size(); i++){
            Set<String> check = new TreeSet<String>();
            for(int j = 0; j < mDataFinish.get(i).getChecks().size(); j++){
                if(mDataFinish.get(i).getChecks().get(j).isChecked()){
                    check.add(mDataFinish.get(i).getChecks().get(j).getName());
                }
            }
            data.put(mDataFinish.get(i).getName(), check);
        }
        return data;
    }
}
