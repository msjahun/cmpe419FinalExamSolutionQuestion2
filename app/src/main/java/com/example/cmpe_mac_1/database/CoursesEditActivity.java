package com.example.cmpe_mac_1.database;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CoursesEditActivity extends AppCompatActivity {
        TextView textViewCourseName;
        EditText editTextCourseNumber;
        EditText editTextCourseGrade;
        Button buttonAdd;
        Button buttonListAll;
    String dbTableName;

    ArrayList<student> studentsList= new ArrayList<>();


    private coursesDB v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_edit);
        Bundle bundle = getIntent().getExtras();
     dbTableName = (String)bundle.getString("dbTableName");


        textViewCourseName = findViewById(R.id.textViewCourseName);
        editTextCourseNumber = findViewById(R.id.editTextNumber);
        editTextCourseGrade = findViewById(R.id.editTextGrade);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonListAll = findViewById(R.id.buttonListAll);
        v1=new coursesDB(this);



        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            addRecord(editTextCourseNumber.getText().toString(), editTextCourseGrade.getText().toString());

            }
        });

        buttonListAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getAllRecord();
                Intent intent = new Intent(CoursesEditActivity.this, CoursesListActivity.class);
                intent.putExtra("CoursesArray",studentsList );
                startActivity(intent);


            }
        });



    }

    private void addRecord(String number, String grade) {

        if(validateNumberinput(number) && validateGradeInput(grade) ){

            SQLiteDatabase db =v1.getWritableDatabase();
            db.execSQL("INSERT INTO "+dbTableName +" VALUES('"+number+"','"+grade+"');");
        }

        clear();

    }

    private void getAllRecord() {


        SQLiteDatabase db=v1.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM "+dbTableName +"  ",null);


          studentsList.add( new student("number","grade"));
        studentsList.add( new student("number1","grade1"));


        //clearText();

        clear();

    }

    private boolean validateNumberinput(String s){
        if (s.trim().length()==0){
            Toast.makeText(this,"Please enter Valid Number No!!!",Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;

    }


    private boolean validateGradeInput(String s){
        if (s.trim().length()==0){
            Toast.makeText(this,"Please enter Valid Grade!!!",Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;

    }


        public void clear(){
        editTextCourseGrade.setText("");
        editTextCourseNumber.setText("");
        }



}
