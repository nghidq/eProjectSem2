/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import aplication.CerManager;
import aplication.ReportCerManager;
import database.DBconnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author David Nghi
 */
public class ManagerCERJFrame extends javax.swing.JFrame {

    /**
     * Creates new form ManagerCERJFrame
     */
    private final CerManager cerManObj = new CerManager();
    private boolean flag = true;
    private final DefaultTableModel tableModelCerMan = new DefaultTableModel();

    private void showDataCerMan() throws SQLException {
        ResultSet result = cerManObj.showCerManager();
        try {
            while (result.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[5];
                rows[0] = result.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã so)
                rows[1] = result.getString(2);
                rows[2] = result.getString(3);
                rows[3] = result.getString(4);
                rows[4] = result.getString(5);
                tableModelCerMan.addRow(rows); // đưa dòng dữ liệu vào tableModel
            }
        } catch (SQLException e) {
        }
    }

    private void loadStudentCERManager() {
        cbtCerManagerStu.removeAllItems();
        DBconnect.loadDataCombobox("SELECT * FROM STUDENT_CER A, STUDENTS B "
                + "WHERE A.STUDENT_ID = B.STUDENT_ID", this.cbtCerManagerStu, "STUDENT_ID");
        String query = "SELECT * FROM STUDENTS WHERE STUDENT_ID = ?";
        DBconnect.loadDataTextfield(query, this.cbtCerManagerStu, this.txtManagerCerStuName, "STUDENT_NAME");
    }

    private void loadCertificateCERManager() {
        cbtCerManagerCer.removeAllItems();
        DBconnect.loadDataCombobox("SELECT * FROM STUDENT_CER A, CERTIFICATED D "
                + "WHERE A.CERTIFICATE_ID = D.CERTIFICATE_ID ", this.cbtCerManagerCer, "ID_NUM");
        String query = "SELECT * FROM STUDENT_CER WHERE ID_NUM = ?";
        DBconnect.loadDataTextfield(query, this.cbtCerManagerCer, this.txtManagerCerName, "CERTIFICATE_ID");
    }
//Ham xoa du lieu trong tableModel

    private void clearDataCerMan() throws SQLException {
//Lay chi so dong cuoi cung
        int n = tableModelCerMan.getRowCount() - 1;
        for (int i = n; i >= 0; i--) {
            tableModelCerMan.removeRow(i);//Remove tung dong
        }
    }

    private void setNullCerMan() {
//Xoa trang cac JtextField
        this.txtManagerCerStuName.setText(null);
        this.txtManagerCerName.setText(null);
    }

    private void setClockNoID(boolean a) {
//Khoa hoac mo khoa cho Cac JTextField
        this.txtNoId.setEnabled(!a);

    }

    private void setClockCerMan(boolean a) {
//Khoa hoac mo khoa cho Cac JTextField
        this.cbtCerManagerStu.setEnabled(!a);
        this.txtManagerCerStuName.setEnabled(!a);
        this.cbtCerManagerCer.setEnabled(!a);
        this.txtManagerCerName.setEnabled(!a);
    }
//Ham khoa cac Button

    private void setButtonCerMan(boolean a) {
//Vo hieu hoac co hieu luc cho cac JButton
        this.btnCerManagerAdd.setEnabled(a);
        this.btnCerManagerDelete.setEnabled(a);
        this.btnCerManagerEdit.setEnabled(a);
        this.btnCerManagerSave.setEnabled(!a);
        this.btnCerManagerReset.setEnabled(!a);
        this.btnCerManagerReport.setEnabled(!a);
        this.btnCerManagerClose.setEnabled(a);
    }

    private void disableForm() {

        txtManagerCerStuName.setEditable(false);
        cbtCerManagerStu.setEnabled(false);
        txtManagerCerName.setEditable(false);
        cbtCerManagerCer.setEditable(false);
        txtNoId.setEnabled(false);

    }

    private void enableForm() {
        txtManagerCerStuName.setEditable(true);
        cbtCerManagerStu.setEnabled(true);
        txtManagerCerName.setEditable(true);
        cbtCerManagerCer.setEditable(true);
        txtNoId.setEnabled(true);
    }

    public ManagerCERJFrame() throws SQLException {
        initComponents();
        jLabelNotify.setVisible(false);
        String[] colsName = {"Identity No", "ID Student", "Full name", "ID Number", "Certificate name"};
// đặt tiêu đề cột cho tableModel
        tableModelCerMan.setColumnIdentifiers(colsName);
// kết nối jtable với tableModel
        jTableCerManager.setModel(tableModelCerMan);
//gọi hàm ShowData để đưa dữ liệu vào tableModel
        showDataCerMan();
        txtNoId.setText(DBconnect.getID("EXECUTE sp_CER_MANAGER_identityID"));
        loadStudentCERManager();
        loadCertificateCERManager();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelNotify = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNoId = new javax.swing.JTextField();
        cbtCerManagerStu = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbtCerManagerCer = new javax.swing.JComboBox<>();
        txtManagerCerStuName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtManagerCerName = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnCerManagerAdd = new javax.swing.JButton();
        btnCerManagerEdit = new javax.swing.JButton();
        btnCerManagerDelete = new javax.swing.JButton();
        btnCerManagerSave = new javax.swing.JButton();
        btnCerManagerReset = new javax.swing.JButton();
        btnCerManagerReport = new javax.swing.JButton();
        btnCerManagerClose = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableCerManager = new javax.swing.JTable();

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
        jLabel1.setText("No. Certificate");

        txtNoId.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        cbtCerManagerStu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cbtCerManagerStu.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbtCerManagerStuPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel2.setText("Student ID");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setText("Student Name");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel4.setText("ID Number");

        cbtCerManagerCer.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cbtCerManagerCer.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbtCerManagerCerPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txtManagerCerStuName.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel5.setText("Certificate Name");

        txtManagerCerName.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(97, 97, 97)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addGap(50, 50, 50)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtManagerCerStuName, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNoId, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbtCerManagerStu, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4))
                            .addGap(34, 34, 34)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtManagerCerName, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbtCerManagerCer, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtNoId, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbtCerManagerStu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtManagerCerStuName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(cbtCerManagerCer, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(txtManagerCerName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(31, 31, 31)))
        );

        btnCerManagerAdd.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnCerManagerAdd.setText("Add");
        btnCerManagerAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerManagerAddMouseClicked(evt);
            }
        });

        btnCerManagerEdit.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnCerManagerEdit.setText("Edit");
        btnCerManagerEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerManagerEditMouseClicked(evt);
            }
        });

        btnCerManagerDelete.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnCerManagerDelete.setText("Delete");
        btnCerManagerDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerManagerDeleteMouseClicked(evt);
            }
        });

        btnCerManagerSave.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnCerManagerSave.setText("Save");
        btnCerManagerSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerManagerSaveMouseClicked(evt);
            }
        });

        btnCerManagerReset.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnCerManagerReset.setText("Reset");
        btnCerManagerReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerManagerResetMouseClicked(evt);
            }
        });

        btnCerManagerReport.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnCerManagerReport.setText("Report");
        btnCerManagerReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCerManagerReportMousePressed(evt);
            }
        });

        btnCerManagerClose.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnCerManagerClose.setText("Close");
        btnCerManagerClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerManagerCloseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCerManagerClose, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCerManagerReport)
                            .addComponent(btnCerManagerReset, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCerManagerSave, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCerManagerAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerManagerDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(btnCerManagerEdit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCerManagerEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnCerManagerDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnCerManagerAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerManagerSave, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerManagerReset, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerManagerReport, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerManagerClose, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTableCerManager.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NO_ID", "STUDENT_ID", "STUDENT_NAME", "ID_NUM", "CERTIFICATE"
            }
        ));
        jTableCerManager.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCerManagerMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableCerManager);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbtCerManagerStuPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbtCerManagerStuPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        String query = "SELECT * FROM STUDENTS WHERE STUDENT_ID = ?";
        DBconnect.loadDataTextfield(query, this.cbtCerManagerStu, this.txtManagerCerStuName, "STUDENT_NAME");
    }//GEN-LAST:event_cbtCerManagerStuPopupMenuWillBecomeInvisible

    private void cbtCerManagerCerPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbtCerManagerCerPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        String query = "SELECT * FROM STUDENT_CER WHERE ID_NUM = ?";
        DBconnect.loadDataTextfield(query, this.cbtCerManagerCer, this.txtManagerCerName, "CERTIFICATE_ID");
    }//GEN-LAST:event_cbtCerManagerCerPopupMenuWillBecomeInvisible

    private void jTableCerManagerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCerManagerMouseClicked
        // TODO add your handling code here:
        try {
            //Lay chi so dong dang chon
            int row = this.jTableCerManager.getSelectedRow();
            TableModel model = jTableCerManager.getModel();
            String noID = (this.jTableCerManager.getModel().getValueAt(row, 0)).toString();
            ResultSet rs = cerManObj.showIDCer(noID);//Goi ham lay du lieu theo ma loai
            if (rs.next()) {
                this.txtNoId.setText(rs.getString("NO_ID"));
                this.cbtCerManagerStu.setSelectedItem(rs.getString("STUDENT_ID"));
                this.txtManagerCerStuName.setText(rs.getString("STUDENT_NAME"));
                this.cbtCerManagerCer.setSelectedItem(rs.getString("ID_NUM"));
                this.txtManagerCerName.setText(rs.getString("CERTIFICATE_NAME"));
            }
            String queryStu = "SELECT * FROM STUDENTS WHERE STUDENT_ID = ?";
            DBconnect.loadDataTextfield(queryStu, this.cbtCerManagerStu, this.txtManagerCerStuName, "STUDENT_NAME");
            String queryCer = "SELECT * FROM STUDENT_CER WHERE ID_NUM = ?";
            DBconnect.loadDataTextfield(queryCer, this.cbtCerManagerCer, this.txtManagerCerName, "CERTIFICATE_ID");
        } catch (SQLException e) {
        }
    }//GEN-LAST:event_jTableCerManagerMouseClicked

    private void btnCerManagerAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerManagerAddMouseClicked
        // TODO add your handling code here:
        setClockNoID(true);
        setNullCerMan();//Xoa trang TextField
        setClockCerMan(false);//Mo khoa TextField
        setButtonCerMan(false);//Goi ham khoa cac Button
        flag = true;//Gan cothem = true de ghi nhan trang thai them moi
    }//GEN-LAST:event_btnCerManagerAddMouseClicked

    private void btnCerManagerDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerManagerDeleteMouseClicked
        // TODO add your handling code here:
        jLabelNotify.setVisible(false);
        setClockNoID(true);
        String id = txtNoId.getText();
        try {
            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "Choose a ID item for delete",
                        "Message", 1);
            } else {
                if (JOptionPane.showConfirmDialog(null, "Do you want to delete " + id + " this item ? ", "Message", 2) == 0) {
                    cerManObj.deleteDataCerMan(id);//goi ham xoa du lieu theo ma loai
                    clearDataCerMan();//Xoa du lieu trong tableModel
                    showDataCerMan();//Do du lieu vao table Model
                    setNullCerMan();//Xoa trang Textfield
                    jLabelNotify.setVisible(true);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fail", "Message", 1);
        }
    }//GEN-LAST:event_btnCerManagerDeleteMouseClicked

    private void btnCerManagerCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerManagerCloseMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCerManagerCloseMouseClicked

    private void btnCerManagerResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerManagerResetMouseClicked
        // TODO add your handling code here:
        setNullCerMan();
        setClockCerMan(true);
        setButtonCerMan(true);
    }//GEN-LAST:event_btnCerManagerResetMouseClicked

    private void btnCerManagerEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerManagerEditMouseClicked
        // TODO add your handling code here:
        setClockNoID(true);
        
        String id = txtNoId.getText();
        if (id.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please choose a id item for edit",
                    "Message", 1);
        } else {

            setClockCerMan(false);//Mo khoa cac TextField
            this.txtManagerCerStuName.enable(false);
            this.txtManagerCerName.enable(false);
            this.txtNoId.enable(false);
            setButtonCerMan(false); //Khoa cac Button
            flag = false; //Gan flag=false de ghi nhan trang thai la sua
        }
    }//GEN-LAST:event_btnCerManagerEditMouseClicked
//txtNoId, cbtCerManagerStu,txtManagerCerStuName, cbtCerManagerCer,txtManagerCerName
    //btnCerManagerAdd, btnCerManagerDelete, btnCerManagerEdit, btnCerManagerSave, btnCerManagerReset, btnCerManagerReport, btnCerManagerClose      
//cerManObj
    private void btnCerManagerSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerManagerSaveMouseClicked
        // TODO add your handling code here:
        String id = txtNoId.getText();
        //String name = txtSubName.getText();
        String idStu = cbtCerManagerStu.getSelectedItem().toString();
        String idCer = cbtCerManagerCer.getSelectedItem().toString();
        if (idStu.length() == 0) {
            JOptionPane.showMessageDialog(null, "You must choose student information first!", "Message", 1);
        }else if (idCer.length() == 0) {
            JOptionPane.showMessageDialog(null, "You must choose identity number of student first!", "Message", 1);
        }else {
            try {
                if (flag == true) {
                    cerManObj.insertDataCerMan(id, idStu, idCer);
                    jLabelNotify.setVisible(true);
                } else {
                    cerManObj.editDataCerMan(id, idStu, idCer);
                    jLabelNotify.setVisible(true);
                }
                clearDataCerMan(); //goi ham xoa du lieu tron tableModel
                showDataCerMan(); //Do lai du lieu vao Table Model
            } catch (SQLException e) {
                e.getMessage();
            }
            setClockCerMan(false);
            setButtonCerMan(true);
        }
    }//GEN-LAST:event_btnCerManagerSaveMouseClicked

    private void btnCerManagerReportMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerManagerReportMousePressed
        // TODO add your handling code here:
        ReportCerManager.ExportExcel(jTableCerManager);
    }//GEN-LAST:event_btnCerManagerReportMousePressed

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
            java.util.logging.Logger.getLogger(ManagerCERJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerCERJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerCERJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerCERJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new ManagerCERJFrame().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(ManagerCERJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerManagerAdd;
    private javax.swing.JButton btnCerManagerClose;
    private javax.swing.JButton btnCerManagerDelete;
    private javax.swing.JButton btnCerManagerEdit;
    private javax.swing.JButton btnCerManagerReport;
    private javax.swing.JButton btnCerManagerReset;
    private javax.swing.JButton btnCerManagerSave;
    private javax.swing.JComboBox<String> cbtCerManagerCer;
    private javax.swing.JComboBox<String> cbtCerManagerStu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelNotify;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableCerManager;
    private javax.swing.JTextField txtManagerCerName;
    private javax.swing.JTextField txtManagerCerStuName;
    private javax.swing.JTextField txtNoId;
    // End of variables declaration//GEN-END:variables
}
