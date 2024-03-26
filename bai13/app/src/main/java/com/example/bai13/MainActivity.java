package com.example.bai13;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bai13.R;

public class MainActivity extends AppCompatActivity {

    private EditText edtNamDuongLich;
    private TextView txtNamAmLich;
    private Button btnChuyenDoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNamDuongLich = findViewById(R.id.editTextDuongLich);
        txtNamAmLich = findViewById(R.id.textViewChuyenDoi);

        btnChuyenDoi = findViewById(R.id.buttonChuyenDoi);
        btnChuyenDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenDoi(v);
            }
        });
    }
    public void chuyenDoi(View view) {
        int namDuongLich = Integer.parseInt(edtNamDuongLich.getText().toString());
        int can = namDuongLich % 10;
        int chi = namDuongLich % 12;

        String[] arrCan = {"Canh", "Tân", "Nhâm", "Quý", "Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ"};
        String[] arrChi = {"Thân", "Dậu", "Tuất", "Hợi", "Tý", "Sửu", "Dần", "Mẹo", "Thìn", "Tỵ", "Ngọ", "Mùi"};

        String namAmLich = arrCan[can] + " " + arrChi[chi];
        txtNamAmLich.setText(namAmLich);
    }
}
