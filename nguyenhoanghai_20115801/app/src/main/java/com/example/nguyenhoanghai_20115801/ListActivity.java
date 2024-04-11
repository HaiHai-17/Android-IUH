package com.example.nguyenhoanghai_20115801;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    TextView ma, ten, luong, chucvu;
    ArrayList<Employee> arrayList;
    EmployeeAdapter adapter;
    private Employee selectedEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        listView = findViewById(R.id.list);
        ma = findViewById(R.id.txtMaNV);
        ten = findViewById(R.id.txtTenNV);
        luong = findViewById(R.id.txtLuong);
        chucvu = findViewById(R.id.txtChucvu);

        arrayList = new ArrayList<>();
        adapter = new EmployeeAdapter(ListActivity.this, R.layout.list_item, arrayList);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        adapter.notifyDataSetChanged();

        loadDB();
    }

    private void loadDB(){
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.nguyenhoanghai_20115801/files/hai.db", null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery("SELECT * FROM hai", null);
            arrayList.clear();
            while(cursor.moveToNext()) {
                String ma = cursor.getString(cursor.getColumnIndex("maso"));
                String ten = cursor.getString(cursor.getColumnIndex("ten"));
                String luong = cursor.getString(cursor.getColumnIndex("luong"));
                String chucvu = cursor.getString(cursor.getColumnIndex("chucvu"));
                arrayList.add(new Employee(ma, ten, luong, chucvu));
            }
            cursor.close();
            db.close();
            adapter.notifyDataSetChanged();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        selectedEmployee = arrayList.get(info.position);
        int id = item.getItemId();
        if (id == R.id.menuAdd) {
            addEmployee();
        } else if (id == R.id.menuRepair) {
            editEmployee(selectedEmployee);
        } else if (id == R.id.menuDel) {
            deleteEmployee(selectedEmployee);
        }
        return super.onContextItemSelected(item);
    }

    public void addEmployee() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
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
                    Toast.makeText(ListActivity.this, "Mã nhân viên phải có ít nhất 3 ký tự", Toast.LENGTH_SHORT).show();
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

    private void editEmployee(Employee employee) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
        builder.setTitle("Chỉnh sửa thông tin Nhân viên");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        builder.setView(view);

        EditText edtmaDialog = view.findViewById(R.id.edtMa);
        EditText edttenDialog = view.findViewById(R.id.edtTen);
        EditText edtluongDialog = view.findViewById(R.id.edtLuong);
        EditText edtchucvuDialog = view.findViewById(R.id.edtChucvu);

        // Điền thông tin của nhân viên vào EditTexts
        edtmaDialog.setText(String.valueOf(employee.getManv())); // Sử dụng String.valueOf() để chuyển đổi int thành String
        edtmaDialog.setEnabled(false);
        edttenDialog.setText(employee.getTennv());
        edtluongDialog.setText(String.valueOf(employee.getLuong())); // Tương tự, chuyển đổi int thành String
        edtchucvuDialog.setText(employee.getChucvu());

        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String ma = edtmaDialog.getText().toString();
                String ten = edttenDialog.getText().toString();
                String luong = edtluongDialog.getText().toString();
                String chucvu = edtchucvuDialog.getText().toString();

                // Kiểm tra nếu mã nhân viên có ít hơn 3 ký tự thì không lưu
                if (ma.length() < 3) {
                    Toast.makeText(ListActivity.this, "Mã nhân viên phải có ít nhất 3 ký tự", Toast.LENGTH_SHORT).show();
                } else {
                    updateEmployee(employee.getManv(), ma, ten, luong, chucvu);
                    loadDB();
                }
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void deleteEmployee(Employee employee) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
        builder.setTitle("Xoá Nhân viên");
        builder.setMessage("Bạn có chắc chắn muốn xoá Nhân viên này?");

        builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteFromDatabase(employee.getManv());
                loadDB();
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void updateEmployee(String id, String ma, String ten, String luong, String chucvu) {
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.nguyenhoanghai_20115801/files/hai.db", null, SQLiteDatabase.OPEN_READWRITE);
            ContentValues values = new ContentValues();
            values.put("maso", ma);
            values.put("ten", ten);
            values.put("luong", luong);
            values.put("chucvu", chucvu);
            db.update("hai", values, "maso=?", new String[]{id});
            db.close();
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteFromDatabase(String id) {
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.nguyenhoanghai_20115801/files/hai.db", null, SQLiteDatabase.OPEN_READWRITE);
            db.delete("hai", "maso=?", new String[]{id});
            db.close();
            adapter.notifyDataSetChanged();
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
        } else if (id == R.id.menuAdd2) {
            addEmployee();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
