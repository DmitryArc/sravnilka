package com.android.sravnilka.ui.fragments;

import android.app.Activity;

import com.android.sravnilka.IFlowController;
import com.android.sravnilka.R;

import java.util.Set;

/**
 * Created by dka on 12.11.2014.
 */
public class ItemsFactoryFragment extends SourceFactoryFragment {

    public ItemsFactoryFragment(){
        super();
        mHintId = R.string.hint_input_item;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        getActivity().getActionBar().setTitle("Items");
    }

    @Override
    protected void next(Set<String> items) {
        if(getActivity() instanceof IFlowController){
            ((IFlowController)getActivity()).onItemSetReady(items);
        }
    }

}
