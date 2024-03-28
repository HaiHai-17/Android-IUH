package com.example.bai17;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lst;
    private ArrayList<Name> data;
    private NameAdapter adapter;
    Button nhap;
    EditText edtten;
    TextView giatri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lst = findViewById(R.id.lsView);
        nhap = findViewById(R.id.btnNhap);
        edtten = findViewById(R.id.edtName);
        giatri = findViewById(R.id.txtValue);

        data = new ArrayList<Name>();
        data.add(new Name("Teo"));
        data.add(new Name("Ty"));

        adapter = new NameAdapter(R.layout.name_layout, MainActivity.this, data);
        lst.setAdapter(adapter);
        registerForContextMenu(lst); // Đăng ký Context Menu với ListView thay vì sử dụng ArrayAdapter

        nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtten.getText().toString();
                data.add(new Name(ten));
                adapter.notifyDataSetChanged();
                edtten.setText("");
                edtten.forceLayout();
            }
        });

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                giatri.setText("Vi trí: " + position + " Giá trị: " + data.get(position).getTen());
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        switch (item.getItemId()) {
            case R.id.edit_menu:
                // Thực hiện chức năng chỉnh sửa tên tại vị trí được chọn
                editName(position);
                return true;
            case R.id.clear_menu:
                // Thực hiện chức năng xoá tên tại vị trí được chọn
                deleteName(position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void editName(int position) {
        // Lấy ra tên tại vị trí được chọn
        Name nameToEdit = data.get(position);

        // Tạo AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chỉnh sửa tên");

        // Thiết lập layout cho dialog
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_edit_name, null);
        // Tìm EditText trong layout của dialog
        final EditText input = viewInflated.findViewById(R.id.editTextName);
        input.setText(nameToEdit.getTen()); // Đặt giá trị hiện tại của tên vào EditText
        builder.setView(viewInflated);

        // Thiết lập nút "OK" để xác nhận chỉnh sửa
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lấy tên mới từ EditText
                String newName = input.getText().toString();
                // Cập nhật tên mới vào danh sách
                data.get(position).setTen(newName);
                // Cập nhật giao diện
                adapter.notifyDataSetChanged();
                // Đóng dialog
                dialog.dismiss();
            }
        });

        // Thiết lập nút "Cancel" để huỷ bỏ chỉnh sửa
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Đóng dialog
                dialog.cancel();
            }
        });

        // Hiển thị dialog
        builder.show();
    }


    private void deleteName(int position) {
        // Xoá tên tại vị trí được chọn từ danh sách
        data.remove(position);
        // Cập nhật giao diện
        adapter.notifyDataSetChanged();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            finish();
            return true;
        }
        // Xử lý các mục menu khác nếu cần
        return super.onOptionsItemSelected(item);
    }

}