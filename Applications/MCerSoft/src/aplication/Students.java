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
public class Students {

    private String stID;
    private String stName;
    private String stIDCard;
    private String stSex;
    private String stDOB;
    private String stPhone;
    private String stEmail;
    private String stAddress;

    public String getStID() {
        return stID;
    }

    public void setStID(String stID) {
        this.stID = stID;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStIDCard() {
        return stIDCard;
    }

    public void setStIDCard(String stIDCard) {
        this.stIDCard = stIDCard;
    }

    public String getStSex() {
        return stSex;
    }

    public void setStSex(String stSex) {
        this.stSex = stSex;
    }

    public String getStDOB() {
        return stDOB;
    }

    public void setStDOB(String stDOB) {
        this.stDOB = stDOB;
    }

    public String getStPhone() {
        return stPhone;
    }

    public void setStPhone(String stPhone) {
        this.stPhone = stPhone;
    }

    public String getStEmail() {
        return stEmail;
    }

    public void setStEmail(String stEmail) {
        this.stEmail = stEmail;
    }

    public String getStAddress() {
        return stAddress;
    }

    public void setStAddress(String stAddress) {
        this.stAddress = stAddress;
    }
        //Truy van tat ca du lieu trong Table
    public ResultSet showStudent() throws SQLException {
       DBconnect.getConnect();
        String sql = "SELECT STUDENT_ID, STUDENT_NAME, IDENTITY_CARD, GENDER, "
                + "DOB, PHONE, EMAIL, STUDENT_ADD FROM STUDENTS";
        //1 ORDER BY USERS_ID ASC
        return DBconnect.loadData(sql);
    }
//Truy van cac dong du lieu trong Table id

    public static ResultSet showIDStudent(String stuId) throws SQLException {
        DBconnect.getConnect();
        String sql = "SELECT STUDENT_ID, STUDENT_NAME, IDENTITY_CARD, GENDER, "
                + "DOB, PHONE, EMAIL, "
                + "STUDENT_ADD FROM STUDENTS WHERE STUDENT_ID ='" + stuId + "'";
        return DBconnect.loadData(sql);
    }
//Theo moi 1 dong du lieu vao table

    public void insertData(String id, String fullname, String idCard, String male, String dob,
             String phone, String mail, String add) throws SQLException {
        DBconnect.getConnect();
        String sql = "INSERT INTO STUDENTS "
                + "VALUES('" + id + "',N'" + fullname + "',N'" + idCard + "',N'" 
                + male + "',N'" + dob + "',N'" + phone + "',N'" + mail + "',N'" + add + "')";
        DBconnect.updateData(sql);
    }
//Dieu chinh 1 dong du lieu vao table

    public void editData(String id, String fullname, String idCard, String male, String dob,
            String phone, String mail, String add) throws SQLException {
        DBconnect.getConnect();
        String sql = "UPDATE STUDENTS SET "+
                "STUDENT_NAME = '" + fullname + "',"+
                "IDENTITY_CARD = '" + idCard + "',"+
                "GENDER = '" + male + "',"+
                "DOB = '" + dob + "',"+
                "PHONE = '" + phone + "',"+
                "EMAIL = '" + mail + "',"+
                "STUDENT_ADD = '" + add + "'"+
                "WHERE STUDENT_ID = '" + id + "'";
        DBconnect.updateData(sql);
    }
//Xoa 1 dong du lieu vao table

    public void deleteData(String id) throws SQLException {
        String sql = "DELETE FROM STUDENTS "
                + "WHERE STUDENT_ID = '" + id + "'";
        DBconnect.updateData(sql);
    }
}
