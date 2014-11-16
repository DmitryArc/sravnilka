package com.android.sravnilka.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.sravnilka.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by dka on 16.11.2014.
 */
public class ResultsAdapter extends BaseAdapter {
    private final ArrayList mData;
    private final LayoutInflater mInflater;

    public ResultsAdapter(Context context, Map<String, Integer> map){
        mData = new ArrayList();
        mData.addAll(map.entrySet());
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Map.Entry<String, Integer> getItem(int position) {
        return (Map.Entry) mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.v_result_item, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.value = (TextView)convertView.findViewById(R.id.tv_percent);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Map.Entry<String, Integer> item = getItem(position);

        viewHolder.name.setText(item.getKey());
        viewHolder.value.setText(item.getValue()+"");


        return convertView;
    }

    static class ViewHolder {
        protected TextView name;
        protected TextView value;
    }
}
