package com.example.bai17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
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

        ArrayList<String> menucontext = new ArrayList<>();
        menucontext.add("Sửa");
        menucontext.add("Xoá");
        ArrayAdapter<String> menuadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, menucontext);
        ListView listView = findViewById(R.id.lsView);
        listView.setAdapter(menuadapter);
        registerForContextMenu(listView);

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

        adapter = new NameAdapter(R.layout.name_layout, MainActivity.this, data);
        lst.setAdapter(adapter);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Lấy thông tin về mục được chọn trong ListView
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        switch (item.getItemId()) {
            case R.id.:
                Toast.makeText(this, "Item 1 selected at position: " + position, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.clear:
                Toast.makeText(this, "Item 2 selected at position: " + position, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}