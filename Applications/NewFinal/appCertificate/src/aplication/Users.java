/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication;

import database.DBconnect;
import java.util.Date;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author David Nghi
 * @date 25/10/2019
 */
public class Users {
    private String uID;
    private String uName;
    private String uPWord;
    private String uRole;
    private String uFullname;
    private String uSex;
    private String uDOB;
    private String uPhone;
    private String uEmail;

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPWord() {
        return uPWord;
    }

    public void setuPWord(String uPWord) {
        this.uPWord = uPWord;
    }

    public String getuRole() {
        return uRole;
    }

    public void setuRole(String uRole) {
        this.uRole = uRole;
    }

    public String getuFullname() {
        return uFullname;
    }

    public void setuFullname(String uFullname) {
        this.uFullname = uFullname;
    }

    public String getuSex() {
        return uSex;
    }

    public void setuSex(String uSex) {
        this.uSex = uSex;
    }

    public String getuDOB() {
        return uDOB;
    }

    public void setuDOB(String uDOB) {
        this.uDOB = uDOB;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public static DBconnect getCn() {
        return cn;
    }

    public static void setCn(DBconnect cn) {
        Users.cn = cn;
    }
    
    public static DBconnect cn = new DBconnect();

    //Truy van tat ca du lieu trong Table
    public ResultSet showUsers() throws SQLException {
       cn.getConnect();
        String sql = "SELECT USERS_ID, USERNAME, ENCRYPTBYPASSPHRASE('12',PASSWORDS) AS PASSWORDS, ROLES, FULLNAME, GENDER, "
                + "DOB, PHONE, EMAIL, "
                + "USERS_ADD FROM USERS";
        //1 ORDER BY USERS_ID ASC
        return DBconnect.loadData(sql);
    }
//Truy van cac dong du lieu trong Table id

    public ResultSet showIDUSER(String userId) throws SQLException {
        cn.getConnect();
        String sql = "SELECT USERS_ID, USERNAME, ENCRYPTBYPASSPHRASE('12',PASSWORDS) AS PASSWORDS, ROLES, FULLNAME, GENDER, "
                + "DOB, PHONE, EMAIL, "
                + "USERS_ADD FROM USERS WHERE USERS_ID='" + userId + "'";
        return cn.loadData(sql);
    }
//Theo moi 1 dong du lieu vao table

    public void insertData(String id, String username, String password, String fullname,
            String male, String dob, String phone, String mail, String roles, String add) throws SQLException {
        cn.getConnect();
        String sql = "INSERT INTO USERS "
                + "VALUES('" + id + "',N'" + username + "',N'" + password + "',N'" + fullname + "',N'"
                + male + "',N'" + dob + "',N'" + phone + "',N'" + mail + "',N'" + roles + "',N'" + add + "')";
        DBconnect.updateData(sql);
    }
//Dieu chinh 1 dong du lieu vao table

    public void editData(String id, String username, String password, String fullname,
            String male, String dob, String phone, String mail, String roles, String add) throws SQLException {
        cn.getConnect();
        String sql = "UPDATE USERS SET "+
                "USERNAME = '" + username + "',"+
                "PASSWORDS = '" + password + "',"+
                "FULLNAME = '" + fullname + "',"+
                "GENDER = '" + male + "',"+
                "DOB = '" + dob + "',"+
                "PHONE = '" + phone + "',"+
                "EMAIL = '" + mail + "',"+
                "ROLES = '" + roles + "',"+
                "USERS_ADD = '" + add + "'"+
                "WHERE USERS_ID = '" + id + "'";
        DBconnect.updateData(sql);
    }
//Xoa 1 dong du lieu vao table

    public void deleteData(String id) throws SQLException {
        String sql = "DELETE FROM USERS WHERE USERS_ID ='" + id + "'";
        DBconnect.updateData(sql);
    }
}
