package com.example.bai22;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CatActivity extends AppCompatActivity {

    ImageView imageView_cat;
    TextView textView_cat;
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity);

        imageView_cat = findViewById(R.id.imageView);
        textView_cat = findViewById(R.id.textView);
        btn = findViewById(R.id.btnBack);

        Intent integer  = getIntent();
        int img = integer.getIntExtra("image", 0);
        imageView_cat.setImageResource(img);
        String name = integer.getStringExtra("name");
        textView_cat.setText(name);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
