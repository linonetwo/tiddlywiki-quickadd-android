package com.example.twquickadd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdaptor extends BaseAdapter {

    private List<Bean> mData;
    private Context mContext;

    public ListAdaptor(List<Bean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }



    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from((mContext)).inflate(R.layout.list_item, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.cache_text);
        textView.setText(mData.get(position).getContent());
        return convertView;
    }
}
