package com.example.hai_20115801;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Place> places;
    PlaceAdapter adapter;

    EditText edtid, name, coutry;
    Button add, update, delete, viewplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Hai 20115801");

        edtid = findViewById(R.id.edtId);
        name = findViewById(R.id.edtName);
        coutry = findViewById(R.id.edtCountry);
        add = findViewById(R.id.btnAdd);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        viewplace = findViewById(R.id.btnView);
        listView = findViewById(R.id.listView);


        places = new ArrayList<>();
        adapter = new PlaceAdapter(this, places);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = edtid.getText().toString();
                String Name = name.getText().toString();
                String country = coutry.getText().toString();

                if (TextUtils.isEmpty(idString)) {
                    Toast.makeText(MainActivity.this, "Mã địa danh không được trống!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(Name)) {
                    Toast.makeText(MainActivity.this, "Tên địa danh không được trống!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(country)) {
                    Toast.makeText(MainActivity.this, "Tên Nước không được trống!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int id;
                try {
                    id = Integer.parseInt(idString);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Mã địa danh không hợp lệ (phải là số)!", Toast.LENGTH_SHORT).show();
                    return; // Exit the method if ID conversion fails
                }

                Place p = new Place(id, Name, country);
                places.add(p);
                adapter.notifyDataSetChanged();

                edtid.setText("");
                name.setText("");
                coutry.setText("");
                edtid.forceLayout();

                Toast.makeText(MainActivity.this, "Thêm địa danh thành công.", Toast.LENGTH_SHORT).show();
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Id = Integer.parseInt(edtid.getText().toString());
                String Name = name.getText().toString();
                String Country = coutry.getText().toString();

                Place p = findLandmarkById(Id);

                p.setName(Name);
                p.setCountry(Country);

                adapter.notifyDataSetChanged();

                edtid.setText("");
                name.setText("");
                coutry.setText("");
                edtid.findFocus();

                Toast.makeText(MainActivity.this, "Cập nhật thành công.", Toast.LENGTH_SHORT).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Id = Integer.parseInt(edtid.getText().toString());

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xoá địa danh");
                builder.setMessage("Xoá địa danh có ID: " + Id + " ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Place placeToDelete = findLandmarkById(Id);
                        if (placeToDelete != null) {
                            places.remove(placeToDelete);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Xoá thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Không tìm thấy địa danh để xoá!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                edtid.setText("");
                name.setText("");
                coutry.setText("");
                edtid.forceLayout();
            }
        });


        viewplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(adapter);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Place selectedPlace = places.get(position);
                edtid.setText(String.valueOf(selectedPlace.getId()));
                name.setText(selectedPlace.getName());
                coutry.setText(selectedPlace.getCountry());
            }
        });
    }
    private Place findLandmarkById(int id) {
        for (Place p : places) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}