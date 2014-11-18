package com.android.sravnilka.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.inputmethod.InputMethodManager;

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
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            getActivity().getActionBar().setTitle(getResources().getString(R.string.title_f_params));
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(vFieldStorage.getChildCount() > 0) {
                        ClosebleEditField cef = (ClosebleEditField) vFieldStorage.getChildAt(vFieldStorage.getChildCount() - 1);
                        if (cef != null) {
                            cef.requestFocus();

                            final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(cef, 0);
                        } else {
                            final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                        }
                    }
                }
            }, 0);

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
