package com.example.bai15;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button tinhtien, tiep, thongke;
    ImageButton exit;
    EditText user, slsach, tongkh, khvip, tongtien;
    CheckBox vip;
    TextView thanhtien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tinhtien = findViewById(R.id.btnTinhtien);
        tiep = findViewById(R.id.btnTiep);
        thongke = findViewById(R.id.btnThongke);
        exit = findViewById(R.id.btnImage);
        user = findViewById(R.id.edtUser);
        slsach = findViewById(R.id.edtSLSach);
        tongkh = findViewById(R.id.edtTongKH);
        khvip = findViewById(R.id.edtTongKHVIP);
        tongtien = findViewById(R.id.edtTongDoanhThu);
        vip = findViewById(R.id.cbVIP);
        thanhtien = findViewById(R.id.txtThanhtien);

        tongkh.setEnabled(false);
        khvip.setEnabled(false);
        tongtien.setEnabled(false);

        ArrayList<KhachHang> danhsach = new ArrayList<>();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog("Thông báo!", "Bạn có muốn thoát!");
            }
        });

        tinhtien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int giasach = 20000, sl;
                int sotien;
                String ten = user.getText().toString();
                int soLuongSach = Integer.parseInt(slsach.getText().toString());
                boolean khachVip = vip.isChecked();

                if(vip.isChecked()){
                    sotien = (int) (giasach * soLuongSach * 0.9);
                }
                else {
                    sotien = giasach * soLuongSach;
                }
                thanhtien.setText(String.valueOf(sotien));
                danhsach.add(new KhachHang(ten, soLuongSach, khachVip));
            }
        });

        tiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setText("");
                slsach.setText("");
                thanhtien.setText("");
                vip.setChecked(false);
                user.requestFocus();
            }
        });

        thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tongSoKhachHang = danhsach.size();
                int tongSoKhachHangVIP = 0;
                int tongTien = 0;

                for (KhachHang khachHang : danhsach) {
                    if (khachHang.isLaKhachVIP()) {
                        tongSoKhachHangVIP++;
                    }
                    tongTien += (khachHang.isLaKhachVIP() ? khachHang.getSoLuongSach() * 20000 * 0.9 : khachHang.getSoLuongSach() * 20000);
                }

                tongkh.setText(String.valueOf(tongSoKhachHang));
                khvip.setText(String.valueOf(tongSoKhachHangVIP));
                tongtien.setText(String.valueOf(tongTien));
            }
        });

    }

    private void showAlertDialog(String title, String message) {
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

    }