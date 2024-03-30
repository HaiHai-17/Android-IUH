package com.example.gk_lt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class KetquaAdapter extends BaseAdapter {

    Context context;
    ArrayList<Ketqua> list_kq;

    public KetquaAdapter(Context context, ArrayList<Ketqua> list_kq) {
        this.context = context;
        this.list_kq = list_kq;
    }


    @Override
    public int getCount() {
        return list_kq.size();
    }

    @Override
    public Object getItem(int position) {
        return list_kq.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(MainActivity.this, android.R.layout.activity_list_item, list_kq);
        

        return convertView;
    }
}
