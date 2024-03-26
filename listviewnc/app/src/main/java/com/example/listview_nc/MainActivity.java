package com.example.listview_nc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView lst;
    ArrayList<Country> data;
    ContryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lst = findViewById(R.id.lsView);

        data = new ArrayList<Country>();
        data.add(new Country(R.drawable.vn, "Việt Nam", 90000000));
        data.add(new Country(R.drawable.jp, "Nhật Bản", 60000000));
        data.add(new Country(R.drawable.my, "Mỹ", 120000000));

        adapter = new ContryAdapter(MainActivity.this, R.layout.item_layout, data);

        lst.setAdapter(adapter);

    }
}