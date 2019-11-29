/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import aplication.Registers;
import aplication.ReportRegistry;
import aplication.Students;
import database.DBconnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author David Nghi
 */
public class RegistryJFrame extends javax.swing.JFrame {

    private final Registers regObj = new Registers();

    private final Students stuRegObj = new Students();
    private boolean flag = true;
    private final DefaultTableModel tableModelReg = new DefaultTableModel();
    private final DefaultTableModel tableModel = new DefaultTableModel();

    private void showDataReg() throws SQLException {
        ResultSet result = regObj.showRegister();
        try {
            while (result.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[6];
                rows[0] = result.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã so)
                rows[1] = result.getString(2);
                rows[2] = result.getString(3);
                rows[3] = result.getString(4);
                rows[4] = result.getString(5);
                rows[5] = result.getString(6);
                tableModelReg.addRow(rows); // đưa dòng dữ liệu vào tableModel
            }
        } catch (SQLException e) {
        }
    }
//Ham xoa du lieu trong tableModel

    private void clearDataReg() throws SQLException {
//Lay chi so dong cuoi cung
        int n = tableModelReg.getRowCount() - 1;
        for (int i = n; i >= 0; i--) {
            tableModelReg.removeRow(i);//Remove tung dong
        }
    }

    private void clearData() throws SQLException {
//Lay chi so dong cuoi cung
        int n = tableModel.getRowCount() - 1;
        for (int i = n; i >= 0; i--) {
            tableModel.removeRow(i);//Remove tung dong
        }
    }

    private void loadCertificate() {
        cbtRegCert.removeAllItems();
        DBconnect.loadDataCombobox("SELECT * FROM CERTIFICATED ", this.cbtRegCert, "CERTIFICATE_ID");
        String query = "SELECT * FROM CERTIFICATED WHERE CERTIFICATE_ID = ?";
        DBconnect.loadDataTextfield(query, this.cbtRegCert, this.txtRegCerName, "CERTIFICATE_NAME");
    }

    private void loadStudents() {
        cbtRegStuID.removeAllItems();
        DBconnect.loadDataCombobox("SELECT * FROM STUDENTS", this.cbtRegStuID, "STUDENT_ID");
        String query = "SELECT * FROM STUDENTS WHERE STUDENT_ID = ?";
        DBconnect.loadDataTextfield(query, this.cbtRegStuID, this.txtRegFullName, "STUDENT_NAME");
    }
    private void loadExamDate() {
        cbtExamDate.removeAllItems();
        DBconnect.loadDataCombobox("SELECT * FROM COURSES ", this.cbtExamDate, "EXAM_DATE");
        String query = "SELECT * FROM COURSES WHERE EXAM_DATE = ?";
        DBconnect.loadDataTextfield(query, this.cbtExamDate, this.txtCourseName, "COURSE_NAME");
    }
    public final void showRegDataStudent() throws SQLException {
        ResultSet result = stuRegObj.showStudent();
        try {
            while (result.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[8];
                rows[0] = result.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã so)
                rows[1] = result.getString(2);
                rows[2] = result.getString(3);
                rows[3] = result.getString(4);
                rows[4] = result.getString(5);
                rows[5] = result.getString(6);
                rows[6] = result.getString(7);
                rows[7] = result.getString(8);
                tableModel.addRow(rows); // đưa dòng dữ liệu vào tableModel
            }
        } catch (SQLException e) {
        }
    }
//Ham xoa du lieu trong tableModel

    private static int getAge(Calendar bd) {
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - bd.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) <= bd.get(Calendar.DAY_OF_YEAR)) {
            return age - 1;
        }
        return age;
    }

    private static float getDateExam(Calendar bd) {
        Calendar today = Calendar.getInstance();
        float year = bd.get(Calendar.YEAR) - today.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) >= bd.get(Calendar.DAY_OF_YEAR)) {
            return year + 1;
        }
        return year;
    }

    private static int getDayExam(Calendar bd) {
        Calendar today = Calendar.getInstance();
        int day = bd.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) <= bd.get(Calendar.DAY_OF_YEAR)) {
            return day;
        }
        return day;
    }

    private void setNull() {
//Xoa trang cac JtextField
        //this.txtIDuser.setText(null);
        this.txtStuRegName.setText(null);
        this.txtStuRegIDCard.setText(null);
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        txtStuRegDOB.setDate(date);
        this.txtStuRegPhone.setText(null);
        this.txtStuRegEmail.setText(null);
        this.txtStuRegAdd.setText(null);
        this.txtStuRegID.requestFocus();
    }
//Ham khoa cac TextField

    private void setClockID(boolean a) {
//Khoa hoac mo khoa cho Cac JTextField
        this.txtStuRegID.setEnabled(!a);
    }

    private void setClock(boolean a) {
//Khoa hoac mo khoa cho Cac JTextField
        this.txtStuRegName.setEnabled(!a);
        this.txtStuRegIDCard.setEnabled(!a);
        this.radioMale.setEnabled(!a);
        this.radioFemale.setEnabled(!a);
        this.txtStuRegDOB.setEnabled(!a);
        this.txtStuRegPhone.setEnabled(!a);
        this.txtStuRegEmail.setEnabled(!a);
        this.txtStuRegAdd.setEnabled(!a);
    }
//Ham khoa cac Button

    private void setButton(boolean a) {
//Vo hieu hoac co hieu luc cho cac JButton
        this.btnRegStuAdd.setEnabled(a);
        this.btnRegStuDelete.setEnabled(a);
        this.btnRegStuEdit.setEnabled(a);
        this.btnStuRegSave.setEnabled(!a);
        this.btnRegStuReset.setEnabled(!a);
        this.btnRegister.setEnabled(!a);
        this.btnRegStuClose.setEnabled(a);
    }

    private void setNullReg() {
//Xoa trang cac JtextField
        //this.txtIDuser.setText(null);
        
        this.txtRegFullName.setText(null);

        this.txtRegCerName.setText(null);
    }

    private void setClockID_NUM(boolean a) {
//Khoa hoac mo khoa cho Cac JTextField
        this.txtRegIDNum.setEnabled(!a);

    }

    private void setClockReg(boolean a) {
//Khoa hoac mo khoa cho Cac JTextField
        this.cbtRegStuID.setEnabled(a);
        this.txtRegFullName.setEnabled(!a);
        this.cbtRegCert.setEnabled(a);
        this.txtRegCerName.setEnabled(!a);
        this.cbtExamDate.setEnabled(a);
        this.txtCourseName.setEnabled(!a);
    }
//Ham khoa cac Button

    private void setButtonReg(boolean a) {
//Vo hieu hoac co hieu luc cho cac JButton
        this.btnRegister.setEnabled(a);
        this.btnRegisterDel.setEnabled(a);
        this.btnRegisterEdit.setEnabled(a);
        this.btnRegisterReport.setEnabled(a);
        this.btnRegisterSave.setEnabled(!a);
        this.btnRegisterReset.setEnabled(!a);
        this.btnRegStuClose.setEnabled(a);
    }

    private void disableForm() {

        txtStuRegName.setEditable(false);
        txtStuRegIDCard.setEnabled(false);
        txtStuRegPhone.setEditable(false);
        txtStuRegAdd.setEditable(false);
        txtStuRegDOB.setEnabled(false);
        radioMale.setEnabled(false);
        radioFemale.setEnabled(false);
    }

    private void enableForm() {
        txtStuRegName.setEditable(true);
        txtStuRegIDCard.setEnabled(true);
        txtStuRegPhone.setEditable(true);
        txtStuRegAdd.setEditable(true);
        txtStuRegDOB.setEnabled(true);
        radioMale.setEnabled(true);
        radioFemale.setEnabled(true);
    }

    public RegistryJFrame() throws SQLException {
        initComponents();
        jLabelNotify.setVisible(false);
        setClockReg(true);
        String[] colsName = {"ID Student", "Full name", "Identity Card", "Gender", "DOB", "Phone", "Email", "Address"};
// đặt tiêu đề cột cho tableModel
        tableModel.setColumnIdentifiers(colsName);
// kết nối jtable với tableModel
        jTableStudentRegistry.setModel(tableModel);
        txtStuRegID.setText(DBconnect.getID("EXECUTE sp_STUDENTS_identityID"));
//gọi hàm ShowData để đưa dữ liệu vào tableModel
        showRegDataStudent();
        long millis = System.currentTimeMillis();
        java.sql.Date currentDate = new java.sql.Date(millis);
        txtStuRegDOB.setDate(currentDate);

        //DBconnect.loadData("SELECT * FROM USERS", jTableUser);
        //txtStuRegID.setText(DBconnect.getID("EXECUTE sp_STUDENTS_identityID"));
        String[] colsReg = {"ID Student", "Full name", "ID Certificate", "Certificate name", "Date of Exam", "Identity number"};
// đặt tiêu đề cột cho tableModel
        tableModelReg.setColumnIdentifiers(colsReg);
// kết nối jtable với tableModel
        jTableRegistry.setModel(tableModelReg);
//gọi hàm ShowData để đưa dữ liệu vào tableModel
        showDataReg();
        txtRegIDNum.setText(DBconnect.getID("EXECUTE sp_STUDENT_CER_identityID_NUM"));
        //txtRegDateExam.setDate(currentDate);
        loadStudents();
        loadCertificate();
        loadExamDate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        btnGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabelNotify = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtStuRegID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtStuRegName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtStuRegPhone = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtStuRegEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtStuRegAdd = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtStuRegIDCard = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        radioFemale = new javax.swing.JRadioButton();
        radioMale = new javax.swing.JRadioButton();
        txtStuRegDOB = new com.toedter.calendar.JDateChooser();
        btnRegStuEdit = new javax.swing.JButton();
        btnRegStuDelete = new javax.swing.JButton();
        btnStuRegSave = new javax.swing.JButton();
        btnRegStuReset = new javax.swing.JButton();
        btnRegStuAdd = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cbtRegStuID = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtRegFullName = new javax.swing.JTextField();
        cbtRegCert = new javax.swing.JComboBox<>();
        txtRegIDNum = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtRegCerName = new javax.swing.JTextField();
        btnRegister = new javax.swing.JButton();
        btnRegStuClose = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnRegisterEdit = new javax.swing.JButton();
        btnRegisterDel = new javax.swing.JButton();
        btnRegisterSave = new javax.swing.JButton();
        btnRegisterReport = new javax.swing.JButton();
        btnRegisterReset = new javax.swing.JButton();
        cbtExamDate = new javax.swing.JComboBox<>();
        txtCourseName = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableStudentRegistry = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableRegistry = new javax.swing.JTable();

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel10.setText("Address");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelNotify.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabelNotify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/notify.png"))); // NOI18N
        jLabelNotify.setText("Notify: Database has been Successfully");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabelNotify)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabelNotify)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Infomation of registry", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 16))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/idstudent.png"))); // NOI18N
        jLabel5.setText("Student ID");

        txtStuRegID.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fullname.png"))); // NOI18N
        jLabel6.setText("Full name");

        txtStuRegName.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/gender.png"))); // NOI18N
        jLabel7.setText("Gender");

        txtStuRegPhone.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/date.png"))); // NOI18N
        jLabel8.setText("Date of Birth");

        txtStuRegEmail.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtStuRegEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStuRegEmailActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/gmail.png"))); // NOI18N
        jLabel9.setText("Email");

        txtStuRegAdd.setColumns(20);
        txtStuRegAdd.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtStuRegAdd.setRows(3);
        jScrollPane1.setViewportView(txtStuRegAdd);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/phone.png"))); // NOI18N
        jLabel11.setText("Phone");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/address.png"))); // NOI18N
        jLabel12.setText("Address");

        txtStuRegIDCard.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/idcard.png"))); // NOI18N
        jLabel13.setText("Identity Card");

        btnGroup.add(radioFemale);
        radioFemale.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        radioFemale.setText("Female");

        btnGroup.add(radioMale);
        radioMale.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        radioMale.setText("Male");

        btnRegStuEdit.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnRegStuEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        btnRegStuEdit.setText("Edit");
        btnRegStuEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegStuEditMouseClicked(evt);
            }
        });

        btnRegStuDelete.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnRegStuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnRegStuDelete.setText("Delete");
        btnRegStuDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegStuDeleteMouseClicked(evt);
            }
        });

        btnStuRegSave.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnStuRegSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png"))); // NOI18N
        btnStuRegSave.setText("Save");
        btnStuRegSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStuRegSaveMouseClicked(evt);
            }
        });

        btnRegStuReset.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnRegStuReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reset1.png"))); // NOI18N
        btnRegStuReset.setText("Reset");
        btnRegStuReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegStuResetMouseClicked(evt);
            }
        });

        btnRegStuAdd.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnRegStuAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        btnRegStuAdd.setText("Add ");
        btnRegStuAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegStuAddMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(radioFemale)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtStuRegName, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                                .addComponent(txtStuRegID)
                                .addComponent(txtStuRegIDCard))))
                    .addComponent(jLabel12)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(53, 53, 53)
                        .addComponent(radioMale))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStuRegEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtStuRegPhone)
                                .addComponent(txtStuRegDOB, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRegStuDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStuRegSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegStuAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegStuEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegStuReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtStuRegID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtStuRegName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtStuRegIDCard, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegStuEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(radioMale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtStuRegDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(radioFemale, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStuRegPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtStuRegEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnRegStuAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegStuDelete)
                .addGap(28, 28, 28)
                .addComponent(btnStuRegSave)
                .addGap(30, 30, 30)
                .addComponent(btnRegStuReset)
                .addGap(32, 32, 32))
        );

        cbtRegStuID.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cbtRegStuID.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbtRegStuIDPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fullname.png"))); // NOI18N
        jLabel1.setText("Student Registry");

        txtRegFullName.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N

        cbtRegCert.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cbtRegCert.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbtRegCertPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txtRegIDNum.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exam.png"))); // NOI18N
        jLabel3.setText("Number of Exam");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/date.png"))); // NOI18N
        jLabel4.setText("Date of Exam");

        txtRegCerName.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N

        btnRegister.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registr.png"))); // NOI18N
        btnRegister.setText("Registry");
        btnRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegisterMouseClicked(evt);
            }
        });

        btnRegStuClose.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnRegStuClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/close.png"))); // NOI18N
        btnRegStuClose.setText("Close");
        btnRegStuClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegStuCloseMouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/certi.png"))); // NOI18N
        jLabel14.setText("Certificated");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel2.setText("Name of Registry");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/certicatename.png"))); // NOI18N
        jLabel15.setText("Name of Certificate");

        btnRegisterEdit.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnRegisterEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        btnRegisterEdit.setText("Edit");
        btnRegisterEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegisterEditMouseClicked(evt);
            }
        });

        btnRegisterDel.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnRegisterDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnRegisterDel.setText("Delete");
        btnRegisterDel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegisterDelMouseClicked(evt);
            }
        });

        btnRegisterSave.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnRegisterSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png"))); // NOI18N
        btnRegisterSave.setText("Save");
        btnRegisterSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegisterSaveMouseClicked(evt);
            }
        });

        btnRegisterReport.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnRegisterReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/report.png"))); // NOI18N
        btnRegisterReport.setText("Report");
        btnRegisterReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRegisterReportMousePressed(evt);
            }
        });

        btnRegisterReset.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnRegisterReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reset1.png"))); // NOI18N
        btnRegisterReset.setText("Reset");
        btnRegisterReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegisterResetMouseClicked(evt);
            }
        });

        cbtExamDate.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cbtExamDate.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbtExamDatePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txtCourseName.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel14)
                    .addComponent(jLabel1)
                    .addComponent(jLabel15)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRegFullName)
                    .addComponent(txtRegCerName)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtRegIDNum, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbtRegStuID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbtRegCert, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbtExamDate, javax.swing.GroupLayout.Alignment.LEADING, 0, 157, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addComponent(txtCourseName)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRegisterSave, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegisterReset)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnRegister)
                        .addGap(33, 33, 33)
                        .addComponent(btnRegisterDel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnRegisterReport)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegStuClose))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(btnRegisterEdit)))
                .addGap(37, 37, 37))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbtRegStuID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRegFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbtRegCert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRegCerName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cbtExamDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRegIDNum, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCourseName))
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegisterDel)
                    .addComponent(btnRegisterEdit)
                    .addComponent(btnRegister))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegisterSave)
                    .addComponent(btnRegStuClose)
                    .addComponent(btnRegisterReset)
                    .addComponent(btnRegisterReport))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTableStudentRegistry.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Student ID", "Full name", "Identity Card", "Gender", "Date of Birth", "Phone", "Email", "Address"
            }
        ));
        jTableStudentRegistry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableStudentRegistryMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableStudentRegistry);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jTableRegistry.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Student Id", "Student Name", "Certificate Id", "Certificate Name", "Exam Date", "ID Number"
            }
        ));
        jTableRegistry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableRegistryMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableRegistry);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.getAccessibleContext().setAccessibleName("Infomation of student");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtStuRegEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStuRegEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStuRegEmailActionPerformed

    private void jTableStudentRegistryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableStudentRegistryMouseClicked
        // TODO add your handling code here:
        try {
            int row = this.jTableStudentRegistry.getSelectedRow();
            TableModel model = jTableStudentRegistry.getModel();
            String stuID = (this.jTableStudentRegistry.getModel().getValueAt(row, 0)).toString();
            ResultSet rs = stuRegObj.showIDStudent(stuID);//Goi ham lay du lieu theo ma loai
            String sexStu = (this.jTableStudentRegistry.getModel().getValueAt(row, 3)).toString();
            String dobs = (this.jTableStudentRegistry.getModel().getValueAt(row, 4)).toString();
            ResultSet rs1 = stuRegObj.showIDStudent(sexStu);//Goi ham lay du lieu theo ma loai
            if (rs.next()) {
                this.txtStuRegID.setText(rs.getString("STUDENT_ID"));
                this.txtStuRegName.setText(rs.getString("STUDENT_NAME"));
                this.txtStuRegIDCard.setText(rs.getString("IDENTITY_CARD"));
                String sex = model.getValueAt(row, 3).toString();
                if (sex.equals("Male") == true || sex.equals("MALE") == true) {
                    this.radioMale.setSelected(true);
                    this.radioFemale.setSelected(false);
                } else {
                    this.radioFemale.setSelected(true);
                    this.radioMale.setSelected(false);
                }
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                txtStuRegDOB.setDate(rs.getDate("DOB"));
                this.txtStuRegPhone.setText(rs.getString("PHONE"));
                this.txtStuRegEmail.setText(rs.getString("EMAIL"));
                this.txtStuRegAdd.setText(rs.getString("STUDENT_ADD"));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }//GEN-LAST:event_jTableStudentRegistryMouseClicked

    private void btnRegStuAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegStuAddMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:

        txtStuRegID.setText(DBconnect.getID("EXECUTE sp_STUDENTS_identityID"));
        setClockID(true);
        setNull();//Xoa trang TextField
        setClock(false);//Mo khoa TextField
        setButton(false);//Goi ham khoa cac Button
        flag = true;//Gan cothem = true de ghi nhan trang thai them moi
    }//GEN-LAST:event_btnRegStuAddMouseClicked

    private void btnRegStuEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegStuEditMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        String id = txtStuRegID.getText();
        if (id.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please choose a id item for edit",
                    "Message", 1);
        } else {
            setClock(false);//Mo khoa cac TextField
            this.txtStuRegID.enable(false);
            setButton(false); //Khoa cac Button
            flag = false; //Gan cothem=false de ghi nhan trang thai la sua
        }
    }//GEN-LAST:event_btnRegStuEditMouseClicked

    private void btnRegStuDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegStuDeleteMouseClicked
        // TODO add your handling code here:
        jLabelNotify.setVisible(false);
        String id = txtStuRegID.getText();
        try {
            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "Choose a ID item for delete",
                        "Message", 1);
            } else {
                if (JOptionPane.showConfirmDialog(null, "Do you want to delete " + id + " this item ? ", "Message", 2) == 0) {
                    stuRegObj.deleteData(id);//goi ham xoa du lieu theo ma loai
                    clearData();//Xoa du lieu trong tableModel
                    showRegDataStudent();//Do du lieu vao table Model
                    setNull();//Xoa trang Textfield
                    jLabelNotify.setVisible(true);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fail", "Message", 1);
        }
    }//GEN-LAST:event_btnRegStuDeleteMouseClicked

    private void btnRegStuResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegStuResetMouseClicked
        // TODO add your handling code here:
        setNull();
        setClock(true);
        setButton(true);
    }//GEN-LAST:event_btnRegStuResetMouseClicked
    static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    private void btnStuRegSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStuRegSaveMouseClicked
        // TODO add your handling code here:
        Date dateOfBirth = txtStuRegDOB.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dob = sdf.format(dateOfBirth);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOfBirth);
        String stuId = txtStuRegID.getText();
        String fullname = txtStuRegName.getText();
        String idCard = txtStuRegIDCard.getText();
        String sex = null;
        if (radioMale.isSelected()) {
            sex = "Male";
        } else {
            sex = "Female";
        }
        String phone = txtStuRegPhone.getText();
        String mail = txtStuRegEmail.getText();
        String add = txtStuRegAdd.getText();
        if (fullname.matches("^[a-zA-Z_0-9\\s]{5,40}") == false) {
            JOptionPane.showMessageDialog(null, "Name cannot empty. Name does not contain special characters!", "Message", 1);
            txtStuRegName.requestFocus();
        } else if (idCard.matches("^[0-9]{9,12}") == false) {
            JOptionPane.showMessageDialog(null, "Username must be between 9 and 12 characters. Name does not contain special characters!", "Message", 1);
            txtStuRegIDCard.requestFocus();
        } else if (add.matches(".{10,100}") == false) {
            JOptionPane.showMessageDialog(null, "Address must be greater than 10 characters and less than 100 characters!", "Message", 1);
            txtStuRegAdd.requestFocus();
        } else if (phone.matches("^[0-9]{9,13}") == false) {
            JOptionPane.showMessageDialog(null, "Invalid phone number, please try again!", "Message", 1);
            txtStuRegPhone.requestFocus();
        } else if (isValidEmail(mail) == false) {
            JOptionPane.showMessageDialog(null, "Invalid your email, please try again!", "Message", 1);
            txtStuRegEmail.requestFocus();
        } else if (getAge(calendar) < 10) {
            JOptionPane.showMessageDialog(null, "must be over 10 years old to register!", "Message", 1);
            txtStuRegDOB.requestFocus();
        } else {
            try {

                if (flag == true) {
                    stuRegObj.insertData(stuId, fullname, idCard, dob, sex, phone, mail, add);
                    jLabelNotify.setVisible(true);
                } else {
                    stuRegObj.editData(stuId, fullname, idCard, dob, sex, phone, mail, add);
                    jLabelNotify.setVisible(true);
                }

                clearData(); //goi ham xoa du lieu tron tableModel
                showRegDataStudent(); //Do lai du lieu vao Table Model
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Updated successfull",
                        "Message", 1);
            }
            setClock(false);
            setButton(true);
        }
    }//GEN-LAST:event_btnStuRegSaveMouseClicked

    private void btnRegStuCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegStuCloseMouseClicked
        // TODO add your handling code here:
          this.dispose();
         new HomeJFrame().setVisible(true);
    }//GEN-LAST:event_btnRegStuCloseMouseClicked

    private void cbtRegStuIDPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbtRegStuIDPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        String query = "SELECT * FROM STUDENTS WHERE STUDENT_ID = ?";
        DBconnect.loadDataTextfield(query, this.cbtRegStuID, this.txtRegFullName, "STUDENT_NAME");
    }//GEN-LAST:event_cbtRegStuIDPopupMenuWillBecomeInvisible

    private void cbtRegCertPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbtRegCertPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        String query = "SELECT * FROM CERTIFICATED WHERE CERTIFICATE_ID = ?";
        DBconnect.loadDataTextfield(query, this.cbtRegCert, this.txtRegCerName, "CERTIFICATE_NAME");
    }//GEN-LAST:event_cbtRegCertPopupMenuWillBecomeInvisible

    private void jTableRegistryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableRegistryMouseClicked
        // TODO add your handling code here:tableModelReg
        try {
            //Lay chi so dong dang chon
            int row = this.jTableRegistry.getSelectedRow();
            TableModel model = jTableRegistry.getModel();
            String stuID = (this.jTableRegistry.getModel().getValueAt(row, 0)).toString();
            String stuName = (this.jTableRegistry.getModel().getValueAt(row, 1)).toString();
            //System.out.println(stuID);

            //ResultSet rs = regObj.showIDStu(stuID);//Goi ham lay du lieu theo
            String cerID = (this.jTableRegistry.getModel().getValueAt(row, 2)).toString();
            String cerName = (this.jTableRegistry.getModel().getValueAt(row, 3)).toString();
            String dateReg = (this.jTableRegistry.getModel().getValueAt(row, 4)).toString();
            String idNum = (this.jTableRegistry.getModel().getValueAt(row, 5)).toString();
            //ResultSet rs1 = regObj.showIDCer(cerID);//Goi ham lay du lieu
            ResultSet rs2 = regObj.showIDStuReg(stuID);//Goi ham lay du lieu theo
            if (rs2.next()) {
                this.cbtRegStuID.setSelectedItem(rs2.getString("STUDENT_ID"));
                this.txtRegFullName.setText(rs2.getString("STUDENT_NAME"));
                this.cbtRegCert.setSelectedItem(rs2.getString("CERTIFICATE_ID"));
                this.txtRegCerName.setText(rs2.getString("CERTIFICATE_NAME"));
                this.cbtExamDate.setSelectedItem(rs2.getString("EXAM_DATE"));
         
                this.txtRegIDNum.setText(rs2.getString("ID_NUM"));
            }
            String queryStu = "SELECT * FROM STUDENTS WHERE STUDENT_ID = ?";
            DBconnect.loadDataTextfield(queryStu, this.cbtRegStuID, this.txtRegFullName, "STUDENT_NAME");
            String query = "SELECT * FROM CERTIFICATED WHERE CERTIFICATE_ID = ?";
            DBconnect.loadDataTextfield(query, this.cbtRegCert, this.txtRegCerName, "CERTIFICATE_NAME");

        } catch (SQLException e) {
        }
    }//GEN-LAST:event_jTableRegistryMouseClicked

    private void btnRegisterDelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterDelMouseClicked
        // TODO add your handling code here:cbtRegCert
        jLabelNotify.setVisible(false);
        String idStu = (String) cbtRegStuID.getSelectedItem();
        String idCer = (String) cbtRegCert.getSelectedItem();
        try {
            if (idStu.length() == 0 || idCer.length() == 0) {
                JOptionPane.showMessageDialog(null, "Choose a ID item for delete",
                        "Message", 1);
            } else {
                if (JOptionPane.showConfirmDialog(null, "Do you want to delete " + idStu + " this item ? ", "Message", 2) == 0) {
                    regObj.deleteDataReg(idStu);//goi ham xoa du lieu theo ma loai
                    clearDataReg();//Xoa du lieu trong tableModel
                    showDataReg();//Do du lieu vao table Model
                    setNull();//Xoa trang Textfield
                    jLabelNotify.setVisible(true);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fail", "Message", 1);
        }
    }//GEN-LAST:event_btnRegisterDelMouseClicked

    private void btnRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterMouseClicked
        // TODO add your handling code here:
        txtRegIDNum.setText(DBconnect.getID("EXECUTE sp_STUDENT_CER_identityID_NUM"));
        setClockID_NUM(true);
        setNullReg();//Xoa trang TextField
        setClockReg(true);//Mo khoa TextField
        setButtonReg(false);//Goi ham khoa cac Button
        flag = true;
    }//GEN-LAST:event_btnRegisterMouseClicked

    private void btnRegisterSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterSaveMouseClicked
        // TODO add your handling code here:
//        Date date = cbtExamDate.getDate();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String dExam = sdf.format(date);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
        /*
        this.cbtRegStuID
        this.txtRegFullName
        this.cbtRegCert
        this.txtRegCerName
        this.txtRegDateExam
        this.txtRegIDNum
         */
        String dateExam = cbtExamDate.getSelectedItem().toString();
        String idStu = cbtRegStuID.getSelectedItem().toString();
        String idCer = cbtRegCert.getSelectedItem().toString();
        String idNum = txtRegIDNum.getText();
        //System.out.println(getDateExam(calendar));
        //System.out.println(getDayExam(calendar));

        if (idStu.length() == 0) {
            JOptionPane.showMessageDialog(null, "Student is empty. Please add student at a from left!", "Message", 1);
            txtStuRegName.requestFocus();
        } else if (idCer.length() == 0) {
            JOptionPane.showMessageDialog(null, "Certificate is empty. Please add student at a from left!", "Message", 1);
            txtStuRegIDCard.requestFocus();
        } else if (dateExam.length() == 0) {
            JOptionPane.showMessageDialog(null, "not empty must be over 20 days new to register!", "Message", 1);
            cbtExamDate.requestFocus();
        } else {
            try {

                if (flag == true) {
                    regObj.insertDataReg(idStu, idCer, dateExam, idNum);
                    jLabelNotify.setVisible(true);
                } else {
                    regObj.editDataReg(idStu, idCer, dateExam, idNum);
                    jLabelNotify.setVisible(true);
                }

                clearDataReg(); //goi ham xoa du lieu tron tableModel
                showDataReg(); //Do lai du lieu vao Table Model
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Updated successfull",
                        "Message", 1);
            }
            setClockReg(false);
            setButtonReg(true);
        }
    }//GEN-LAST:event_btnRegisterSaveMouseClicked

    private void btnRegisterResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterResetMouseClicked
        // TODO add your handling code here:
        setNullReg();
        setClockReg(true);
        setButtonReg(true);
    }//GEN-LAST:event_btnRegisterResetMouseClicked

    private void btnRegisterReportMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterReportMousePressed
        // TODO add your handling code here:
        ReportRegistry.ExportExcel(jTableRegistry);
    }//GEN-LAST:event_btnRegisterReportMousePressed

    private void btnRegisterEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterEditMouseClicked
        // TODO add your handling code here:
        jLabelNotify.setVisible(false);
        String idStu = (String) cbtRegStuID.getSelectedItem();
        String idCer = (String) cbtRegCert.getSelectedItem();
        String examD = (String) cbtExamDate.getSelectedItem();
        if (idStu.length() == 0 || idCer.length() == 0 || examD.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please choose a id item for edit",
                "Message", 1);
        } else {
            setClockReg(false);//Mo khoa cac TextField
            this.txtRegIDNum.enable(false);
            this.txtRegFullName.enable(false);
            this.txtRegCerName.enable(false);
            this.txtCourseName.enable(false);
            setButtonReg(false); //Khoa cac Button
            flag = false; //Gan cothem=false de ghi nhan trang thai la sua
        }
    }//GEN-LAST:event_btnRegisterEditMouseClicked

    private void cbtExamDatePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbtExamDatePopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        String query = "SELECT * FROM COURSES WHERE EXAM_DATE = ?";
        DBconnect.loadDataTextfield(query, this.cbtExamDate, this.txtCourseName, "COURSE_NAME");
    }//GEN-LAST:event_cbtExamDatePopupMenuWillBecomeInvisible

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistryJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistryJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistryJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistryJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new RegistryJFrame().setVisible(true);
                new RegistryJFrame().setResizable(false);
            } catch (Exception e) {
                e.getMessage();
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JButton btnRegStuAdd;
    private javax.swing.JButton btnRegStuClose;
    private javax.swing.JButton btnRegStuDelete;
    private javax.swing.JButton btnRegStuEdit;
    private javax.swing.JButton btnRegStuReset;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnRegisterDel;
    private javax.swing.JButton btnRegisterEdit;
    private javax.swing.JButton btnRegisterReport;
    private javax.swing.JButton btnRegisterReset;
    private javax.swing.JButton btnRegisterSave;
    private javax.swing.JButton btnStuRegSave;
    private javax.swing.JComboBox<String> cbtExamDate;
    private javax.swing.JComboBox<String> cbtRegCert;
    private javax.swing.JComboBox<String> cbtRegStuID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelNotify;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableRegistry;
    private javax.swing.JTable jTableStudentRegistry;
    private javax.swing.JRadioButton radioFemale;
    private javax.swing.JRadioButton radioMale;
    private javax.swing.JTextField txtCourseName;
    private javax.swing.JTextField txtRegCerName;
    private javax.swing.JTextField txtRegFullName;
    private javax.swing.JTextField txtRegIDNum;
    private javax.swing.JTextArea txtStuRegAdd;
    private com.toedter.calendar.JDateChooser txtStuRegDOB;
    private javax.swing.JTextField txtStuRegEmail;
    private javax.swing.JTextField txtStuRegID;
    private javax.swing.JTextField txtStuRegIDCard;
    private javax.swing.JTextField txtStuRegName;
    private javax.swing.JTextField txtStuRegPhone;
    // End of variables declaration//GEN-END:variables
}
