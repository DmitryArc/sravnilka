package com.android.sravnilka.ui.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.android.sravnilka.IFlowController;
import com.android.sravnilka.R;
import com.android.sravnilka.RootActivity;
import com.android.sravnilka.ui.widgets.ClosebleEditField;

import java.util.Set;

/**
 * Created by dka on 12.11.2014.
 */
@SuppressLint("ValidFragment")
public class ParamsFactoryFragment extends SourceFactoryFragment {

    public ParamsFactoryFragment(){
        super();
        mHintId = R.string.hint_input_param;
        i = 2;
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
            ((IFlowController)getActivity()).onParamSetReady(items);
            // TODO remove next code
//            vFieldStorage.addView(new ClosebleEditField(getActivity(), this));
        }
    }

    protected Set<String> a(){
        return null;
    }
}
