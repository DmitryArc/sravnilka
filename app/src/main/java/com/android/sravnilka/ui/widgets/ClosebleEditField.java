package com.android.sravnilka.ui.widgets;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.android.sravnilka.R;

/**
 * Created by Dmitry.Kalyuzhnyi on 11/14/2014.
 */
public class ClosebleEditField extends RelativeLayout {
    private int mHintId;
    private EditText vEditText;
    private ImageButton vBtnClose;
    private IInputStorage mRootListener;

    public ClosebleEditField(Context context, IInputStorage listener, int hintId){
        super(context);
        mRootListener = listener;
        mRootListener.onItemCreated(this);
        mHintId = hintId;
        init(context);
    }

    public ClosebleEditField(Context context) {
        super(context);
        init(context);
    }

    public ClosebleEditField(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ClosebleEditField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ClosebleEditField(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public boolean hasData(){
        return vBtnClose.getVisibility() == View.VISIBLE;
    }

    public String getData(){
        if(vEditText == null){
            return "";
        }
        return vEditText.getText().toString();
    }

    private void init(Context context){
        this.setId(hashCode());

        LayoutInflater inflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        this.addView(inflater.inflate(R.layout.v_input_field, null));

        vEditText = (EditText) findViewById(R.id.et_input_edit);
        vEditText.setHint(mHintId);
        vEditText.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                if(mRootListener != null && s.length() > 0){
                    mRootListener.onStartTyping(ClosebleEditField.this);
                    vBtnClose.setVisibility(View.VISIBLE);
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
                // NOP
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
                // NOP
            }
        });

        vBtnClose = (ImageButton) findViewById(R.id.btn_input_close);
        vBtnClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRootListener != null){
                    mRootListener.onCloseButtonClicked(ClosebleEditField.this);
                }
            }
        });
    }
}
