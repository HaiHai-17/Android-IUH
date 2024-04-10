package com.example.bai27;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    TextView stt, ma, ten;
    ArrayList<Album> arrayList;
    AlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_album);

        listView = findViewById(R.id.listAlbum);
        stt = findViewById(R.id.txtId);
        ma = findViewById(R.id.txtMaAlbum);
        ten = findViewById(R.id.txtName);

        arrayList = new ArrayList<>();
        albumAdapter = new AlbumAdapter(ListActivity.this, R.layout.list_item, arrayList);
        listView.setAdapter(albumAdapter);

        loadDB();
    }

    private void loadDB(){
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.bai27/files/album.db", null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery("SELECT * FROM album", null);
            arrayList.clear();
            while(cursor.moveToNext()){
                int stt = cursor.getInt(cursor.getColumnIndex("stt"));
                String ma = cursor.getString(cursor.getColumnIndex("ma"));
                String ten = cursor.getString(cursor.getColumnIndex("ten"));
                arrayList.add(new Album(stt, ma, ten));
            }
            albumAdapter.notifyDataSetChanged();
            cursor.close();
            db.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
