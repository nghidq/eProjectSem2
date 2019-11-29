/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication;

import database.DBconnect;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author David Nghi
 */
public class Courses {
    private String examDate;
    private String courseName;

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    //Truy van tat ca du lieu trong Table
    public ResultSet showCourse() throws SQLException {
       //cn.getConnect();
        String sql = "SELECT EXAM_DATE, COURSE_NAME FROM COURSES ";
        return DBconnect.loadData(sql);
    }
//Truy van cac dong du lieu trong Table theo id

    public ResultSet showIDCer(String examD) throws SQLException {
        //cn.getConnect();
        String sql = "SELECT EXAM_DATE, COURSE_NAME FROM COURSES"
                + " WHERE EXAM_DATE = '" + examD + "'";
        return DBconnect.loadData(sql);
    }
//Them moi 1 dong du lieu vao table 

    public void insertData(String id, String name) throws SQLException {
        DBconnect.getConnect();
        String sql = "INSERT INTO COURSES "
                + "VALUES('" + id + "',N'" + name + "')";
        DBconnect.updateData(sql);
    }
//Dieu chinh 1 dong du lieu vao table

    public void editData(String idN, String name, String idO) throws SQLException {
        DBconnect.getConnect();
        String sql = "UPDATE COURSES SET "+
                "EXAM_DATE = '" + idN + "',"+
                "COURSE_NAME = '" + name + "'"+
                "WHERE EXAM_DATE = '" + idO + "'";
        DBconnect.updateData(sql);
    }
//Xoa 1 dong du lieu vao table

    public void deleteData(String id) throws SQLException {
        String sql = "DELETE FROM COURSES WHERE EXAM_DATE ='" + id + "'";
        DBconnect.updateData(sql);
    }
    
}
