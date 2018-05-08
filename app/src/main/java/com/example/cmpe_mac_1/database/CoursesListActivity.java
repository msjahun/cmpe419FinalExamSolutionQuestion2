package com.example.cmpe_mac_1.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CoursesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);

        Bundle bundle = getIntent().getExtras();
        String dbTableName = (String)bundle.getString("dbTableName");



    }
}
