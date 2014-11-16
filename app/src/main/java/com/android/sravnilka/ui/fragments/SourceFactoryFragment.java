package com.android.sravnilka.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.sravnilka.R;
import com.android.sravnilka.RootActivity;
import com.android.sravnilka.ui.widgets.ClosebleEditField;
import com.android.sravnilka.ui.widgets.IInputStorage;
import com.android.sravnilka.utils.StackRandomAccess;

import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Created by dka on 12.11.2014.
 */
public abstract class SourceFactoryFragment extends Fragment implements IInputStorage {
    protected int mHintId;
//    protected Set<String> mData;
    protected Button vNextButton;
    protected LinearLayout vFieldStorage;
    protected Stack<ClosebleEditField> mFieldStack;
    protected int i;

//    public SourceFactoryFragment(Set<String> data){
//        super();
//        mData = data;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(">>>", "onCreate " + i + "savedInstanceState = " + savedInstanceState);
        mFieldStack = new Stack<ClosebleEditField>();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(">>>", "onActivityCreated " + i + "savedInstanceState = " + savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(">>>", "onDetach " + i);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(">>>", "onAttach " + i);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(">>>", "onDestroy " + i);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(">>>", "onDestroyView " + i);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(">>>", "onResume " + i);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(">>>", "onSaveInstanceState " + i);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(">>>", "onViewStateRestored " + i + savedInstanceState);
    }

    /**************************************************************
     * Fragment callbacks
     **************************************************************/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(">>>", "onCreateView " + i);
        return inflater.inflate(R.layout.fr_input, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(">>>", "onViewCreated " + i + "bundle = " + savedInstanceState);

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

    protected abstract Set<String> a();
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

//    protected void addInputField(String data){
//        ClosebleEditField field = new ClosebleEditField(getActivity(), this, mHintId);
//        field.setData(data);
//        vFieldStorage.addView(field);
//    }
}
