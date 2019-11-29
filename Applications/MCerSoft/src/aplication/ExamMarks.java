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

    public ResultSet showIDCer(String cerId) throws SQLException {
        //cn.getConnect();
        String sql = "SELECT CERTIFICATE_ID, CERTIFICATE_NAME, CERTIFICATE_NUM FROM CERTIFICATED"
                + " WHERE CERTIFICATE_ID = '" + cerId + "'";
        return DBconnect.loadData(sql);
    }

    public ResultSet showIDStu(String stuId) throws SQLException {
        //cn.getConnect();
        String sql = "SELECT STUDENT_ID, STUDENT_NAME, IDENTITY_CARD, GENDER, "
                + "DOB, PHONE, EMAIL, "
                + "STUDENT_ADD FROM STUDENTS WHERE STUDENT_ID ='" + stuId + "'";
        return DBconnect.loadData(sql);
    }
    public ResultSet showIDStuReg(String Id) throws SQLException {
        //cn.getConnect();
        String sql = "SELECT B.STUDENT_ID, B.STUDENT_NAME, C.CERTIFICATE_ID, C.CERTIFICATE_NAME, A.EXAM_DATE, A.ID_NUM "
                + "FROM STUDENT_CER A, STUDENTS B, CERTIFICATED C "
                + "WHERE B.STUDENT_ID = A.STUDENT_ID AND A.CERTIFICATE_ID = C.CERTIFICATE_ID AND B.STUDENT_ID  = '" + Id + "'";
        return DBconnect.loadData(sql);
    }
//Them moi 1 dong du lieu vao table 

    public void insertDataReg(String idStu, String idCer,String dExam, String idNum) throws SQLException {
        DBconnect.getConnect();
        String sql = "INSERT INTO STUDENT_CER "
                + "VALUES('" + idStu + "',N'" + idCer + "',N'" + dExam + "',N'" + idNum + "',N'" + 0 + "',N'" + null +"')";
        DBconnect.updateData(sql);
    }
//Dieu chinh 1 dong du lieu vao table

    public void editDataReg(String idStu, String idCer,String dExam, String idNum) throws SQLException {
        DBconnect.getConnect();
        String sql = "UPDATE STUDENT_CER SET "
                + "STUDENT_ID = '" + idStu + "',"
                + "CERTIFICATE_ID = '" + idCer + "',"
                + "EXAM_DATE = '" + dExam + "',"
                + "AVG_MARK = '" + 0 + "',"
                + "RESULT = '" + null + "'"
                + "WHERE ID_NUM = '" + idNum + "'";
        DBconnect.updateData(sql);
    }
//Xoa 1 dong du lieu vao table

    public void deleteDataReg(String id) throws SQLException {
        String sql = "DELETE FROM STUDENT_CER WHERE STUDENT_ID ='" + id + "'";
        DBconnect.updateData(sql);
    }
}
