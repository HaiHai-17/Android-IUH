package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private EditText editText;
    private Button add, del, repair;
    private ArrayList<Sinhvien> arrayList;
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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy ID từ EditText
                int idToDelete = Integer.parseInt(editText.getText().toString());

                // Gọi phương thức xóa dựa trên ID
                delete(idToDelete);
                loadDB();
                // Xóa văn bản trong EditText sau khi xóa
                editText.setText("");
            }
        });

        repair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy ID từ EditText
                int idToUpdate = Integer.parseInt(editText.getText().toString());

                // Gọi phương thức cập nhật dựa trên ID
                update(idToUpdate);
                loadDB();
                // Xóa văn bản trong EditText sau khi cập nhật
                editText.setText("");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy đối tượng Sinhvien tương ứng với mục được chọn
                Sinhvien selectedSinhvien = arrayList.get(position);

                // Hiển thị thông tin ID của Sinhvien lên EditText
                editText.setText(String.valueOf(selectedSinhvien.getId()));
            }
        });
    }

    private void loadDB(){
        try {
            db = SQLiteDatabase.openDatabase("/data/data/com.example.sqlite/files/test.db", null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery("SELECT * FROM test", null);
            arrayList.clear();
            while(cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                arrayList.add(new Sinhvien(id, name));
            }
            arrayAdapter.notifyDataSetChanged();
            cursor.close();
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
            long result = db.insert("test", null, cv);
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

    private void update(int position) {
        try {
            db = SQLiteDatabase.openDatabase("/data/data/com.example.sqlite/files/test.db", null, SQLiteDatabase.OPEN_READWRITE);
            ContentValues cv = new ContentValues();
            cv.put("name", editText.getText().toString());
            int result = db.update("test", cv, "id=?", new String[]{String.valueOf(arrayList.get(position).getId())});
            if (result > 0) {
                Toast.makeText(MainActivity.this, "Update success", Toast.LENGTH_SHORT).show();
                // Cập nhật đối tượng Sinhvien trong danh sách
                arrayList.get(position).setName(editText.getText().toString());
                arrayAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(MainActivity.this, "Update fail", Toast.LENGTH_SHORT).show();
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void delete(int id) {
        try {
            db = SQLiteDatabase.openDatabase("/data/data/com.example.sqlite/files/test.db", null, SQLiteDatabase.OPEN_READWRITE);
            int result = db.delete("test", "id=?", new String[]{String.valueOf(id)});
            if (result > 0) {
                Toast.makeText(MainActivity.this, "Delete success", Toast.LENGTH_SHORT).show();
                // Xóa đối tượng Sinhvien khỏi danh sách
                // (trong trường hợp này, không cần phải xóa khỏi danh sách hiển thị)
                //arrayList.remove(position);
                arrayAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(MainActivity.this, "Delete fail", Toast.LENGTH_SHORT).show();
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
