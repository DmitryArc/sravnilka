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
import java.util.Stack;
import java.util.TreeSet;

/**
 * Created by dka on 12.11.2014.
 */
public abstract class SourceFactoryFragment extends Fragment implements IInputStorage {
    protected int mHintId;
    protected Button vNextButton;
    protected LinearLayout vFieldStorage;
    protected Stack<ClosebleEditField> mFieldStack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFieldStack = new Stack<ClosebleEditField>();
    }

    /**************************************************************
     * Fragment callbacks
     **************************************************************/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_input, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vNextButton = (Button) view.findViewById(R.id.btn_next);
        vNextButton.setOnClickListener(mNextButtonListener);
        vFieldStorage = (LinearLayout) view.findViewById(R.id.lt_input_storage);

        if(!mFieldStack.isEmpty()){
            vFieldStorage.removeAllViews();
            for(ClosebleEditField field : mFieldStack){
                vFieldStorage.addView(field);
            }
        } else if(vFieldStorage.getChildCount() == 0){
            addInputField();
        }
    }

    /**************************************************************
     * Flow methods
     **************************************************************/
    protected abstract void next(Set<String> items);

    protected View.OnClickListener mNextButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(validateData()){
                Set<String> data = new TreeSet<String>();
                for(ClosebleEditField field : mFieldStack){
                    if(field.hasData() && !field.getData().isEmpty()) {
                        data.add(field.getData());
                    }
                }
                next(data);
                vFieldStorage.removeAllViews();
                vFieldStorage = null;
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
    public void onItemCreated(ClosebleEditField field) {
        mFieldStack.push(field);
    }

    @Override
    public void onStartTyping(ClosebleEditField field) {
        if(mFieldStack.peek() != null && mFieldStack.peek().equals(field)){
            addInputField();
        }
    }

    @Override
    public void onCloseButtonClicked(ClosebleEditField field) {
        if(vFieldStorage != null){
            vFieldStorage.removeView(field);
            mFieldStack.remove(field);
        }
    }

    protected void addInputField(){
        vFieldStorage.addView(new ClosebleEditField(getActivity(), this, mHintId));
    }

}
