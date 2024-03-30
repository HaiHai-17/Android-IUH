package com.example.gk_lt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtA, edtB;
    TextView txtKq;
    Button sum, clear, del;
    ListView lst;
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

        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edtA.getText().toString());
                int b = Integer.parseInt(edtB.getText().toString());
                int c = a + b;
                txtKq.setText(c);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtA.setText("");
                edtB.setText("");
                edtA.requestFocus();
                txtKq.setText("");
            }
        });


    }
}