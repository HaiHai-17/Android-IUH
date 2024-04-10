package com.example.bai27;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnadd, btnxem;
    ArrayList<Album> data;
    AlbumAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnadd = findViewById(R.id.btnAdd);
        btnxem = findViewById(R.id.btnSeen);
        data = new ArrayList<>();
        adapter = new AlbumAdapter(MainActivity.this, R.layout.list_album, data);

        btnxem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlbum();
            }
        });
    }

    public void addAlbum() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Thêm mới Album");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        builder.setView(view);

        EditText edtmaDialog = view.findViewById(R.id.edtMa);
        EditText edttenDialog = view.findViewById(R.id.edtTen);

        builder.setPositiveButton("Lưu Album", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String ma = edtmaDialog.getText().toString();
                String ten = edttenDialog.getText().toString();
                addToDatabase(ma, ten);
            }
        });

        builder.setNegativeButton("Xoá trắng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edtmaDialog.setText("");
                edttenDialog.setText("");
                edtmaDialog.requestFocus();

                dialog.dismiss();
            }
        });

        builder.show();
    }

    public void addToDatabase(String ma, String ten) {
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.bai27/files/album.db", null, SQLiteDatabase.OPEN_READWRITE);
            ContentValues values = new ContentValues();
            values.put("ma", ma);
            values.put("ten", ten);
            db.insert("album", null, values);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}