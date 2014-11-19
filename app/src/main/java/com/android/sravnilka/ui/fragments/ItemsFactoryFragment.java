package com.android.sravnilka.ui.fragments;

import android.app.Activity;
import android.os.Bundle;

import com.android.sravnilka.IFlowController;
import com.android.sravnilka.R;
import com.android.sravnilka.ui.widgets.ClosebleEditField;

import java.util.Set;
import java.util.Stack;

/**
 * Created by dka on 12.11.2014.
 */
public class ItemsFactoryFragment extends SourceFactoryFragment {


    public ItemsFactoryFragment(){
        super();
        mHintId = R.string.hint_input_item;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            getActivity().getActionBar().setTitle(getResources().getString(R.string.title_f_items));
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    protected boolean next(Set<String> items) {
        if(getActivity() instanceof IFlowController){
            return ((IFlowController)getActivity()).onItemSetReady(items);
        }
        return false;
    }

}
