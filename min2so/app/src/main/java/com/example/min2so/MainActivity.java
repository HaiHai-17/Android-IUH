package com.example.min2so;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText songuyen1, songuyen2;
    private TextView ketqua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songuyen1 = findViewById(R.id.songuyen1);
        songuyen2 = findViewById(R.id.songuyen2);
        ketqua = findViewById(R.id.ketqua);
        findViewById(R.id.thuchien).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sothu1 = Integer.parseInt(songuyen1.getText().toString());
                int sothu2 = Integer.parseInt(songuyen2.getText().toString());

                int min = Math.min(sothu1, sothu2);

                ketqua.setText("Min là: " + min);
            }
        });
    }
}