// MainActivity.java
package com.example.stenotes;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> listViewItems = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.mainListView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listViewItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedNote = (String) parent.getItemAtPosition(position);
                openNoteActivity(selectedNote);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNoteActivity("");
            }
        });
    }

    private void openNoteActivity(String selectedNote) {
        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
        intent.putExtra("selectedNote", selectedNote);
        startActivityForResult(intent, NoteActivity.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NoteActivity.REQUEST_CODE && resultCode == RESULT_OK) {
            String updatedNote = data.getStringExtra("updatedNote");
            if (listViewItems.contains(updatedNote)) {
                int position = listViewItems.indexOf(updatedNote);
                listViewItems.set(position, updatedNote);
            } else {
                listViewItems.add(updatedNote);
            }
            adapter.notifyDataSetChanged();
        }
    }
}
