package com.example.cmpe_mac_1.database;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CoursesEditActivity extends AppCompatActivity {

Spinner spinnerCourses;
    EditText editTextStudentNumber;
    EditText editTextStudentName;
    EditText editTextStudentSurname;
        Button buttonAdd;
        Button buttonListAll;
    String dbTableName="CMPE419";
String courseList [] ={"CMPE419","CMPE416"};
    ArrayAdapter<String> adapter;

    ArrayList<student> studentsList= new ArrayList<>();
    ArrayList<String> passingList = new ArrayList<>();


    private coursesDB v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_edit);
      // Bundle bundle = getIntent().getExtras();
    // dbTableName = (String)bundle.getString("dbTableName");
        dbTableName="CMPE419";

        spinnerCourses = findViewById(R.id.spinnerCourseList);

        editTextStudentName = findViewById(R.id.editTextStudentName);
        editTextStudentNumber = findViewById(R.id.editTextStudentNumber);
        editTextStudentSurname = findViewById(R.id.editTextStudentSurname);
        buttonAdd = findViewById(R.id.buttonAddToDB);
        buttonListAll = findViewById(R.id.buttonList);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,courseList);
        spinnerCourses.setAdapter(adapter);
        v1=new coursesDB(this);


        /*spinnerCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CoursesEditActivity.this, courseList[position]+" has been clicked", Toast.LENGTH_SHORT).show();
                dbTableName = courseList[position];
            }
        });*/

        dbTableName = courseList[spinnerCourses.getSelectedItemPosition()];
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            addRecord(editTextStudentNumber.getText().toString(), editTextStudentName.getText().toString(), editTextStudentSurname.getText().toString());

            }
        });

        buttonListAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbTableName = courseList[spinnerCourses.getSelectedItemPosition()];
                getAllRecord();
                passingList= new ArrayList<>();
                for(student stu : studentsList){
                  passingList.add(stu.getStudentNumber()+" "+stu.getStudentName()+" "+stu.getStudentSurname());
                }
                Intent intent = new Intent(CoursesEditActivity.this, CoursesListActivity.class);
                intent.putExtra("CoursesArray",passingList );
                startActivity(intent);


            }
        });



    }

    private void addRecord(String studentNumber, String studentName, String studentSurname) {
//This is ready
        dbTableName = courseList[spinnerCourses.getSelectedItemPosition()].toString();
        Toast.makeText(this, dbTableName, Toast.LENGTH_SHORT).show();
        if(validateInput(studentNumber, "Student Number") && validateInput(studentName, "Student Name") && validateInput(studentSurname, "Student Surname") ){

            SQLiteDatabase db =v1.getWritableDatabase();
            db.execSQL("INSERT INTO "+dbTableName +" VALUES('"+studentNumber+"','"+studentName+"','"+studentSurname+"');");
        }

        clear();

    }

    private void getAllRecord() {
        dbTableName = courseList[spinnerCourses.getSelectedItemPosition()];
//This needs a bit of work
        SQLiteDatabase db=v1.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM "+dbTableName +"  ",null);
studentsList= new ArrayList<>();
        while(c.moveToNext()){


            studentsList.add( new student(c.getString(0),c.getString(1), c.getString(2)));
        }

          //studentsList.add( new student("22323","musa jahun", "Suleiman"));
        //  studentsList.add( new student("22323d"," jahun", "mm Suleiman"));



        //clearText();

        clear();

    }

    private boolean validateInput(String s,String SS){
        //Ready
        if (s.trim().length()==0){
            Toast.makeText(this,"Please enter Valid"+SS+"!!!",Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;

    }



        public void clear(){
        //Ready
        editTextStudentSurname.setText("");
        editTextStudentNumber.setText("");
        editTextStudentName.setText("");
        }



}
