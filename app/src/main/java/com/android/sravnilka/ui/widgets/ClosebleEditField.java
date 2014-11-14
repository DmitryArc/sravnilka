package com.android.sravnilka.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.android.sravnilka.R;

/**
 * Created by Dmitry.Kalyuzhnyi on 11/14/2014.
 */
public class ClosebleEditField extends RelativeLayout {
    private ImageButton vBtnClose;
    private IInputStorage mRootListener;

    public ClosebleEditField(Context context, IInputStorage listener){
        this(context);
        mRootListener = listener;
        mRootListener.onItemCreated(this);
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

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        this.addView(inflater.inflate(R.layout.v_input_field, null));

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
