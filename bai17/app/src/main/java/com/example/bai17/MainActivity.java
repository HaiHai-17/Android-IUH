package com.example.bai17;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lst;
    private ArrayList<Name> data;
    private NameAdapter adapter;
    Button nhap;
    EditText edtten;
    TextView giatri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lst = findViewById(R.id.lsView);
        nhap = findViewById(R.id.btnNhap);
        edtten = findViewById(R.id.edtName);
        giatri = findViewById(R.id.txtValue);

        data = new ArrayList<Name>();
        data.add(new Name("Teo"));
        data.add(new Name("Ty"));

        nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtten.getText().toString();
                data.add(new Name(ten));
                adapter.notifyDataSetChanged();
                edtten.setText("");
                edtten.forceLayout();
            }
        });

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                giatri.setText("Vi trí: " + position + " Giá trị: " + data.get(position).getTen());
            }
        });

        adapter = new NameAdapter(R.layout.name_layout, MainActivity.this, data);
        lst.setAdapter(adapter);
    }
}