package com.example.bai20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<Danhsach> data;

    public SpinnerAdapter(Context context, int layout, ArrayList<Danhsach> data) {
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

        Danhsach ds = data.get(position);
        TextView txt = convertView.findViewById(android.R.id.text1);

        txt.setText(ds.getDanhsach());
        return convertView;
    }
}
