package com.example.kt_cau2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<Employee> employees;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        databaseHelper = new DatabaseHelper(this);
        employees = databaseHelper.getAllEmployees();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getEmployeeNames());
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Employee employee = employees.get(position);
                showEmployeeDetails(employee);
                return true;
            }

        });
    }

    private List<String> getEmployeeNames() {
        List<String> employeeNames = new ArrayList<>();
        for (Employee employee : employees) {
            employeeNames.add(employee.getName());
        }
        return employeeNames;
    }

    private void showEmployeeDetails(final Employee employee) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_layout, null);

        TextView txtMaNv = dialogView.findViewById(R.id.txtMaNv);
        TextView txtTenNv = dialogView.findViewById(R.id.txtTenNv);
        TextView txtTuoiNv = dialogView.findViewById(R.id.txtTuoiNv);
        EditText edtMaNv = dialogView.findViewById(R.id.edtMaNv);
        EditText edtTenNv = dialogView.findViewById(R.id.edtTenNv);
        EditText edtTuoiNv = dialogView.findViewById(R.id.edtTuoiNv);

        edtMaNv.setText(employee.getId());
        edtTenNv.setText(employee.getName());
        edtTuoiNv.setText(String.valueOf(employee.getAge()));

        builder.setView(dialogView);
        builder.setPositiveButton("Xoá nhân viên", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteEmployee(employee.getId());
            }
        });

        builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }


    private void deleteEmployee(String employeeId) {
        databaseHelper.deleteEmployee(employeeId);
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(employeeId)) {
                employees.remove(i);
                break;
            }
        }
        adapter.notifyDataSetChanged();
    }
}