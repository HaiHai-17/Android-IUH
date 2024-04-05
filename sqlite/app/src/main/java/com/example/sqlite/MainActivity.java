package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText editText;
    private Button add, del, repair;
    private ArrayList <Sinhvien> arrayList;
    private SinhvienAdapter arrayAdapter;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        editText = findViewById(R.id.edtName);
        add = findViewById(R.id.btnAdd);
        del = findViewById(R.id.btnDel);
        repair = findViewById(R.id.btnRepair);

        arrayList = new ArrayList<>();
        arrayAdapter = new SinhvienAdapter(MainActivity.this, R.layout.list_item, arrayList);
        listView.setAdapter(arrayAdapter);


        loadDB();
    }

    private void loadDB(){
        try {
            db = SQLiteDatabase.openDatabase("/data/data/com.example.sqlite/files/test.db", null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cs = db.rawQuery("select * from table_name", null);
            while(cs.moveToNext()){
                int id = cs.getInt(0);
                String name = cs.getString(1);

                arrayList.add(new Sinhvien(id, name));
            }
            arrayAdapter.notifyDataSetChanged();
            cs.close();
            db.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void add() {
        try {
            db = SQLiteDatabase.openDatabase("/data/data/com.example.sqlite/files/test.db", null, SQLiteDatabase.OPEN_READWRITE);
            ContentValues cv = new ContentValues();
            cv.put("name", editText.getText().toString());
            long result = db.insert("table_name", null, cv);
            if (result == -1) {
                Toast.makeText(MainActivity.this, "Fail to add", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Add success", Toast.LENGTH_SHORT).show();
                // Thêm đối tượng Sinhvien mới vào danh sách
                arrayList.add(new Sinhvien((int) result, editText.getText().toString()));
                arrayAdapter.notifyDataSetChanged();
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Update() {
        try {
            db = SQLiteDatabase.openDatabase("/data/data/com.example.sqlite/files/test.db", null, SQLiteDatabase.OPEN_READWRITE);
            ContentValues cv = new ContentValues();
            cv.put("name", editText.getText().toString());
            int pos = -1;
            db.update("table_name", cv, "id=?", new String[]{String.valueOf(pos + 1)});
            Toast.makeText(MainActivity.this, "Update success", Toast.LENGTH_SHORT).show();
            // Cập nhật đối tượng Sinhvien trong danh sách
            arrayList.set(pos, new Sinhvien(pos + 1, editText.getText().toString()));
            arrayAdapter.notifyDataSetChanged();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Delete() {
        try {
            db = SQLiteDatabase.openDatabase("/data/data/com.example.sqlite/files/test.db", null, SQLiteDatabase.OPEN_READWRITE);
            int pos = -1;
            db.delete("table_name", "id=?", new String[]{String.valueOf(pos + 1)});
            Toast.makeText(MainActivity.this, "Delete success", Toast.LENGTH_SHORT).show();
            // Xóa đối tượng Sinhvien khỏi danh sách
            arrayList.remove(pos);
            arrayAdapter.notifyDataSetChanged();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}