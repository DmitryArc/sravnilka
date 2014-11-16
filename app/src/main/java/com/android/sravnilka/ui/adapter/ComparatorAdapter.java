package com.android.sravnilka.ui.adapter;

import android.content.Context;
import android.support.v7.internal.widget.TintCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.sravnilka.R;
import com.android.sravnilka.dao.CheckItem;
import com.android.sravnilka.dao.ComparatorItem;

import java.util.ArrayList;

/**
 * Created by Дмитрий on 13.11.2014.
 */
public class ComparatorAdapter extends BaseAdapter{
    private ArrayList<ComparatorItem> mNameData;
    private LayoutInflater inflater;


    public ComparatorAdapter(Context context,ArrayList<ComparatorItem> nameData){
       this.mNameData = nameData;
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
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.textNameComparator.setText(mNameData.get(position).getName());
        viewHolder.checksContainer.removeAllViews();
        for(int i = 0; i < mNameData.get(position).getChecks().size(); i++){
            View check = inflater.inflate(R.layout.v_check_item, null);
            CheckBox checkBox = (CheckBox) check.findViewById(R.id.check_box);
            checkBox.setText(mNameData.get(position).getChecks().get(i).getName());
            checkBox.setChecked(mNameData.get(position).getChecks().get(i).isChecked());
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckBox cb = (CheckBox) view;
                    CheckItem item = (CheckItem) cb.getTag();
                    item.setChecked(cb.isChecked());
                }
            });
            checkBox.setTag(mNameData.get(position).getChecks().get(i));
            viewHolder.checksContainer.addView(check);
        }


        return convertView;
    }
}
