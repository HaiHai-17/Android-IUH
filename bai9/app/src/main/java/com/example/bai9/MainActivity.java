package com.example.bai9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btndoC, btndoF, btnxoa;
    EditText edtdoC, edtdoF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btndoC = findViewById(R.id.btnCelsius);
        btndoF = findViewById(R.id.btnFahrenheit);
        btnxoa = findViewById(R.id.btnClear);
        edtdoC = findViewById(R.id.doC);
        edtdoF = findViewById(R.id.doF);

        btndoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenDoi(edtdoF, edtdoC); // Chuyển từ độ F sang độ C
            }
        });

        btndoF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenDoi(edtdoC, edtdoF); // Chuyển từ độ C sang độ F
            }
        });

        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtdoC.setText("");
                edtdoF.setText("");
            }
        });
    }

    private void chuyenDoi(EditText edtNhap, EditText edtKetQua) {
        try {
            double giaTriNhap = Double.parseDouble(edtNhap.getText().toString());
            double ketQua;

            if (edtNhap == edtdoF) {  // Chuyển từ độ F sang độ C
                ketQua = (giaTriNhap - 32) * 5 / 9;
            } else {                  // Chuyển từ độ C sang độ F
                ketQua = giaTriNhap * 9 / 5 + 32;
            }

            edtKetQua.setText(String.valueOf(ketQua));
        } catch (NumberFormatException e) {
            edtKetQua.setText("Lỗi: Nhập số không hợp lệ");
        }
    }
}
