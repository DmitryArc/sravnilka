package com.android.sravnilka.ui.fragments;

import android.app.Fragment;

import com.android.sravnilka.R;

/**
 * Created by dka on 12.11.2014.
 */
public class ResultsFragment extends Fragment {

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            getActivity().getActionBar().setTitle(getResources().getString(R.string.title_f_result));
        }
    }
}
