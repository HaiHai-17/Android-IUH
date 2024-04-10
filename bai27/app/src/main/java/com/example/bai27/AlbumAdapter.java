package com.example.bai27;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AlbumAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<Album> dataAlbum;

    public AlbumAdapter(Context context, int layout, ArrayList<Album> dataAlbum) {
        this.context = context;
        this.layout = layout;
        this.dataAlbum = dataAlbum;
    }


    @Override
    public int getCount() {
        return dataAlbum.size();
    }

    @Override
    public Object getItem(int position) {
        return dataAlbum.get(position);
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
            holder.idTextView = convertView.findViewById(R.id.txtId);
            holder.maTextView = convertView.findViewById(R.id.txtMaAlbum);
            holder.nameTextView = convertView.findViewById(R.id.txtName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Album album = dataAlbum.get(position);
        holder.idTextView.setText(String.valueOf(album.getStt()));
        holder.maTextView.setText(album.getMa());
        holder.nameTextView.setText(album.getTen());

        return convertView;
    }

    private static class ViewHolder {
        TextView idTextView;
        TextView maTextView;
        TextView nameTextView;

    }
}
