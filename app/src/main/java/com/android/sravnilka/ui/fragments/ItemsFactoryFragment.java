package com.android.sravnilka.ui.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.sravnilka.IFlowController;
import com.android.sravnilka.R;
import com.android.sravnilka.RootActivity;

import java.util.Set;

/**
 * Created by dka on 12.11.2014.
 */
@SuppressLint("ValidFragment")
public class ItemsFactoryFragment extends SourceFactoryFragment {

    public ItemsFactoryFragment(){
        super();
        mHintId = R.string.hint_input_item;
        i = 1;
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

    protected Set<String> a(){
        return ((RootActivity)getActivity()).a();
    }
}
