package com.example.bai19;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

    public class MainActivity extends AppCompatActivity {

        private ListView listView;
        private List<String> listData;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            listView = findViewById(R.id.list);

            listData = Arrays.asList("Hà Nội", "Huế", "Côn Sơn", "Spa", "Vũng Tàu", "Đà Nẵng");

            CustomAdapter adapter = new CustomAdapter(this, listData);

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String name = listData.get(position);
                    TextView txtDetail = findViewById(R.id.txtView);
                    txtDetail.setText("Tên địa điểm: " + listData.get(position));
                }
            });
        }
    }
