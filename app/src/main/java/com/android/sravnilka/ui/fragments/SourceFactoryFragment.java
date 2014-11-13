package com.android.sravnilka.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.sravnilka.R;

import java.util.Set;

/**
 * Created by dka on 12.11.2014.
 */
public abstract class SourceFactoryFragment extends Fragment {
    protected Set<String> mData;
    protected Button vNextButton;

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
    }

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
}
