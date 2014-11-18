package com.android.sravnilka.ui.fragments;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.LayoutTransition;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

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
                if(field.getParent() != null){
                    ((ViewGroup) field.getParent()).removeAllViews();
                }
                vFieldStorage.addView(field);
            }
        } else if(vFieldStorage.getChildCount() == 0){
            addInputField();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Animator disappearAnimator = AnimatorInflater.loadAnimator(getActivity(), R.animator.disappear_animator);
//        vFieldStorage.getLayoutTransition().setAnimator(LayoutTransition.DISAPPEARING, disappearAnimator);

        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(getView(), 0);


    }

    /**************************************************************
     * Flow methods
     **************************************************************/
    protected abstract boolean next(Set<String> items);

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
                if (next(data)){
//                    final Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            if(vFieldStorage != null) {
//                                vFieldStorage.removeAllViews();
//                                vFieldStorage = null;
//                            }
//                        }
//                    }, getResources().getInteger(android.R.integer.config_mediumAnimTime));

                    final LayoutTransition transitioner = new LayoutTransition();
                    vFieldStorage.setLayoutTransition(transitioner);
                    Animator disappearAnimator = AnimatorInflater.loadAnimator(getActivity(), R.animator.disappear_animator);
                    transitioner.setAnimator(LayoutTransition.DISAPPEARING, disappearAnimator);

                    vFieldStorage.removeAllViews();
                    vFieldStorage = null;
                }

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
