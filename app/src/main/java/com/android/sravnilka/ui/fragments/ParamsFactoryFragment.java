package com.android.sravnilka.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.inputmethod.InputMethodManager;

import com.android.sravnilka.IFlowController;
import com.android.sravnilka.R;
import com.android.sravnilka.ui.widgets.ClosebleEditField;

import java.io.Serializable;
import java.util.Set;
import java.util.Stack;

/**
 * Created by dka on 12.11.2014.
 */
public class ParamsFactoryFragment extends SourceFactoryFragment {

    public static ParamsFactoryFragment newInstance(boolean reloadPreviousState){
        ParamsFactoryFragment fragment = new ParamsFactoryFragment();
        Bundle args = new Bundle();
        args.putBoolean(EXTRA_RELOAD, reloadPreviousState);
        fragment.setArguments(args);
        return fragment;
    }

    public ParamsFactoryFragment(){
        super();
        mHintId = R.string.hint_input_param;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if(savedInstanceState == null || !savedInstanceState.getBoolean(EXTRA_RELOAD) ||
//                mFieldStack == null) {
//            mFieldStack = new Stack<ClosebleEditField>();
//        }
//    }

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
                            imm.showSoftInput(cef, InputMethodManager.SHOW_IMPLICIT);
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
        if(getActivity() instanceof IFlowController) {
            if (((IFlowController) getActivity()).onParamSetReady(items)) {
                final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                return true;
            }
        }
        return false;
    }

}
