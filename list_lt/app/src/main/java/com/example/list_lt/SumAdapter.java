package com.example.list_lt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.list_lt.Sum;

import java.util.ArrayList;

public class SumAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Sum> list_kq;

    public SumAdapter(Context context, ArrayList<Sum> list_kq) {
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
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.item_sum, null);

        Sum sum = list_kq.get(position);
        TextView txtKetQua = convertView.findViewById(R.id.txtSum);

        // Đặt dữ liệu vào các TextView
        txtKetQua.setText(sum.toString());

        return convertView;
    }
}
