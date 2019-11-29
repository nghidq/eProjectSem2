/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import aplication.Users;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Nghi
 */
public class DBconnect {

    static Connection conn = null;
    public static DefaultTableModel tableModel;
    static String hostDB, dbName, accSQL, passSQL;

    //static String dbSettingsPropertyFile = "./src/database/config.properties";
    //Phuong thuc thuc hien ket noi CSDL
    public static Connection getConnect() {

        try {
            readConfig();
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(driver);
            String url = "jdbc:sqlserver://" + hostDB + ";databaseName=" + dbName;
            conn = DriverManager.getConnection(url, accSQL, passSQL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            //System.out.println("Field Error");
            conn = null;
        }
        return conn;
    }

    public static void readConfig() {
        try {
            //String cerDir = new java.io.File(".").getCanonicalPath();
            String cerDir = System.getProperty("user.dir");
            // Create Properties object.
            Properties props = new Properties();
            String dbSettingsPropertyFile = cerDir + "\\config.properties";
            // Properties will use a FileReader object as input.
            FileReader fReader = new FileReader(dbSettingsPropertyFile);
            // Load jdbc related properties in above file. 
            props.load(fReader);
            // Get each property value.
            String dbDriverClass = props.getProperty("db.driver.class");
            hostDB = props.getProperty("db.conn.url");
            dbName = props.getProperty("db.dbname");
            accSQL = props.getProperty("db.username");
            passSQL = props.getProperty("db.password");
        } catch (IOException ex) {
        }
    }

//Phuong thuc dung de truy van CSDL
    public static ResultSet loadData(String sql) {
        getConnect();
        ResultSet result = null;
        try {
            Statement statement = conn.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    // de up date bang du lieu
    public static int upData(String sql) {
        getConnect();
        ResultSet result = null;
        try {
            Statement statement = conn.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return 0;
    }
    /*
    public static void loadData(String sql, JTable tbl) {
        try {
            getConnect();
            Statement stt = getConnect().createStatement();
            ResultSet rs = stt.executeQuery(sql);
            
            ResultSetMetaData metadata = rs.getMetaData();
            int numberColumns = metadata.getColumnCount();
            ArrayList<String> arrColumns = new ArrayList<>();
            for (int i = 1; i <= numberColumns; i++) {
                arrColumns.add(metadata.getColumnName(i));
            }
            tableModel.setColumnIdentifiers(arrColumns.toArray());
            ArrayList<String> arrRow = new ArrayList<>();
            while(rs.next()){
                for (int i = 1; i <= numberColumns; i++) {
                    arrRow.add(rs.getString(i));
                }
                tableModel.addRow(arrRow.toArray());
                arrRow.clear();
            }
            
            tbl.setModel(tableModel);
            rs.close();
            stt.close();
            getConnect().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data loaded into table failed!", "Message", 1);
        }
    }
     */
//Phuong thuc thuc hien Them, Xoa, Sua du lieu
    public static void updateData(String sql) {
        //int executeUpdate = 0;
        try {
            getConnect();
            try (Statement state = conn.createStatement()) {
                state.executeUpdate(sql);
                DBconnect.conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void loadDataCombobox(String query, JComboBox cbb, String field) {
        try {
            getConnect();
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                cbb.addItem(result.getString(field));
            }
            result.close();
            pst.close();
            getConnect().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data loaded into combobox failed!", "Message", 1);
        }
    }

    public static void loadDataTextfield(String query, JComboBox cbb, JTextField txt, String field) {
        try {
            getConnect();
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, (String) cbb.getSelectedItem());
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                txt.setText(result.getString(field));
            } else {
                txt.setText(null);
            }
            result.close();
            pst.close();
            getConnect().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data loaded into textfield failed!", "Message", 1);
        }
    }

    public static String getID(String procedure) {
        String ID = null;
        try {
            getConnect();
            Statement stt = conn.createStatement();
            ResultSet result = stt.executeQuery(procedure);
            while (result.next()) {
                ID = result.getString(1);
            }
            stt.close();
            result.close();
            getConnect().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Get ID failed!", "Message", 1);
        }
        return ID;
    }

    public static String getName(String procedure) {
        String ID = null;
        try {
            getConnect();
            Statement stt = conn.createStatement();
            ResultSet result = stt.executeQuery(procedure);
            while (result.next()) {
                ID = result.getString(1);
            }
            stt.close();
            result.close();
            getConnect().close();
        } catch (SQLException ex) {

        }
        return ID;
    }

    public static ResultSet displayInformation(String sql) {
        ResultSet rsDisplay = null;
        try {
            getConnect();
            Statement state = conn.createStatement();
            rsDisplay = state.executeQuery(sql);
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Detailed display failed!", "Message", 1);
        }
        return rsDisplay;
    }

    /*
    public static int updateData(String sql){
        int mess=0;
        try {
            getConnect();
            Statement state = conn.createStatement();
            mess = state.executeUpdate(sql);
            state.close();
            getConnect().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data already exists in the system, please try again!", "Message", 1);
        }
        return mess;
    }
     */
    public static String checkRole() {
        Users u = new Users();
        String role = null;
        try {
            getConnect();
            Statement stt = getConnect().createStatement();
            String sql = "SELECT * FROM USERS WHERE USERNAME = '" + u.getuName() + "'";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                role = rs.getString("ROLES");
            }
            rs.close();
            stt.close();
            getConnect().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return role;
    }
}
