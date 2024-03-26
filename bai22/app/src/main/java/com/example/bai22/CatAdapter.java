package com.example.bai22;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class CatAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<cat> catData;

    public CatAdapter(Context context, int layout, ArrayList<cat> catData) {
        this.context = context;
        this.layout = layout;
        this.catData = catData;
    }

    @Override
    public int getCount() {
        return catData.size();
    }

    @Override
    public Object getItem(int position) {
        return catData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout, parent, false);

            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.img_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        cat c = catData.get(position);
        holder.imageView.setImageResource(c.getImg());

        return convertView;
    }
}
