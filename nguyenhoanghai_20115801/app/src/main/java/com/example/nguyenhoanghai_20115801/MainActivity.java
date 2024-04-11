package com.example.nguyenhoanghai_20115801;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnadd, btnxem;
    ArrayList<Employee> data;
    EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnadd = findViewById(R.id.btnAdd);
        btnxem = findViewById(R.id.btnSeen);
        data = new ArrayList<>();
        adapter = new EmployeeAdapter(MainActivity.this, R.layout.list_activity, data);

        btnxem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmployee();
            }
        });
    }

    public void addEmployee() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Thêm mới Nhân viên");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        builder.setView(view);

        EditText edtmaDialog = view.findViewById(R.id.edtMa);
        EditText edttenDialog = view.findViewById(R.id.edtTen);
        EditText edtluongDialog = view.findViewById(R.id.edtLuong);
        EditText edtchucvuDialog = view.findViewById(R.id.edtChucvu);

        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String ma = edtmaDialog.getText().toString();
                String ten = edttenDialog.getText().toString();
                String luong = edtluongDialog.getText().toString();
                String chucvu = edtchucvuDialog.getText().toString();

                // Kiểm tra nếu mã nhân viên có ít hơn 3 ký tự thì không lưu
                if (ma.length() < 3) {
                    Toast.makeText(MainActivity.this, "Mã nhân viên phải có ít nhất 3 ký tự", Toast.LENGTH_SHORT).show();
                } else {
                    addToDatabase(ma, ten, luong, chucvu);
                    loadDB();
                }
            }
        });

        builder.setNegativeButton("Xoá trắng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edtmaDialog.setText("");
                edttenDialog.setText("");
                edtluongDialog.setText("");
                edtchucvuDialog.setText("");
                edtmaDialog.requestFocus();

                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void loadDB(){
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.nguyenhoanghai_20115801/files/hai.db", null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery("SELECT * FROM hai", null);
            data.clear();
            while(cursor.moveToNext()) {
                String ma = cursor.getString(cursor.getColumnIndex("maso"));
                String ten = cursor.getString(cursor.getColumnIndex("ten"));
                String luong = cursor.getString(cursor.getColumnIndex("luong"));
                String chucvu = cursor.getString(cursor.getColumnIndex("chucvu"));
                data.add(new Employee(ma, ten, luong, chucvu));
            }
            cursor.close();
            db.close();
            adapter.notifyDataSetChanged();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToDatabase(String ma, String ten, String luong, String chucvu) {
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.nguyenhoanghai_20115801/files/hai.db", null, SQLiteDatabase.OPEN_READWRITE);
            ContentValues values = new ContentValues();
            values.put("maso", ma);
            values.put("ten", ten);
            values.put("luong", luong);
            values.put("chucvu", chucvu);
            db.insert("hai", null, values);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuThoat) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}