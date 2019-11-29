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
public class ExamMarks {
    private String idStu;
    private String stuName;
    private String idSub;
    private String subName;
    private float mark;

    public String getIdStu() {
        return idStu;
    }

    public void setIdStu(String idStu) {
        this.idStu = idStu;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getIdSub() {
        return idSub;
    }

    public void setIdSub(String idSub) {
        this.idSub = idSub;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }
    //Truy van tat ca du lieu trong Table
    public ResultSet showExam() throws SQLException {
        //cn.getConnect();
        String sql = "SELECT E.SUBJECT_ID, D.SUBJECT_NAME, E.ID_NUM, S.STUDENT_NAME, E.MARK \n" +
"FROM EXAM_MARK E, STUDENTS S, DETAIL_CERTIFICATE D, STUDENT_CER C\n" +
"WHERE S.STUDENT_ID = C.STUDENT_ID AND C.ID_NUM = E.ID_NUM AND D.SUBJECT_ID = E.SUBJECT_ID";
        return DBconnect.loadData(sql);
    }
//Truy van cac dong du lieu trong Table theo id

    public ResultSet showIDExam(String idSub, String id) throws SQLException {
        //cn.getConnect();
        String sql = "SELECT E.SUBJECT_ID, D.SUBJECT_NAME, E.ID_NUM, S.STUDENT_NAME, E.MARK \n" +
                    "FROM EXAM_MARK E, STUDENTS S, DETAIL_CERTIFICATE D, STUDENT_CER C\n" +
                "WHERE S.STUDENT_ID = C.STUDENT_ID AND C.ID_NUM = E.ID_NUM AND D.SUBJECT_ID = E.SUBJECT_ID "
                + "AND E.SUBJECT_ID  = '" + idSub + "' AND E.ID_NUM = '"+ id +"'";
        return DBconnect.loadData(sql);
    }
//Them moi 1 dong du lieu vao table 

    public void insertDataExam(String idSub, String idNum, float mExam) throws SQLException {
        DBconnect.getConnect();
        String sql = "INSERT INTO EXAM_MARK "
                + "VALUES('" + idSub + "',N'" + idNum + "',N'" + mExam + "')";
        DBconnect.updateData(sql);
    }
//Dieu chinh 1 dong du lieu vao table

    public void editDataExam(String idSub, String idNum, float mExam) throws SQLException {
        DBconnect.getConnect();
        String sql = "UPDATE EXAM_MARK SET "
                + "SUBJECT_ID = '" + idSub + "',"
                + "ID_NUM = '" + idNum + "',"
                + "MARK = '" + mExam + "'"            
                + "WHERE SUBJECT_ID ='" + idSub + "' AND ID_NUM = '" + idNum + "'";
        DBconnect.updateData(sql);
    }
//Xoa 1 dong du lieu vao table

    public void deleteDataExam(String id, String idNum) throws SQLException {
        String sql = "DELETE FROM EXAM_MARK WHERE SUBJECT_ID ='" + id + "' AND ID_NUM = '"+ idNum +"'";
        DBconnect.updateData(sql);
    }
}
