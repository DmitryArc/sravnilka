package com.android.sravnilka.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.sravnilka.R;
import com.android.sravnilka.ui.widgets.ClosebleEditField;
import com.android.sravnilka.ui.widgets.IInputStorage;

import java.util.Set;

/**
 * Created by dka on 12.11.2014.
 */
public abstract class SourceFactoryFragment extends Fragment implements IInputStorage {
    protected Set<String> mData;
    protected Button vNextButton;
    protected LinearLayout vFieldStorage;

    /**************************************************************
     * Fragment callbacks
     **************************************************************/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fr_sorter for this fragment
        return inflater.inflate(R.layout.fr_test, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vNextButton = (Button) view.findViewById(R.id.btn_next);
        vNextButton.setOnClickListener(mNextButtonListener);
        vFieldStorage = (LinearLayout) view.findViewById(R.id.lt_input_storage);
    }

    /**************************************************************
     * Flow methods
     **************************************************************/
    protected abstract void next(Set<String> items);

    protected View.OnClickListener mNextButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(validateData()){
                next(mData);
            }
        }
    };

    protected boolean validateData() {
        return true;
    }

    /**************************************************************
     * IInputStorage implementation
     **************************************************************/
    @Override
    public void onCloseButtonClicked(ClosebleEditField field) {
        if(vFieldStorage != null){
            vFieldStorage.removeView(field);
        }
    }
}
