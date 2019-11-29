/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import aplication.ReportStudent;
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
public class StudentJFrame extends javax.swing.JFrame {

    /**
     * Creates new form StudentJFrame
     */
    private final Students stuObj = new Students();
    private boolean flag = true;
    private final DefaultTableModel tableModel = new DefaultTableModel();

    public final void showDataStudent() throws SQLException {
        ResultSet result = stuObj.showStudent();
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

    public void clearData() throws SQLException {
//Lay chi so dong cuoi cung
        int n = tableModel.getRowCount() - 1;
        for (int i = n; i >= 0; i--) {
            tableModel.removeRow(i);//Remove tung dong
        }
        
    }
    
    private static int getAge(Calendar bd) {
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - bd.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) <= bd.get(Calendar.DAY_OF_YEAR)) {
            return age - 1;
        }
        return age;
    }

    private void setNull() {
//Xoa trang cac JtextField
        //this.txtIDuser.setText(null);
        this.txtStuFullName.setText(null);
        this.txtStuIdCard.setText(null);
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        txtStuDOB.setDate(date);
        this.txtStuPhone.setText(null);
        this.txtStuEmail.setText(null);
        this.txtStuAddress.setText(null);
        this.txtStuID.requestFocus();
    }
//Ham khoa cac TextField

    private void setClockID(boolean a) {
//Khoa hoac mo khoa cho Cac JTextField
        this.txtStuID.setEnabled(!a);
    }

    private void setClock(boolean a) {
//Khoa hoac mo khoa cho Cac JTextField
        this.txtStuFullName.setEnabled(!a);
        this.txtStuIdCard.setEnabled(!a);
        this.radioGender.setEnabled(!a);
        this.radioGenderFe.setEnabled(!a);
        this.txtStuDOB.setEnabled(!a);
        this.txtStuPhone.setEnabled(!a);
        this.txtStuEmail.setEnabled(!a);
        this.txtStuAddress.setEnabled(!a);
    }
//Ham khoa cac Button

    private void setButton(boolean a) {
//Vo hieu hoac co hieu luc cho cac JButton
        this.btnStuAdd.setEnabled(a);
        this.btnStuDelete.setEnabled(a);
        this.btnStuEdit.setEnabled(a);
        this.btnStuSave.setEnabled(!a);
        this.btnStuReset.setEnabled(!a);
        this.btnStuReport.setEnabled(!a);
        this.btnStuClose.setEnabled(a);
    }

    private void disableForm() {

        txtStuFullName.setEditable(false);
        txtStuIdCard.setEnabled(false);
        txtStuPhone.setEditable(false);
        txtStuAddress.setEditable(false);
        txtStuDOB.setEnabled(false);
        radioGender.setEnabled(false);
        radioGenderFe.setEnabled(false);
    }

    private void enableForm() {
        txtStuFullName.setEditable(true);
        txtStuIdCard.setEnabled(true);
        txtStuPhone.setEditable(true);
        txtStuAddress.setEditable(true);
        txtStuDOB.setEnabled(true);
        radioGender.setEnabled(true);
        radioGenderFe.setEnabled(true);
    }

    public StudentJFrame() throws SQLException {
        initComponents();
        jLabelNotify.setVisible(false);
        String[] colsName = {"ID Student", "Full name", "Identity Card", "Gender", "DOB", "Phone", "Email", "Address"};
// đặt tiêu đề cột cho tableModel
        tableModel.setColumnIdentifiers(colsName);
// kết nối jtable với tableModel
        jTableStudent.setModel(tableModel);
//gọi hàm ShowData để đưa dữ liệu vào tableModel
        showDataStudent();
        long millis = System.currentTimeMillis();
        java.sql.Date currentDate = new java.sql.Date(millis);
        txtStuDOB.setDate(currentDate);
        //DBconnect.loadData("SELECT * FROM USERS", jTableUser);
        txtStuID.setText(DBconnect.getID("EXECUTE sp_STUDENTS_identityID"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabelNotify = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtStuID = new javax.swing.JTextField();
        radioGender = new javax.swing.JRadioButton();
        radioGenderFe = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        txtStuFullName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtStuIdCard = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtStuPhone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtStuEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtStuAddress = new javax.swing.JTextArea();
        txtStuDOB = new com.toedter.calendar.JDateChooser();
        btnStuEdit = new javax.swing.JButton();
        btnStuAdd = new javax.swing.JButton();
        btnStuDelete = new javax.swing.JButton();
        btnStuSave = new javax.swing.JButton();
        btnStuReset = new javax.swing.JButton();
        btnStuReport = new javax.swing.JButton();
        btnStuClose = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableStudent = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelNotify.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabelNotify.setText("Notify: Database has been Successfully");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabelNotify)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelNotify)
                .addGap(29, 29, 29))
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel1.setText("Student ID");

        txtStuID.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        radioGender.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        radioGender.setText("Male");

        radioGenderFe.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        radioGenderFe.setText("Female");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel2.setText("Student Name");

        txtStuFullName.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setText("Identity Card");

        txtStuIdCard.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel4.setText("Phone");

        txtStuPhone.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel5.setText("Date of Birth");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel6.setText("Email");

        txtStuEmail.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel7.setText("Address");

        txtStuAddress.setColumns(20);
        txtStuAddress.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtStuAddress.setRows(3);
        jScrollPane2.setViewportView(txtStuAddress);

        btnStuEdit.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnStuEdit.setText("Edit");
        btnStuEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStuEditMouseClicked(evt);
            }
        });

        btnStuAdd.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnStuAdd.setText("Add");
        btnStuAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStuAddMouseClicked(evt);
            }
        });

        btnStuDelete.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnStuDelete.setText("Delete");
        btnStuDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStuDeleteMouseClicked(evt);
            }
        });

        btnStuSave.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnStuSave.setText("Save");
        btnStuSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStuSaveMouseClicked(evt);
            }
        });

        btnStuReset.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnStuReset.setText("Reset");
        btnStuReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStuResetMouseClicked(evt);
            }
        });

        btnStuReport.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnStuReport.setText("Report");
        btnStuReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnStuReportMousePressed(evt);
            }
        });

        btnStuClose.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnStuClose.setText("Close");
        btnStuClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStuCloseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnStuAdd)
                        .addGap(41, 41, 41)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtStuID, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(radioGender)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioGenderFe))
                            .addComponent(txtStuFullName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtStuDOB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtStuIdCard, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                    .addComponent(txtStuPhone))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnStuEdit)
                                .addGap(50, 50, 50)
                                .addComponent(btnStuDelete)
                                .addGap(43, 43, 43)
                                .addComponent(btnStuSave)
                                .addGap(49, 49, 49)
                                .addComponent(btnStuReset)))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnStuReport)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnStuClose)
                                .addGap(55, 55, 55))
                            .addComponent(txtStuEmail)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))))
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtStuID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(radioGender))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(radioGenderFe)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtStuDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStuFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStuEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtStuIdCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(txtStuPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStuEdit)
                    .addComponent(btnStuAdd)
                    .addComponent(btnStuDelete)
                    .addComponent(btnStuSave)
                    .addComponent(btnStuReset)
                    .addComponent(btnStuReport)
                    .addComponent(btnStuClose))
                .addGap(22, 22, 22))
        );

        jTableStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Student ID", "Full name", "Identity Card", "Gender", "DOB", "Phone", "Email", "Address"
            }
        ));
        jTableStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableStudentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableStudent);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableStudentMouseClicked
        // TODO add your handling code here:
        try {
            int row = this.jTableStudent.getSelectedRow();
            TableModel model = jTableStudent.getModel();
            String stuID = (this.jTableStudent.getModel().getValueAt(row, 0)).toString();
            ResultSet rs = stuObj.showIDStudent(stuID);//Goi ham lay du lieu theo ma loai
            String sexStu = (this.jTableStudent.getModel().getValueAt(row, 3)).toString();
            ResultSet rs1 = stuObj.showIDStudent(sexStu);//Goi ham lay du lieu theo ma loai
            if (rs.next()) {
                this.txtStuID.setText(rs.getString("STUDENT_ID"));
                this.txtStuFullName.setText(rs.getString("STUDENT_NAME"));
                this.txtStuIdCard.setText(rs.getString("IDENTITY_CARD"));
                String sex = model.getValueAt(row, 3).toString();
                if (sex.equals("Male") == true || sex.equals("MALE") == true) {
                    this.radioGender.setSelected(true);
                    this.radioGenderFe.setSelected(false);
                } else {
                    this.radioGenderFe.setSelected(true);
                    this.radioGender.setSelected(false);
                }
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                txtStuDOB.setDate(rs.getDate("DOB"));
                this.txtStuPhone.setText(rs.getString("PHONE"));
                this.txtStuEmail.setText(rs.getString("EMAIL"));
                this.txtStuAddress.setText(rs.getString("STUDENT_ADD"));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }//GEN-LAST:event_jTableStudentMouseClicked

    private void btnStuAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStuAddMouseClicked
        // TODO add your handling code here:
        setClockID(true);
        setNull();//Xoa trang TextField
        setClock(false);//Mo khoa TextField
        setButton(false);//Goi ham khoa cac Button
        flag = true;//Gan cothem = true de ghi nhan trang thai them moi
    }//GEN-LAST:event_btnStuAddMouseClicked

    private void btnStuEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStuEditMouseClicked
        // TODO add your handling code here:
        String id = txtStuID.getText();
        if (id.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please choose a id item for edit",
                    "Message", 1);
        } else {
            setClock(false);//Mo khoa cac TextField
            this.txtStuID.enable(false);
            setButton(false); //Khoa cac Button
            flag = false; //Gan cothem=false de ghi nhan trang thai la sua
        }
    }//GEN-LAST:event_btnStuEditMouseClicked

    private void btnStuDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStuDeleteMouseClicked
        // TODO add your handling code here:
        jLabelNotify.setVisible(false);
        String id = txtStuID.getText();
        try {
            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "Choose a ID item for delete",
                        "Message", 1);
            } else {
                if (JOptionPane.showConfirmDialog(null, "Do you want to delete " + id + " this item ? ", "Message", 2) == 0) {
                    stuObj.deleteData(id);//goi ham xoa du lieu theo ma loai
                    clearData();//Xoa du lieu trong tableModel
                    showDataStudent();//Do du lieu vao table Model
                    setNull();//Xoa trang Textfield
                    jLabelNotify.setVisible(true);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fail", "Message", 1);
        }
    }//GEN-LAST:event_btnStuDeleteMouseClicked

    private void btnStuResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStuResetMouseClicked
        // TODO add your handling code here:
        setNull();
        setClock(true);
        setButton(true);
    }//GEN-LAST:event_btnStuResetMouseClicked

    private void btnStuCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStuCloseMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnStuCloseMouseClicked

    private void btnStuSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStuSaveMouseClicked
        // TODO add your handling code here:
        Date dateOfBirth = txtStuDOB.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dob = sdf.format(dateOfBirth);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOfBirth);
        String stuId = txtStuID.getText();
        String fullname = txtStuFullName.getText();
        String idCard = txtStuIdCard.getText();
        String sex = null;
        if (radioGender.isSelected()) {
            sex = "Male";
        } else {
            sex = "Female";
        }
        String phone = txtStuPhone.getText();
        String mail = txtStuEmail.getText();
        String add = txtStuAddress.getText();
        if (fullname.matches("^[a-zA-Z_0-9\\s]{5,40}") == false) {
            JOptionPane.showMessageDialog(null, "Name cannot empty. Name does not contain special characters!", "Message", 1);
            txtStuFullName.requestFocus();
        } else if (idCard.matches("^[0-9]{9,12}") == false) {
            JOptionPane.showMessageDialog(null, "Username must be between 9 and 12 characters. Name does not contain special characters!", "Message", 1);
            txtStuIdCard.requestFocus();
        } else if (add.matches(".{10,100}") == false) {
            JOptionPane.showMessageDialog(null, "Address must be greater than 10 characters and less than 100 characters!", "Message", 1);
            txtStuAddress.requestFocus();
        } else if (phone.matches("^[0-9]{9,13}") == false) {
            JOptionPane.showMessageDialog(null, "Invalid phone number, please try again!", "Message", 1);
            txtStuPhone.requestFocus();
        } else if (mail.matches("[A-Z0-9._%+-][A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{3}") == true) {
            JOptionPane.showMessageDialog(null, "Invalid your email, please try again!", "Message", 1);
            txtStuEmail.requestFocus();
        } else if (getAge(calendar) < 6) {
            JOptionPane.showMessageDialog(null, "must be over 6 years old to register!", "Message", 1);
            txtStuDOB.requestFocus();
        } else {
            try {

                if (flag == true) {
                    stuObj.insertData(stuId, fullname, idCard, sex, dob, phone, mail, add);
                    jLabelNotify.setVisible(true);
                } else {
                    stuObj.editData(stuId, fullname, idCard, sex, dob, phone, mail, add);
                    jLabelNotify.setVisible(true);
                }

                clearData(); //goi ham xoa du lieu tron tableModel
                showDataStudent(); //Do lai du lieu vao Table Model
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Updated successfull",
                        "Message", 1);
            }
            setClock(false);
            setButton(true);
        }
    }//GEN-LAST:event_btnStuSaveMouseClicked

    private void btnStuReportMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStuReportMousePressed
        // TODO add your handling code here:
                //String[] colsName = {"ID Student", "Full name", "Identity Card", "Gender", "DOB", "Phone", "Email", "Address"};
// đặt tiêu đề cột cho tableModel
        //tableModel.setColumnIdentifiers(colsName);
        //jTableStudent.setModel(tableModel);
        ReportStudent.ExportExcel(jTableStudent);
 
    }//GEN-LAST:event_btnStuReportMousePressed

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
            java.util.logging.Logger.getLogger(StudentJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new StudentJFrame().setVisible(true);
            } catch (SQLException e) {
                e.getMessage();
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStuAdd;
    private javax.swing.JButton btnStuClose;
    private javax.swing.JButton btnStuDelete;
    private javax.swing.JButton btnStuEdit;
    private javax.swing.JButton btnStuReport;
    private javax.swing.JButton btnStuReset;
    private javax.swing.JButton btnStuSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelNotify;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableStudent;
    private javax.swing.JRadioButton radioGender;
    private javax.swing.JRadioButton radioGenderFe;
    private javax.swing.JTextArea txtStuAddress;
    private com.toedter.calendar.JDateChooser txtStuDOB;
    private javax.swing.JTextField txtStuEmail;
    private javax.swing.JTextField txtStuFullName;
    private javax.swing.JTextField txtStuID;
    private javax.swing.JTextField txtStuIdCard;
    private javax.swing.JTextField txtStuPhone;
    // End of variables declaration//GEN-END:variables
}
