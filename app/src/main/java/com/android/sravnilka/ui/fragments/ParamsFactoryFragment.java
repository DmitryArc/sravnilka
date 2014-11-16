package com.android.sravnilka.ui.fragments;

import android.app.Activity;

import com.android.sravnilka.IFlowController;
import com.android.sravnilka.R;

import java.util.Set;

/**
 * Created by dka on 12.11.2014.
 */
public class ParamsFactoryFragment extends SourceFactoryFragment {

    public ParamsFactoryFragment(){
        super();
        mHintId = R.string.hint_input_param;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            getActivity().getActionBar().setTitle(getResources().getString(R.string.title_f_params));
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected boolean next(Set<String> items) {
        if(getActivity() instanceof IFlowController){
            return ((IFlowController)getActivity()).onParamSetReady(items);
        }
        return false;
    }

}
