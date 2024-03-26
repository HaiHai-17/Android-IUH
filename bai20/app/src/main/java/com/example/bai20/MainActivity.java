package com.example.bai20;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private ListView listView;

    private SpinnerAdapter spinnerAdapter;
    private PhoneAdapter listAdapter;

    private ArrayList<Danhsach> spinnerData;
    private ArrayList<Phone> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spnView);
        listView = findViewById(R.id.list);

        spinnerData = new ArrayList<>();
        spinnerData.add(new Danhsach("Samsung Note"));
        spinnerData.add(new Danhsach("Samsung S"));
        spinnerData.add(new Danhsach("Samsung A"));

        listData = new ArrayList<>();

        spinnerAdapter = new SpinnerAdapter(MainActivity.this, android.R.layout.simple_spinner_item , spinnerData);
        spinner.setAdapter(spinnerAdapter);

        listAdapter = new PhoneAdapter(MainActivity.this, R.layout.item_list ,listData);
        listView.setAdapter(listAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = spinnerData.get(position).getDanhsach();
                updateListView(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Phone phone = listData.get(position);
                Toast.makeText(MainActivity.this, "Điện thoạt: " + phone.getPhone(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateListView(String selectedCategory) {
        listData.clear();
        if (selectedCategory.equals("Samsung Note")) {
            listData.add(new Phone("Note 20"));
            listData.add(new Phone("Note 21"));
            listData.add(new Phone("Note 22"));
        } else if (selectedCategory.equals("Samsung S")) {
            listData.add(new Phone("S20"));
            listData.add(new Phone("S21"));
            listData.add(new Phone("S22"));
        } else if (selectedCategory.equals("Samsung A")) {
            listData.add(new Phone("A35"));
            listData.add(new Phone("A55"));
            listData.add(new Phone("A75"));
        }
        listAdapter.notifyDataSetChanged();
    }
}
