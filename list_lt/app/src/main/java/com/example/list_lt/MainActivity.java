package com.example.list_lt;

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

    EditText edtA, edtB;
    TextView txtKq;
    Button sum, clear, del;
    ListView lst;
    ArrayList<Sum> data_sum;
    SumAdapter sumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtA = findViewById(R.id.SoA);
        edtB = findViewById(R.id.SoB);
        txtKq = findViewById(R.id.ketqua);
        sum = findViewById(R.id.btnSum);
        clear = findViewById(R.id.btnClear);
        del = findViewById(R.id.btnDelete);
        lst = findViewById(R.id.list);

        data_sum = new ArrayList<>();
        sumAdapter = new SumAdapter(this, data_sum); // Khởi tạo SumAdapter và gắn với data_sum
        lst.setAdapter(sumAdapter);

        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edtA.getText().toString());
                int b = Integer.parseInt(edtB.getText().toString());
                int c = a + b;
                txtKq.setText("Kết quả: " + a + "+" + b + "=" + c);
                data_sum.add(new Sum(a, b, c));
                sumAdapter.notifyDataSetChanged();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtA.setText("");
                edtB.setText("");
                edtA.requestFocus();
                txtKq.setText("Kết quả: ");
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_sum.clear();
                sumAdapter.notifyDataSetChanged();
            }
        });

    }
}
