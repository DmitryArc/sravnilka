package com.android.sravnilka.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.sravnilka.IFlowController;
import com.android.sravnilka.R;

import java.util.Set;

/**
 * Created by dka on 12.11.2014.
 */
public class ItemsFactoryFragment extends SourceFactoryFragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        getActivity().getActionBar().setTitle("Items");
    }

    @Override
    protected void next(Set<String> items) {
        if(getActivity() instanceof IFlowController){
            ((IFlowController)getActivity()).onItemSetReady(mData);
        }
    }
}
