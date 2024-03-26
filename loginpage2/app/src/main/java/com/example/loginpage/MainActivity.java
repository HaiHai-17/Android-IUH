package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtten, edtmatkhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtten = findViewById(R.id.edtten);
        edtmatkhau = findViewById(R.id.edtmatkhau);

        edtmatkhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        findViewById(R.id.btndangnhap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtten.getText().toString();
                String password = edtmatkhau.getText().toString();

                if (username.equals("admin") && password.equals("admin"))
                {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btnthoat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}