package com.example.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SinhvienAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<Sinhvien> data;

    public SinhvienAdapter(Context context, int layout, ArrayList<Sinhvien> data) {
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
        convertView = inflater.inflate(R.layout.list_item, null);

        Sinhvien sv = data.get(position);
        TextView id = convertView.findViewById(R.id.txtId);
        TextView name = convertView.findViewById(R.id.txtName);

        id.setText(sv.getId());
        name.setText(sv.getName());

        return convertView;
    }
}
