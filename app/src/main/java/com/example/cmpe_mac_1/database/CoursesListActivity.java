package com.example.cmpe_mac_1.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CoursesListActivity extends AppCompatActivity {
    ListView listall;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);

        Bundle bundle = getIntent().getExtras();
        ArrayList<String> array = (ArrayList<String>)bundle.getStringArrayList("CoursesArray");


        listall=(ListView)findViewById(R.id.listView);

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        listall.setAdapter(adapter);


    }
}
