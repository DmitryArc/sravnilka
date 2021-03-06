package com.android.sravnilka.ui.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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
    private ComparatorAdapter mAdapter;
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_comparator, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listComparatorView = (ListView) view.findViewById(R.id.comparator_list);
        initList();
        mAdapter = new ComparatorAdapter(getActivity(), mDataFinish);
        FrameLayout footerLayout = (FrameLayout) getActivity().getLayoutInflater().inflate(R.layout.v_btn_next,null);
        Button nextButton = (Button) footerLayout.findViewById(R.id.btn_next);
        nextButton.setText(R.string.next);
        listComparatorView.addFooterView(footerLayout);
        nextButton.setOnClickListener(this);
        listComparatorView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            getActivity().getActionBar().setTitle(getResources().getString(R.string.title_f_compare));
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

            getActivity().getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
