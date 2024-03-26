package com.example.relativelayout2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    private EditText soa, sob, soc;
    private TextView ketqua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soa = findViewById(R.id.edtnhapa);
        sob = findViewById(R.id.edtnhapb);
        soc = findViewById(R.id.edtnhapc);
        ketqua = findViewById(R.id.txketqua);

        findViewById(R.id.btnketqua).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double a = Double.parseDouble(soa.getText().toString());
                double b = Double.parseDouble(sob.getText().toString());
                double c = Double.parseDouble(soc.getText().toString());

                if (a!=0)
                {
                    double delta = b * b - 4 * a * c;
                    double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                    double x2 = (-b - Math.sqrt(delta)) / (2 * a);

                    DecimalFormat x11 = new DecimalFormat("#.###");
                    DecimalFormat x22 = new DecimalFormat("#.###");

                    String x111 = x11.format(x1);
                    String x222 = x22.format(x2);
                    ketqua.setText("PT có 2 No: " + x111 + ", " + x222);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Số a không thể bằng 0!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btnthoat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btnnext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soa.setText("");
                sob.setText("");
                soc.setText("");
                ketqua.setText("PT có 2 No: ");
            }
        });
    }
}