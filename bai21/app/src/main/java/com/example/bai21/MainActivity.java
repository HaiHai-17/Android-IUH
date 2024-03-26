package com.example.bai21;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    Button btnadd;
    GridView gridview;
    Spinner spinner;

    ArrayList<Phone> phoneData;
    ArrayList<Spinners> spinnerData;
    ArrayAdapter<String> autoCompleteAdapter;
    PhoneAdapter phoneAdapter;
    SpinnerAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        spinner = findViewById(R.id.spnView);
        autoCompleteTextView = findViewById(R.id.autoText);
        btnadd = findViewById(R.id.btnAdd);
        gridview = findViewById(R.id.gridView);

        spinnerData = new ArrayList<Spinners>();
        spinnerData.add(new Spinners("Samsung Note"));
        spinnerData.add(new Spinners("Samsung S"));
        spinnerData.add(new Spinners("Samsung A"));
        spinnerAdapter =new SpinnerAdapter(MainActivity.this, android.R.layout.simple_spinner_item, spinnerData);
        spinner.setAdapter(spinnerAdapter);

        phoneData = new ArrayList<>();
        phoneAdapter = new PhoneAdapter(MainActivity.this, android.R.layout.simple_expandable_list_item_1, phoneData);
        gridview.setAdapter(phoneAdapter);

        autoCompleteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView.setAdapter(autoCompleteAdapter);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = autoCompleteTextView.getText().toString();
                phoneData.add(new Phone(value));
                phoneAdapter.notifyDataSetChanged();

                autoCompleteAdapter.add(value);
                autoCompleteAdapter.notifyDataSetChanged();

                autoCompleteTextView.setText("");
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ds = spinnerData.get(position).getDanhsach();
                UpdateGrid(ds);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = phoneData.get(position).getPhone();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông tin sản phẩm");
                builder.setMessage("Đây là mẫu điện thoại: " + value);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

    }
    public void UpdateGrid(String value){
        phoneData.clear();
        if(value.equals("Samsung Note")){
            phoneData.add(new Phone("Samsung Note 8"));
            phoneData.add(new Phone("Samsung Note 9"));
        }
        else if(value.equals("Samsung S")){
            phoneData.add(new Phone("Samsung S 8"));
            phoneData.add(new Phone("Samsung S9"));
        }
        else if(value.equals("Samsung A")){
            phoneData.add(new Phone("Samsung A52"));
            phoneData.add(new Phone("Samsung A73"));
        }
        phoneAdapter.notifyDataSetChanged();
    }
}