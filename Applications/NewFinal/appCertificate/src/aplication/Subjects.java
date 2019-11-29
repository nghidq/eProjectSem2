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
public class Subjects {
    private String subID;
    private String subName;
    private String cerID;

    public String getSubID() {
        return subID;
    }

    public void setSubID(String subID) {
        this.subID = subID;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getCerID() {
        return cerID;
    }

    public void setCerID(String cerID) {
        this.cerID = cerID;
    }
    
            //Truy van tat ca du lieu trong Table
    public ResultSet showSubject() throws SQLException {
       //cn.getConnect();
        String sql = "SELECT A.SUBJECT_ID, A.SUBJECT_NAME, B.CERTIFICATE_ID, B.CERTIFICATE_NAME "
                + "FROM DETAIL_CERTIFICATE A, CERTIFICATED B "
                + "WHERE A.CERTIFICATE_ID = B.CERTIFICATE_ID "
                + "ORDER BY A.SUBJECT_ID ASC ";
        return DBconnect.loadData(sql);
    }
//Truy van cac dong du lieu trong Table theo id

    public ResultSet showIDSub(String subId) throws SQLException {
        //cn.getConnect();
        String sql = "SELECT A.SUBJECT_ID, A.SUBJECT_NAME, B.CERTIFICATE_ID, B.CERTIFICATE_NAME "
                + "FROM DETAIL_CERTIFICATE A, CERTIFICATED B "
                + " WHERE A.CERTIFICATE_ID = B.CERTIFICATE_ID AND A.SUBJECT_ID = '" + subId + "'";
        return DBconnect.loadData(sql);
    }
//Them moi 1 dong du lieu vao table 

    public void insertData(String id, String name, String idCer) throws SQLException {
        DBconnect.getConnect();
        String sql = "INSERT INTO DETAIL_CERTIFICATE "
                + "VALUES('" + id + "',N'" + name + "',N'" + idCer + "')";
        DBconnect.updateData(sql);
    }
//Dieu chinh 1 dong du lieu vao table

    public void editData(String id, String name,String idCer) throws SQLException {
        DBconnect.getConnect();
        String sql = "UPDATE DETAIL_CERTIFICATE SET "+
                "SUBJECT_NAME = '" + name + "',"+
                "CERTIFICATE_ID = '" + idCer + "'"+
                
                "WHERE SUBJECT_ID = '" + id + "'";
        DBconnect.updateData(sql);
    }
//Xoa 1 dong du lieu vao table

    public void deleteData(String id) throws SQLException {
        String sql = "DELETE FROM DETAIL_CERTIFICATE WHERE SUBJECT_ID ='" + id + "'";
        DBconnect.updateData(sql);
    }
}
