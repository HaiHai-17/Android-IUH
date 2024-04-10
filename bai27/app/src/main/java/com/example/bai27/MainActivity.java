package com.example.bai27;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnadd, btnxem;
    EditText edtma, edtten;
    ArrayList<Album> data;
    AlbumAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnadd = findViewById(R.id.btnAdd);
        btnxem = findViewById(R.id.btnSeen);
        edtten = findViewById(R.id.edtTen);
        edtma = findViewById(R.id.edtMa);
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
                String ma = edtma.getText().toString();
                String ten = edtten.getText().toString();
                add(ma, ten);
            }
        });
    }

    public void add(String ma, String ten){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Thêm mới ALbum");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        builder.setView(view);

        edtma.setText(ma);
        edtten.setText(ten);
        builder.setPositiveButton("Xoá trắng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edtma.setText("");
                edtten.setText("");
                edtma.requestFocus();
            }
        });

        builder.setPositiveButton("Lưu Album", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}