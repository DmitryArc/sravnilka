package com.android.sravnilka.ui.fragments;

import android.animation.Animator;
import android.animation.AnimatorInflater;
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
import com.android.sravnilka.ui.adapter.ResultsAdapter;
import com.android.sravnilka.utils.FragmentUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by dka on 12.11.2014.
 */
public class ResultsFragment extends Fragment implements View.OnClickListener{
    private final static String TAG = ResultsFragment.class.getSimpleName();
    private final static String RES = TAG + ".res";
    private ListView mListView;
    private ResultsAdapter mAdapter;
    private Button mNextButton;
    private Map<String, Integer> mResults;

    public static ResultsFragment newInstance(Map<String, Integer> results){
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putSerializable(RES, (Serializable) results);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            mResults = (Map<String, Integer>)args.getSerializable(RES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fr_results, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView) view.findViewById(R.id.lv_results);
        mAdapter = new ResultsAdapter(getActivity(), mResults);
        FrameLayout footerLayout = (FrameLayout) getActivity().getLayoutInflater().inflate(R.layout.v_btn_next,null);
        mNextButton = (Button) footerLayout.findViewById(R.id.btn_next);
        mNextButton.setText(R.string.reload);
        mListView.addFooterView(footerLayout);
        mNextButton.setOnClickListener(this);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            getActivity().getActionBar().setTitle(getResources().getString(R.string.title_f_result));
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
            ((IFlowController)getActivity()).onReload();
        }
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        if (FragmentUtils.sDisableFragmentAnimations) {
            return AnimatorInflater.loadAnimator(getActivity(), R.animator.slide_out_to_left);
        }
        return super.onCreateAnimator(transit, enter, nextAnim);
    }

}
