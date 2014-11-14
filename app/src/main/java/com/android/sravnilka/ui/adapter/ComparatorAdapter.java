package com.android.sravnilka.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.sravnilka.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Дмитрий on 13.11.2014.
 */
public class ComparatorAdapter extends BaseAdapter{
    private ArrayList<String> mNameData;
    private ArrayList<String> mChecksData;
    private Context mContext;
    private LayoutInflater inflater;


    public ComparatorAdapter(Context context,ArrayList<String> nameData, ArrayList<String> checksData){
       this.mContext = context;
       this.mNameData = nameData;
       this.mChecksData = checksData;
       inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    static class ViewHolder {
        protected TextView textNameComparator;
        protected LinearLayout checksContainer;
    }

    @Override
    public int getCount() {
        return mNameData.size();
    }

    @Override
    public Object getItem(int position) {
        return mNameData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.v_comparator_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textNameComparator = (TextView) convertView.findViewById(R.id.comparator_name);
            viewHolder.checksContainer = (LinearLayout)convertView.findViewById(R.id.check_container);
            for(int i = 0; i < mChecksData.size(); i++){
                View check = inflater.inflate(R.layout.v_check_item, null);
                CheckBox checkBox = (CheckBox) check.findViewById(R.id.check_box);
                TextView checkText = (TextView) check.findViewById(R.id.check_text);
                checkText.setText(mChecksData.get(i));
                viewHolder.checksContainer.addView(check);
            }
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.textNameComparator.setText(mNameData.get(position));

        return convertView;
    }
}
