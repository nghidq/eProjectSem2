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
public class Registers {

    private String idStu;
    private String idCer;
    private String examDate;
    private String idNum;
    private float avgMark;
    private String result;

    public String getIdStu() {
        return idStu;
    }

    public void setIdStu(String idStu) {
        this.idStu = idStu;
    }

    public String getIdCer() {
        return idCer;
    }

    public void setIdCer(String idCer) {
        this.idCer = idCer;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public float getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(float avgMark) {
        this.avgMark = avgMark;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /*
    STUDENT_CER(
	STUDENT_ID NVARCHAR(20),
	CERTIFICATE_ID NVARCHAR(20),
	EXAM_DATE DATE,
	ID_NUM NVARCHAR(20) NOT NULL UNIQUE,
	AVG_MARK FLOAT,
	RESULT NVARCHAR(20),
     */
    //Truy van tat ca du lieu trong Table
    public ResultSet showRegister() throws SQLException {
        //cn.getConnect();
        String sql = "SELECT B.STUDENT_ID, B.STUDENT_NAME, C.CERTIFICATE_ID, C.CERTIFICATE_NAME, A.EXAM_DATE, A.ID_NUM \n"
                + "FROM STUDENT_CER A, STUDENTS B, CERTIFICATED C\n"
                + "WHERE A.STUDENT_ID = B.STUDENT_ID AND A.CERTIFICATE_ID = C.CERTIFICATE_ID ";
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
    public int ShowKQ() throws SQLException {
        DBconnect.getConnect();
        String sql = "UPDATE STUDENT_CER \n"
                + "  set STUDENT_CER.RESULT = case\n"
                + "                  when STUDENT_CER.AVG_MARK >=8 then 'A'\n"
                + "                  when STUDENT_CER.AVG_MARK >=6 then 'B'\n"
                + "				  when STUDENT_CER.AVG_MARK >=5 then 'C'\n"
                + "				  else null end\n"
                + "UPDATE STUDENT_CER\n"
                + "SET STUDENT_CER.RESULT=NULL\n"
                + "update STUDENT_CER \n"
                + "SET STUDENT_CER.RESULT=case\n"
                + "                  when KQ.DIEMTB >=7 then 'A'\n"
                + "                  when KQ.DIEMTB >=6 then 'B'\n"
                + "				  when KQ.DIEMTB >=4 then 'C'\n"
                + "				  else null end\n"
                + "FROM STUDENT_CER AS SC,\n"
                + "(SELECT sc.ID_NUM,s.STUDENT_ID,s.STUDENT_NAME, c.CERTIFICATE_ID,c.CERTIFICATE_NAME,sc.EXAM_DATE,avg(e.MARK) as DIEMTB \n"
                + "FROM EXAM_MARK AS e, STUDENTS AS s, CERTIFICATED AS c, STUDENT_CER AS sc,\n"
                + "(SELECT sc.ID_NUM,s.STUDENT_ID,s.STUDENT_NAME, c.CERTIFICATE_ID,c.CERTIFICATE_NAME,sc.EXAM_DATE \n"
                + "FROM EXAM_MARK AS e, STUDENTS AS s, CERTIFICATED AS c, STUDENT_CER AS sc\n"
                + "WHERE \n"
                + "e.ID_NUM=sc.ID_NUM\n"
                + "AND s.STUDENT_ID=sc.STUDENT_ID\n"
                + "AND c.CERTIFICATE_ID=sc.CERTIFICATE_ID\n"
                + "Group by sc.ID_NUM,s.STUDENT_ID,s.STUDENT_NAME, c.CERTIFICATE_ID,c.CERTIFICATE_NAME,sc.EXAM_DATE\n"
                + "HAVING avg(e.MARK) >=4\n"
                + "except\n"
                + "SELECT sc.ID_NUM,s.STUDENT_ID,s.STUDENT_NAME, c.CERTIFICATE_ID,c.CERTIFICATE_NAME,sc.EXAM_DATE\n"
                + "FROM EXAM_MARK AS e, STUDENTS AS s, CERTIFICATED AS c, STUDENT_CER AS sc\n"
                + "WHERE e.ID_NUM=sc.ID_NUM\n"
                + "AND s.STUDENT_ID=sc.STUDENT_ID\n"
                + "AND c.CERTIFICATE_ID=sc.CERTIFICATE_ID\n"
                + "AND e.MARK<4) as T\n"
                + "WHERE \n"
                + "T.ID_NUM=sc.ID_NUM\n"
                + "and e.ID_NUM=sc.ID_NUM\n"
                + "AND s.STUDENT_ID=sc.STUDENT_ID\n"
                + "AND c.CERTIFICATE_ID=sc.CERTIFICATE_ID\n"
                + "Group by sc.ID_NUM,s.STUDENT_ID,s.STUDENT_NAME, c.CERTIFICATE_ID,c.CERTIFICATE_NAME,sc.EXAM_DATE\n"
                + "HAVING avg(e.MARK) >=4) AS KQ\n"
                + "WHERE SC.ID_NUM=KQ.ID_NUM\n"
                + "update STUDENT_CER \n"
                + "SET STUDENT_CER.AVG_MARK=KQ.DIEMTB\n"
                + "FROM STUDENT_CER AS SC,\n"
                + "(SELECT sc.ID_NUM,s.STUDENT_ID,s.STUDENT_NAME, c.CERTIFICATE_ID,c.CERTIFICATE_NAME,sc.EXAM_DATE, AVG(e.MARK) as DIEMTB\n"
                + "FROM EXAM_MARK AS e, STUDENTS AS s, CERTIFICATED AS c, STUDENT_CER AS sc\n"
                + "WHERE \n"
                + "e.ID_NUM=sc.ID_NUM\n"
                + "AND s.STUDENT_ID=sc.STUDENT_ID\n"
                + "AND c.CERTIFICATE_ID=sc.CERTIFICATE_ID\n"
                + "Group by sc.ID_NUM,s.STUDENT_ID,s.STUDENT_NAME, c.CERTIFICATE_ID,c.CERTIFICATE_NAME,sc.EXAM_DATE)\n"
                + " AS KQ\n"
                + "WHERE SC.ID_NUM=KQ.ID_NUM\n"
                + "insert into CER_MANAGER (ID_NUM, RESULT)\n"
                + "select ID_NUM, RESULT from STUDENT_CER \n"
                + "where STUDENT_CER.RESULT IS NOT NULL";
        return DBconnect.upData(sql);
    }

    public ResultSet ShowIDNum(String IDNum) throws SQLException {
        DBconnect.getConnect();
        String sql = "SELECT c.CERTIFICATE_NAME,s.STUDENT_NAME,s.DOB,s.STUDENT_ADD, sc.EXAM_DATE, sc.RESULT,cm.NO_ID\n"
                + "FROM CER_MANAGER AS cm, STUDENTS AS s, CERTIFICATED AS c, STUDENT_CER AS sc\n"
                + "WHERE cm.ID_NUM=sc.ID_NUM\n"
                + "AND s.STUDENT_ID=sc.STUDENT_ID\n"
                + "AND c.CERTIFICATE_ID=sc.CERTIFICATE_ID\n"
                + "AND cm.ID_NUM='" + IDNum + "'";
        return DBconnect.loadData(sql);
    }
}
