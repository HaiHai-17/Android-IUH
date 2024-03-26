package com.example.listview_nc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ContryAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<Country> data;

    public ContryAdapter(Context context, int layout, ArrayList<Country> data) {
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
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_layout, null);

        Country country = data.get(position);
        TextView txt_Name = convertView.findViewById(R.id.txtName);
        TextView txt_Pop = convertView.findViewById(R.id.txtPop);
        ImageView img = convertView.findViewById(R.id.imageView);

        txt_Name.setText("Name: " + country.getName());
        txt_Pop.setText("Pop: " + country.getPop());
        img.setImageResource(country.getImage());

        return convertView;
    }
}
