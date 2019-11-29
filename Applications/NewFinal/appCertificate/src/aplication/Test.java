/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication;

import database.DBconnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Nghi
 */
public class Test {

    private static int getDayExam(Calendar bd) {
        Calendar today = Calendar.getInstance();
        int day = bd.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) <= bd.get(Calendar.DAY_OF_YEAR)) {
            return day + 30;
        }
        return day;
    }

    private void showDataExam() throws SQLException {
        try {
            DBconnect.getConnect();

            ResultSet rs = examObj.showExam();
            System.out.println("COLUMN NAME\tDATATYPE\tNULL\tKEY\tDEFAULT\tEXTRA");
            while (rs.next()) {
                System.out.print(rs.getString(1) + "\t");
                System.out.print(rs.getString(2) + "\t");
                System.out.print(rs.getString(3) + "\t");
                System.out.print(rs.getString(4) + "\t");
                System.out.print(rs.getString(5) + "\t");
                System.out.println(rs.getString(6));
                if(rs.getString(3)=="No1"){
                System.out.println("toi la ai");
            }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
        }

    }
    private static final ExamMarks examObj = new ExamMarks();

    public static void main(String[] args) throws SQLException{
        try {
            DBconnect.getConnect();

            ResultSet rs = examObj.showExam();
            System.out.println("COLUMN NAME\tDATATYPE\tNULL\tKEY\tDEFAULT\tEXTRA");
            while (rs.next()) {
                System.out.print(rs.getString(1) + "\t");
                System.out.print(rs.getString(2) + "\t");
                System.out.print(rs.getString(3) + "\t");
                System.out.print(rs.getString(4) + "\t");
                System.out.print(rs.getString(5) + "\t");
                System.out.println(rs.getString(6));
            }
                    } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
