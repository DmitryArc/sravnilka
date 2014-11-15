package com.android.sravnilka.ui.fragments;

import android.app.Activity;

import com.android.sravnilka.IFlowController;
import com.android.sravnilka.R;
import com.android.sravnilka.ui.widgets.ClosebleEditField;

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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        getActivity().getActionBar().setTitle("Params");
    }

    @Override
    protected void next(Set<String> items) {
        if(getActivity() instanceof IFlowController){
            // TODO uncomment
            ((IFlowController)getActivity()).onParamSetReady(mData);
            // TODO remove next code
//            vFieldStorage.addView(new ClosebleEditField(getActivity(), this));
        }
    }
}
