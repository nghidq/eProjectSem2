/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication;
import java.sql.Connection;
import database.DBconnect;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author David Nghi
 */
public class Login {

    String perName, admin;
    int userID, perID;

    public static String username;
    public static String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPerID() {
        return perID;
    }

    public void setPerID(int perID) {
        this.perID = perID;
    }

    public ResultSet showLogin() throws SQLException {
        DBconnect.getConnect();
        String sql = "SELECT USERS_ID, USERNAME, ENCRYPTBYPASSPHRASE('12',PASSWORDS) AS PASSWORDS FROM USERS";
        return DBconnect.loadData(sql);
    }
//Truy van cac dong du lieu trong Table theo admin

    public ResultSet showAdmin(String users, String pass) throws SQLException {
        String sql = "SELECT USERNAME, ENCRYPTBYPASSPHRASE('12',PASSWORDS) AS PASSWORDS FROM USERS WHERE ROLES = 'ADMIN' AND"
                + " USERNAME='" + users + "' AND PASSWORDS='" + pass + "'";
        return DBconnect.loadData(sql);
    }
//Truy van cac dong du lieu trong Table theo other user
    public ResultSet showKeeper(String users, String pass) throws SQLException {
        String sql = "SELECT USERNAME, ENCRYPTBYPASSPHRASE('12',PASSWORDS) AS PASSWORDS FROM USERS WHERE ROLES NOT IN "
                + "(SELECT ROLES FROM USERS WHERE ROLES = 'ADMIN') AND"
                + " USERNAME='" + users + "' AND PASSWORDS='" + pass + "'";
        return DBconnect.loadData(sql);
    }
   public void editDataChange(String username, String password, String newPass) throws SQLException {
        DBconnect.getConnect();
        String sql = "UPDATE USERS SET "+               
                "PASSWORDS = '" + newPass + "'"+               
                "WHERE USERNAME = '" + username + "' and PASSWORDS = '" + password + "'";
        DBconnect.updateData(sql);
    }
}
