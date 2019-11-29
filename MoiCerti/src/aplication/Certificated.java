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
public class Certificated {

    private String cerID;
    private String cerName;
    private String cerNum;

    public String getCerID() {
        return cerID;
    }

    public void setCerID(String cerID) {
        this.cerID = cerID;
    }

    public String getCerName() {
        return cerName;
    }

    public void setCerName(String cerName) {
        this.cerName = cerName;
    }

    public String getCerNum() {
        return cerNum;
    }

    public void setCerNum(String cerNum) {
        this.cerNum = cerNum;
    }
        //Truy van tat ca du lieu trong Table
    public ResultSet showCertificate() throws SQLException {
       //cn.getConnect();
        String sql = "SELECT CERTIFICATE_ID, CERTIFICATE_NAME, CERTIFICATE_NUM FROM CERTIFICATED ";
        return DBconnect.loadData(sql);
    }
//Truy van cac dong du lieu trong Table theo id

    public ResultSet showIDCer(String cerId) throws SQLException {
        //cn.getConnect();
        String sql = "SELECT CERTIFICATE_ID, CERTIFICATE_NAME, CERTIFICATE_NUM FROM CERTIFICATED"
                + " WHERE CERTIFICATE_ID = '" + cerId + "'";
        return DBconnect.loadData(sql);
    }
//Them moi 1 dong du lieu vao table 

    public void insertData(String id, String name, String num) throws SQLException {
        DBconnect.getConnect();
        String sql = "INSERT INTO CERTIFICATED "
                + "VALUES('" + id + "',N'" + name + "',N'" + num + "')";
        DBconnect.updateData(sql);
    }
//Dieu chinh 1 dong du lieu vao table

    public void editData(String id, String name, String num) throws SQLException {
        DBconnect.getConnect();
        String sql = "UPDATE CERTIFICATED SET "+
                "CERTIFICATE_NAME = '" + name + "',"+
                "CERTIFICATE_NUM = '" + num + "'"+
                "WHERE CERTIFICATE_ID = '" + id + "'";
        DBconnect.updateData(sql);
    }
//Xoa 1 dong du lieu vao table

    public void deleteData(String id) throws SQLException {
        String sql = "DELETE FROM CERTIFICATED WHERE CERTIFICATE_ID ='" + id + "'";
        DBconnect.updateData(sql);
    }
}
