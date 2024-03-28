package com.example.bai12;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login, exit;
    EditText user, pass;
    CheckBox save;
    String ten, mk, u, p;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.btnLogin);
        exit = findViewById(R.id.btnExit);
        user = findViewById(R.id.edtUser);
        pass = findViewById(R.id.edtPass);
        save = findViewById(R.id.cbSave);

        pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us = user.getText().toString();
                String ps = pass.getText().toString();
                checkLogin(us, ps);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("savelogin", MODE_PRIVATE);
        ten = sharedPreferences.getString("1", u);
        user.setText(ten);
        mk = sharedPreferences.getString("2", p);
        pass.setText(mk);
        check = sharedPreferences.getInt("3", 0);
        save.setChecked(check == 1);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertdialog("Thông báo!", "Bạn có muốn thoát!");
            }
        });
    }

    private void showAlertdialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void checkLogin(String u, String p){
        SharedPreferences sharedPreferences = getSharedPreferences("savelogin", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(u.equals("admin") && p.equals("123")){
            if (save.isChecked()) {
                Toast.makeText(MainActivity.this, "Đăng nhập thành công, lưu thông tin!", Toast.LENGTH_SHORT).show();
                editor.putString("1", u);
                editor.putString("2", p);
                editor.putInt("3", 1);
            }
            else {
                Toast.makeText(MainActivity.this, "Đăng nhập thành công, không lưu thông tin!", Toast.LENGTH_SHORT).show();
                editor.clear();
            }
            editor.commit();
        }
        else {
            Toast.makeText(MainActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
        }
    }
}
