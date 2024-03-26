package com.example.bai20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.imgIcon = convertView.findViewById(R.id.imgView);
            holder.txtPhone = convertView.findViewById(R.id.txtPhone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Phone phone = data.get(position);
        holder.txtPhone.setText(phone.getPhone());

        if (phone.getPhone().length() <= 3) {
            holder.imgIcon.setImageResource(R.drawable.tron);
        } else {
            holder.imgIcon.setImageResource(R.drawable.sao);
        }

        return convertView;
    }

    private static class ViewHolder {
        ImageView imgIcon;
        TextView txtPhone;
    }
}
