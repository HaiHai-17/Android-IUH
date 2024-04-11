package com.example.nguyenhoanghai_20115801;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EmployeeAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Employee> data;

    public EmployeeAdapter(Context context, int layout, ArrayList<Employee> data) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, parent, false);
            holder = new ViewHolder();
            holder.maTextView = convertView.findViewById(R.id.txtMaNV);
            holder.tenTextView = convertView.findViewById(R.id.txtTenNV);
            holder.luongTextView = convertView.findViewById(R.id.txtLuong);
            holder.chucvuTextView = convertView.findViewById(R.id.txtChucvu);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Employee ep = data.get(position);
        holder.maTextView.setText(ep.getManv());
        holder.tenTextView.setText(ep.getTennv());
        holder.luongTextView.setText(ep.getLuong());
        holder.chucvuTextView.setText(ep.getChucvu());

        return convertView;
    }

    private static class ViewHolder {
        TextView maTextView;
        TextView tenTextView;
        TextView luongTextView;
        TextView chucvuTextView;

    }
}
