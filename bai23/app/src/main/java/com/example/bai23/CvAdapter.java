package com.example.bai23;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class CvAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Congviec> data;

    public CvAdapter(Context context, int layout, ArrayList<Congviec> data) {
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
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, parent, false);
        }

        // Lấy thông tin công việc tại vị trí position
        Congviec cv = data.get(position);

        // Hiển thị thông tin công việc lên các TextView trong layout
        TextView txtCongViec = convertView.findViewById(R.id.txtCv);
        TextView txtNoiDung = convertView.findViewById(R.id.txtNd);
        TextView txtNgay = convertView.findViewById(R.id.txtDay_List);
        TextView txtGio = convertView.findViewById(R.id.txtTime_List);

        txtCongViec.setText(cv.getTenCongViec());
        txtNoiDung.setText(cv.getNoiDung());
        txtNgay.setText(cv.getNgay());
        txtGio.setText(cv.getGio());

        return convertView;
    }

}
