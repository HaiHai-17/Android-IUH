package com.example.bai19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<String> listData;

    public CustomAdapter(Context context, List<String> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
            holder = new ViewHolder();
            holder.imgIcon = convertView.findViewById(R.id.imgTron);
            holder.txtName = convertView.findViewById(R.id.txtTP);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String name = listData.get(position);
        holder.txtName.setText(name);

        if (name.length() <= 3) {
            holder.imgIcon.setImageResource(R.drawable.sao);
        } else {
            holder.imgIcon.setImageResource(R.drawable.tron);
        }

        return convertView;
    }

    private static class ViewHolder {
        ImageView imgIcon;
        TextView txtName;
    }
}

