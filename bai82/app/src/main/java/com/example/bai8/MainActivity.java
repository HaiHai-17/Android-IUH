package com.example.bai8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnTong2so, btnHieu2so, btnTich2so, btnThuong2so, btnUocChung2so, btnExit;
    EditText edtSoA, edtSoB;
    TextView txtKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSoA = findViewById(R.id.SoA);
        edtSoB = findViewById(R.id.SoB);
        txtKetQua = findViewById(R.id.ketqua);

        // Button click listeners
        btnTong2so = findViewById(R.id.tong2so);
        btnHieu2so = findViewById(R.id.hieu2so);
        btnTich2so = findViewById(R.id.tich2so);
        btnThuong2so = findViewById(R.id.thuong2so);
        btnUocChung2so = findViewById(R.id.uocchung2so);
        btnExit = findViewById(R.id.exit);

        btnTong2so.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edtSoA.getText().toString());
                int b = Integer.parseInt(edtSoB.getText().toString());
                int sum = a + b;
                txtKetQua.setText("Kết quả: " + sum);
            }
        });

        btnHieu2so.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edtSoA.getText().toString());
                int b = Integer.parseInt(edtSoB.getText().toString());
                int difference = a - b;
                txtKetQua.setText("Kết quả: " + difference);
            }
        });

        btnTich2so.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edtSoA.getText().toString());
                int b = Integer.parseInt(edtSoB.getText().toString());
                int product = a * b;
                txtKetQua.setText("Kết quả: " + product);
            }
        });

        btnThuong2so.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edtSoA.getText().toString());
                int b = Integer.parseInt(edtSoB.getText().toString());
                if (b == 0) {
                    txtKetQua.setText("Lỗi: Chia cho 0!");
                } else {
                    int quotient = a / b;
                    txtKetQua.setText("Kết quả: " + quotient);
                }
            }
        });

        btnUocChung2so.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edtSoA.getText().toString());
                int b = Integer.parseInt(edtSoB.getText().toString());
                int ucln = _gcd(a, b);
                txtKetQua.setText("Kết quả: " + ucln);
            }

            private int _gcd(int a, int b) {
                while (b != 0) {
                    int temp = b;
                    b = a % b;
                    a = temp;
                }
                return a;
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
