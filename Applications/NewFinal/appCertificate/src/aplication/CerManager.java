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
public class CerManager {
    private String noID;
    private String idNum;
    private String idStu;

    public String getIdStu() {
        return idStu;
    }

    public void setIdStu(String idStu) {
        this.idStu = idStu;
    }

    public String getNoID() {
        return noID;
    }

    public void setNoID(String noID) {
        this.noID = noID;
    }

    
    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

     //Truy van tat ca du lieu trong Table
    public ResultSet showCerManager() throws SQLException {
       //cn.getConnect();
        String sql = "SELECT C.NO_ID, B.STUDENT_ID, B.STUDENT_NAME, C.ID_NUM, D.CERTIFICATE_NAME " 
                + "FROM STUDENT_CER A, STUDENTS B, CER_MANAGER C, CERTIFICATED D " 
                + "WHERE A.STUDENT_ID = B.STUDENT_ID AND C.ID_NUM  = A.ID_NUM AND A.CERTIFICATE_ID = D.CERTIFICATE_ID AND A.RESULT IS NOT NULL "
                + "GROUP BY C.NO_ID, B.STUDENT_ID, B.STUDENT_NAME, C.ID_NUM, D.CERTIFICATE_NAME";
        return DBconnect.loadData(sql);
    }
//Truy van cac dong du lieu trong Table theo id

    public ResultSet showIDCer(String noId) throws SQLException {
        //cn.getConnect();
        String sql = "SELECT C.NO_ID, B.STUDENT_ID, B.STUDENT_NAME, C.ID_NUM, D.CERTIFICATE_NAME"
                + " FROM STUDENT_CER A, STUDENTS B, CER_MANAGER C, CERTIFICATED D"
                + " WHERE A.STUDENT_ID = B.STUDENT_ID AND C.ID_NUM  = A.ID_NUM AND A.CERTIFICATE_ID = D.CERTIFICATE_ID AND A.RESULT IS NOT NULL AND C.NO_ID = '" + noId + "'";
        return DBconnect.loadData(sql);
    }
//Them moi 1 dong du lieu vao table 

    public void insertDataCerMan(String id, String num) throws SQLException {
        DBconnect.getConnect();
        String sql = "INSERT INTO CER_MANAGER "
                + "VALUES('" + id + "',N'" + num + "')";
        DBconnect.updateData(sql);
    }
//Dieu chinh 1 dong du lieu vao table

    public void editDataCerMan(String id, String num) throws SQLException {
        DBconnect.getConnect();
        String sql = "UPDATE CER_MANAGER SET "+
                "ID_NUM = '" + num + "'"+
                "WHERE NO_ID = '" + id + "'";
        DBconnect.updateData(sql);
    }
//Xoa 1 dong du lieu vao table

    public void deleteDataCerMan(String id) throws SQLException {
        String sql = "DELETE FROM CER_MANAGER WHERE NO_ID ='" + id + "'";
        DBconnect.updateData(sql);
    }
}
