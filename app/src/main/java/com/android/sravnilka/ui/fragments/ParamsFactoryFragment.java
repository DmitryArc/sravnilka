package com.android.sravnilka.ui.fragments;

import android.app.Activity;

import com.android.sravnilka.IFlowController;

import java.util.Set;

/**
 * Created by dka on 12.11.2014.
 */
public class ParamsFactoryFragment extends SourceFactoryFragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        getActivity().getActionBar().setTitle("Params");
    }

    @Override
    protected void next(Set<String> items) {
        if(getActivity() instanceof IFlowController){
            ((IFlowController)getActivity()).onParamSetReady(mData);
        }
    }
}