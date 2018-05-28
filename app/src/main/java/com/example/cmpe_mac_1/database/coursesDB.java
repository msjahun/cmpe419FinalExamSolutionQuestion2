package com.example.cmpe_mac_1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cmpe_mac_1 on 08/05/18.
 */

public class coursesDB extends SQLiteOpenHelper {
    private static  final String _db="Courses_db";
    private static final int version=1;

    public coursesDB(Context context) {
        super(context, _db, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE CMPE419(studentNumber TEXT, studentName TEXT, studentSurname TEXT );");
        db.execSQL("CREATE TABLE CMPE416(studentNumber TEXT, studentName TEXT, studentSurname TEXT );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST CMPE419");
        db.execSQL("DROP TABLE IF EXIST CMPE416");

        onCreate(db);

    }
}
