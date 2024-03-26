package com.example.bai18;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> items = new ArrayList<>();
        items.add("intel");
        items.add("samsung");
        items.add("nokia");
        items.add("amd");
        items.add("kic");
        items.add("edc");

        CustomAdapter adapter = new CustomAdapter(this, items);
        setListAdapter(adapter);

        txt = findViewById(R.id.txtValue);

        ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txt.setText("Vị trí: " + position + ", Giá trị: " + items.get(position));
            }
        });
    }
}
