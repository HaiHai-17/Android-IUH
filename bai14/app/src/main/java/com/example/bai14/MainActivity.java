package com.example.bai14;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtHoTen, edtCMND, edtBoSung;
    private RadioButton rbtnTrungCap, rbtnCaodang, rbtnDaiHoc;
    private CheckBox cbDocBao, cbDocSach, cbDocCode;
    private Button btnGui;
    private TextView txtThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các widget theo id
        edtHoTen = findViewById(R.id.edtHovaten);
        edtCMND = findViewById(R.id.edtCMND);
        edtBoSung = findViewById(R.id.edtBosung);
        rbtnTrungCap = findViewById(R.id.rbtnTrungcap);
        rbtnCaodang = findViewById(R.id.rbtnCaodang);
        rbtnDaiHoc = findViewById(R.id.rbtnDaihoc);
        cbDocBao = findViewById(R.id.cbDocbao);
        cbDocSach = findViewById(R.id.cbDocsach);
        cbDocCode = findViewById(R.id.cbDoccode);
        btnGui = findViewById(R.id.btnGui);

        // Xử lý sự kiện click nút Gửi
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kiểm tra tính hợp lệ của dữ liệu nhập vào
                if (validateInput()) {
                    // Thu thập dữ liệu từ các widget
                    String name = edtHoTen.getText().toString().trim();
                    String cmnd = edtCMND.getText().toString().trim();
                    String degree = "";
                    if (rbtnTrungCap.isChecked()) {
                        degree = "Trung cấp";
                    } else if (rbtnCaodang.isChecked()) {
                        degree = "Cao đẳng";
                    } else {
                        degree = "Đại học";
                    }
                    String hobbies = "";
                    if (cbDocBao.isChecked()) {
                        hobbies += "Đọc báo";
                    }
                    if (cbDocSach.isChecked()) {
                        hobbies += ", Đọc sách";
                    }
                    if (cbDocCode.isChecked()) {
                        hobbies += ", Đọc code";
                    }

                    // Hiển thị thông tin cá nhân trong AlertDialog
                    showAlertDialog(name, cmnd, degree, hobbies);
                }
            }
        });
    }

    private boolean validateInput() {
        // Kiểm tra tên
        String name = edtHoTen.getText().toString().trim();
        if (TextUtils.isEmpty(name) || name.length() < 3) {
            edtHoTen.setError("Tên không được để trống và phải có ít nhất 3 ký tự");
            return false;
        }

        // Kiểm tra CMND
        String cmnd = edtCMND.getText().toString().trim();
        if (!cmnd.matches("[0-9]{9}")) {
            edtCMND.setError("CMND chỉ được nhập kiểu số và phải có đúng 9 chữ số");
            return false;
        }

        // Kiểm tra sở thích
        if (!cbDocBao.isChecked() && !cbDocSach.isChecked() && !cbDocCode.isChecked()) {
            showAlertDialog("Lỗi", "Phải chọn ít nhất 1 sở thích");
            return false;
        }

        return true;
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi nút "OK" được nhấn
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAlertDialog(String name, String cmnd, String degree, String hobbies) {
        String info = "Họ và tên: " + name + "\n"
                + "CMND: " + cmnd + "\n"
                + "Trình độ: " + degree + "\n"
                + "Sở thích: " + hobbies + "\n"
                + "Thông tin bổ sung: " + edtBoSung.getText().toString().trim();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setMessage(info);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi nút "OK" được nhấn
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
