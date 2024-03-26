package com.example.bai18;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, List<String> items) {
        super(context, android.R.layout.simple_list_item_1, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView= inflater.inflate(android.R.layout.simple_list_item_1, null);

        String item = getItem(position);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(item);

        return convertView;
    }
}

