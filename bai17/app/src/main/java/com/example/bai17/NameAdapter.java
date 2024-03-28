package com.example.bai17;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
public class NameAdapter extends BaseAdapter {

    int layout;
    Context context;
    ArrayList<Name> data;

    public NameAdapter(int layout, Context context, ArrayList<Name> data) {
        this.layout = layout;
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.name_layout, null);

        Name n = data.get(position);
        TextView txtten = convertView.findViewById(R.id.txt_Ten);

        txtten.setText(n.getTen());

        return convertView;
    }

}