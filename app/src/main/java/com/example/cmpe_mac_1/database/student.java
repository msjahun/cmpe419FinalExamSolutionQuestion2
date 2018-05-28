package com.example.cmpe_mac_1.database;

import java.io.Serializable;

/**
 * Created by cmpe_mac_1 on 08/05/18.
 */

public class student implements Serializable {
    private String studentNumber;
    private String studentName;
    private String studentSurname;

    public student(String studentNumber, String studentName, String studentSurname) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }
}
