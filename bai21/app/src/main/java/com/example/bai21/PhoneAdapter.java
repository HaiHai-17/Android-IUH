package com.example.bai21;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PhoneAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<Phone> data;

    public PhoneAdapter(Context context, int layout, ArrayList<Phone> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        Phone p= data.get(position);
        TextView txt = convertView.findViewById(android.R.id.text1);

        txt.setText(p.getPhone());
        return convertView;
    }
}
