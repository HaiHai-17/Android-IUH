package com.example.bai22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int img[] = {R.drawable.hinh1, R.drawable.hinh2, R.drawable.hinh3, R.drawable.hinh4, R.drawable.hinh5,
            R.drawable.hinh6, R.drawable.hinh7, R.drawable.hinh8, R.drawable.hinh9};
    String img_name[] = {"Hình 1", "Hình 2", "Hình 3", "Hình 4", "Hình 5", "Hình 6", "Hình 7", "Hình 8", "Hình 9"};
    GridView gridView;
    ArrayList<cat> catList;
    CatAdapter catAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.grid);
        catList = new ArrayList<>();
        for (int i = 0; i < img_name.length; i++) {
            catList.add(new cat(img_name[i], img[i]));
        }

        catAdapter = new CatAdapter(MainActivity.this, R.layout.image_item, catList);
        gridView.setAdapter(catAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, CatActivity.class);
                intent.putExtra("image", img[position]);
                intent.putExtra("name", img_name[position]);
                startActivity(intent);
            }
        });
    }
}
