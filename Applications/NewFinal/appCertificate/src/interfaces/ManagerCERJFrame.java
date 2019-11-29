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
import java.sql.SQLIntegrityConstraintViolationException;
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
        DBconnect.loadDataCombobox("SELECT A.STUDENT_ID, B.STUDENT_NAME FROM STUDENT_CER A, STUDENTS B\n"
                + " WHERE A.STUDENT_ID = B.STUDENT_ID\n"
                + " GROUP BY A.STUDENT_ID, B.STUDENT_NAME ", this.cbtCerManagerStu, "STUDENT_ID");
        String query = "SELECT A.STUDENT_ID, B.STUDENT_NAME FROM STUDENT_CER A, STUDENTS B\n"
                + " WHERE A.STUDENT_ID = B.STUDENT_ID AND B.STUDENT_ID = ?\n"
                + " GROUP BY A.STUDENT_ID, B.STUDENT_NAME";
        DBconnect.loadDataTextfield(query, this.cbtCerManagerStu, this.txtManagerCerStuName, "STUDENT_ID");
    }

    private void loadCertificateCERManager() {
        cbtCerManagerCer.removeAllItems();
        DBconnect.loadDataCombobox("SELECT A.ID_NUM, B.CERTIFICATE_NAME FROM STUDENT_CER A, CERTIFICATED B\n"
                + " WHERE A.CERTIFICATE_ID = B.CERTIFICATE_ID AND A.RESULT IS NOT NULL\n"
                + " GROUP BY A.ID_NUM, B.CERTIFICATE_NAME ", this.cbtCerManagerCer, "ID_NUM");
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNoId = new javax.swing.JTextField();
        cbtCerManagerStu = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbtCerManagerCer = new javax.swing.JComboBox<>();
        txtManagerCerStuName = new javax.swing.JTextField();
        txtManagerCerName = new javax.swing.JTextField();
        btnCerManagerAdd = new javax.swing.JButton();
        btnCerManagerDelete = new javax.swing.JButton();
        btnCerManagerEdit = new javax.swing.JButton();
        btnCerManagerSave = new javax.swing.JButton();
        btnCerManagerReset = new javax.swing.JButton();
        btnCerManagerReport = new javax.swing.JButton();
        btnCerManagerClose = new javax.swing.JButton();
        jLabelNotify = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableCerManager = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 61, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/certificateid.png"))); // NOI18N
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
        cbtCerManagerStu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbtCerManagerStuActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/idstudent.png"))); // NOI18N
        jLabel2.setText("Student ID");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fullname.png"))); // NOI18N
        jLabel3.setText("Student Name");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iduser.png"))); // NOI18N
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

        txtManagerCerName.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        btnCerManagerAdd.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCerManagerAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        btnCerManagerAdd.setText("Add");
        btnCerManagerAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerManagerAddMouseClicked(evt);
            }
        });

        btnCerManagerDelete.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCerManagerDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnCerManagerDelete.setText("Delete");
        btnCerManagerDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerManagerDeleteMouseClicked(evt);
            }
        });

        btnCerManagerEdit.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCerManagerEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        btnCerManagerEdit.setText("Edit");
        btnCerManagerEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerManagerEditMouseClicked(evt);
            }
        });

        btnCerManagerSave.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCerManagerSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png"))); // NOI18N
        btnCerManagerSave.setText("Save");
        btnCerManagerSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerManagerSaveMouseClicked(evt);
            }
        });

        btnCerManagerReset.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCerManagerReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reset1.png"))); // NOI18N
        btnCerManagerReset.setText("Reset");
        btnCerManagerReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerManagerResetMouseClicked(evt);
            }
        });

        btnCerManagerReport.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCerManagerReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/report.png"))); // NOI18N
        btnCerManagerReport.setText("Report");
        btnCerManagerReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCerManagerReportMousePressed(evt);
            }
        });

        btnCerManagerClose.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCerManagerClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/close.png"))); // NOI18N
        btnCerManagerClose.setText("Close");
        btnCerManagerClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerManagerCloseMouseClicked(evt);
            }
        });

        jLabelNotify.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabelNotify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/notify.png"))); // NOI18N
        jLabelNotify.setText("Notify: Database has been Successfully");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabelNotify)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnCerManagerDelete)
                                .addGap(40, 40, 40)
                                .addComponent(btnCerManagerSave)
                                .addGap(48, 48, 48)
                                .addComponent(btnCerManagerReset)
                                .addGap(30, 30, 30)
                                .addComponent(btnCerManagerReport, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCerManagerClose))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbtCerManagerStu, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(cbtCerManagerCer, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(31, 31, 31)
                                    .addComponent(txtManagerCerName))
                                .addComponent(txtNoId, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtManagerCerStuName, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnCerManagerAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCerManagerEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNoId, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelNotify)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtManagerCerName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbtCerManagerCer, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbtCerManagerStu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtManagerCerStuName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerManagerReport, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerManagerClose, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerManagerEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerManagerDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerManagerAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerManagerSave, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerManagerReset, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbtCerManagerStuPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbtCerManagerStuPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        String queryStu = "SELECT A.STUDENT_ID, B.STUDENT_NAME FROM STUDENT_CER A, STUDENTS B\n"
                + " WHERE A.STUDENT_ID = B.STUDENT_ID AND B.STUDENT_ID = ?\n"
                + " GROUP BY A.STUDENT_ID, B.STUDENT_NAME";
        DBconnect.loadDataTextfield(queryStu, this.cbtCerManagerStu, this.txtManagerCerStuName, "STUDENT_NAME");
    }//GEN-LAST:event_cbtCerManagerStuPopupMenuWillBecomeInvisible

    private void cbtCerManagerCerPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbtCerManagerCerPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        String query = "SELECT A.ID_NUM, B.CERTIFICATE_NAME FROM STUDENT_CER A, CERTIFICATED B\n"
                + " WHERE A.CERTIFICATE_ID = B.CERTIFICATE_ID AND A.RESULT IS NOT NULL AND ID_NUM = ?\n"
                + "GROUP BY A.ID_NUM, B.CERTIFICATE_NAME";
        DBconnect.loadDataTextfield(query, this.cbtCerManagerCer, this.txtManagerCerName, "CERTIFICATE_NAME");
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
            String queryStu = "SELECT A.STUDENT_ID, B.STUDENT_NAME FROM STUDENT_CER A, STUDENTS B\n"
                    + " WHERE A.STUDENT_ID = B.STUDENT_ID AND B.STUDENT_ID = ?\n"
                    + " GROUP BY A.STUDENT_ID, B.STUDENT_NAME";
            DBconnect.loadDataTextfield(queryStu, this.cbtCerManagerStu, this.txtManagerCerStuName, "STUDENT_NAME");
            String queryCer = "SELECT A.ID_NUM, B.CERTIFICATE_NAME FROM STUDENT_CER A, CERTIFICATED B\n"
                    + " WHERE A.CERTIFICATE_ID = B.CERTIFICATE_ID AND A.RESULT IS NOT NULL AND ID_NUM = ?\n"
                    + "GROUP BY A.ID_NUM, B.CERTIFICATE_NAME";
            DBconnect.loadDataTextfield(queryCer, this.cbtCerManagerCer, this.txtManagerCerName, "CERTIFICATE_NAME");
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
        //this.cbtCerManagerStu.setEnabled(false);
        this.txtManagerCerStuName.setEnabled(false);
        this.txtManagerCerName.setEnabled(false);

        txtNoId.setText(DBconnect.getID("EXECUTE sp_CER_MANAGER_identityID"));
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
        new HomeJFrame().setVisible(true);
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
    public static final int UNIQUE_CONSTRAINT_VIOLATION = 1;
    private void btnCerManagerSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerManagerSaveMouseClicked
        // TODO add your handling code here:
        String id = txtNoId.getText();
        //String name = txtSubName.getText();
        String idStu = cbtCerManagerStu.getSelectedItem().toString();
        String idCer = cbtCerManagerCer.getSelectedItem().toString();
        if (id.length() == 0) {
            JOptionPane.showMessageDialog(null, "You must choose information first!", "Message", 1);
        } else if (idStu.length() == 0) {
            JOptionPane.showMessageDialog(null, "You must choose information first!", "Message", 1);
        } else if (idCer.length() == 0) {
            JOptionPane.showMessageDialog(null, "You must choose identity number of student first!", "Message", 1);
        } else {
            try {
                if (flag == true) {
                    cerManObj.insertDataCerMan(id, idCer);
                    jLabelNotify.setVisible(true);
                } else {
                    cerManObj.editDataCerMan(id, idCer);
                    jLabelNotify.setVisible(true);
                }
                clearDataCerMan(); //goi ham xoa du lieu tron tableModel
                showDataCerMan(); //Do lai du lieu vao Table Model

            } catch (java.sql.SQLIntegrityConstraintViolationException e) {
                System.out.println("Caught java.sql.SQLIntegrityConstraintViolationException");
            } catch (SQLException e) {
                if(e.getErrorCode() == 8115){
                    JOptionPane.showMessageDialog(null, "first!", "Message", 1);
                }else{
                    JOptionPane.showMessageDialog(null, "ffft!", "Message", 1);
                }
            }
            setClockCerMan(false);
            setButtonCerMan(true);
        }
    }//GEN-LAST:event_btnCerManagerSaveMouseClicked

    private void btnCerManagerReportMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerManagerReportMousePressed
        // TODO add your handling code here:
        ReportCerManager.ExportExcel(jTableCerManager);
    }//GEN-LAST:event_btnCerManagerReportMousePressed

    private void cbtCerManagerStuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbtCerManagerStuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbtCerManagerStuActionPerformed

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
    private javax.swing.JLabel jLabelNotify;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableCerManager;
    private javax.swing.JTextField txtManagerCerName;
    private javax.swing.JTextField txtManagerCerStuName;
    private javax.swing.JTextField txtNoId;
    // End of variables declaration//GEN-END:variables
}
