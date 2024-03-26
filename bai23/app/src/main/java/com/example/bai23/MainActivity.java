package com.example.bai23;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText congviec, noidung;
    TextView txtNgay, txtGio;
    Button btnNgay, btnGio, btnThem;
    ListView lst;
    ArrayList<Congviec> listCv;
    CvAdapter adapter;
    String selectedDate, selectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần giao diện
        congviec = findViewById(R.id.edtCv);
        noidung = findViewById(R.id.edNd);
        txtGio = findViewById(R.id.txtTime);
        txtNgay = findViewById(R.id.txtDay);
        btnNgay = findViewById(R.id.btnDate);
        btnGio = findViewById(R.id.btnTime);
        btnThem = findViewById(R.id.btnAdd);
        lst = findViewById(R.id.lst);

        // Khởi tạo danh sách công việc và adapter
        listCv = new ArrayList<>();
        adapter = new CvAdapter(this, R.layout.list_item, listCv);
        lst.setAdapter(adapter);

        // Xử lý sự kiện khi nhấn nút chọn ngày
        btnNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Xử lý sự kiện khi nhấn nút chọn giờ
        btnGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        // Xử lý sự kiện khi nhấn nút thêm công việc
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin công việc và nội dung từ EditText
                String cv = congviec.getText().toString();
                String nd = noidung.getText().toString();
                // Tạo đối tượng công việc mới
                Congviec congViec = new Congviec(cv, nd, selectedDate, selectedTime);
                //Kiem tra truong rong
                if(TextUtils.isEmpty(cv)){
                  Toast.makeText(MainActivity.this, "Cong viec khong duoc de trong!", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(nd)){
                  Toast.makeText(MainActivity.this, "Noi dung khong duoc de trong!", Toast.LENGTH_SHORT).show();
               }
                else {
                    // Thêm công việc vào danh sách
                    listCv.add(congViec);
                    // Cập nhật giao diện
                    adapter.notifyDataSetChanged();
                    // Xóa nội dung đã nhập trong EditText
                    congviec.setText("");
                    noidung.setText("");
                    txtNgay.setText("");
                    txtGio.setText("");
                    congviec.requestFocus();
                }
            }
        });
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo!");
                builder.setMessage("Bạn đã hoàn thành công việc này?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xóa công việc đã hoàn thành khỏi danh sách
                        listCv.remove(position);
                        // Cập nhật lại ListView
                        adapter.notifyDataSetChanged();
                    }
                });
                // Thêm xử lý cho nút "CANCEL"
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Không thực hiện hành động nào khi người dùng nhấn nút "CANCEL"
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    // Hiển thị hộp thoại chọn ngày
    public void showDatePickerDialog(){
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        txtNgay.setText(selectedDate);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }

    // Hiển thị hộp thoại chọn giờ
    public void showTimePickerDialog(){
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedTime = hourOfDay + ":" + minute;
                        txtGio.setText(selectedTime);
                    }
                }, hour, minute, true);

        timePickerDialog.show();
    }

}
