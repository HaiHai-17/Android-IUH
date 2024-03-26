package com.example.hai_20115801;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaceAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Place> data;

    public PlaceAdapter(Context context, ArrayList<Place> data) {
        this.context = context;
        this.layout = R.layout.item_list;
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

        Place p = data.get(position);
        TextView id = convertView.findViewById(R.id.txtId);
        TextView name = convertView.findViewById(R.id.txtName);
        TextView country = convertView.findViewById(R.id.txtCountry);

        id.setText(String.valueOf(p.getId()));
        name.setText(p.getName());
        country.setText(p.getCountry());

        return convertView;
    }
}
