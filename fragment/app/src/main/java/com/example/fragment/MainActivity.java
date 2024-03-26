package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    Button btnHome, btnContact;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo biến
        btnHome = findViewById(R.id.btntrangchu);
        btnContact = findViewById(R.id.btnlienhe);
        frameLayout = findViewById(R.id.fragmentmain);

        fragmentManager = getSupportFragmentManager();

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment1 fragment1 = new Fragment1();
                fragmentManager.beginTransaction().replace(R.id.fragmentmain, fragment1).commit();
            }
        });

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment2 fragment2 = new Fragment2();
                fragmentManager.beginTransaction().replace(R.id.fragmentmain, fragment2).commit();
            }
        });
    }
}
