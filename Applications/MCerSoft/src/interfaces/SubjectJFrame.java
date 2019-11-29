/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import aplication.ReportSubjects;
import aplication.Subjects;
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
public class SubjectJFrame extends javax.swing.JFrame {

    private final Subjects subObj = new Subjects();
    private boolean flag = true;
    private final DefaultTableModel tableModel = new DefaultTableModel();

    private void showDataSub() throws SQLException {
        ResultSet result = subObj.showSubject();
        try {
            while (result.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[4];
                rows[0] = result.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã so)
                rows[1] = result.getString(2);
                rows[2] = result.getString(3);
                rows[3] = result.getString(4);
                tableModel.addRow(rows); // đưa dòng dữ liệu vào tableModel
            }
        } catch (SQLException e) {
        }
    }
//Ham xoa du lieu trong tableModel

    private void clearData() throws SQLException {
//Lay chi so dong cuoi cung
        int n = tableModel.getRowCount() - 1;
        for (int i = n; i >= 0; i--) {
            tableModel.removeRow(i);//Remove tung dong
        }
    }

    private void loadCertificate() {
        cbtCerID.removeAllItems();
        DBconnect.loadDataCombobox("SELECT * FROM CERTIFICATED ", this.cbtCerID, "CERTIFICATE_ID");
        String query = "SELECT * FROM CERTIFICATED WHERE CERTIFICATE_ID = ?";
        DBconnect.loadDataTextfield(query, this.cbtCerID, this.txtCerName, "CERTIFICATE_NAME");
    }

    private void setNull() {
//Xoa trang cac JtextField

        this.txtCerName.setText(null);
        //this.txtSubID.setText(null);
        this.txtSubName.setText(null);
        this.txtSubName.requestFocus();
    }
//Ham khoa cac TextField

    private void setClockID(boolean a) {
//Khoa hoac mo khoa cho Cac JTextField
        this.txtSubID.setEnabled(!a);
    }
    private void setClockNameCer(boolean a) {
//Khoa hoac mo khoa cho Cac JTextField
        this.txtCerName.setEnabled(!a);
    }
    private void setClock(boolean a) {
//Khoa hoac mo khoa cho Cac JTextField
        //this.txtCerName.setEnabled(!a);
        this.txtSubName.setEnabled(!a);
        //this.txtSubID.setEnabled(!a);
    }
//Ham khoa cac Button

    private void setButton(boolean a) {
//Vo hieu hoac co hieu luc cho cac JButton
        this.btnAddSubject.setEnabled(a);
        this.btnDeleteSubject.setEnabled(a);
        this.btnEditSubject.setEnabled(a);
        this.btnSaveSubject.setEnabled(!a);
        this.btnReportSubject.setEnabled(!a);
        this.btnResetSubject.setEnabled(!a);
        this.btnCloseSubject.setEnabled(a);
    }

    private void disableForm() {

        this.txtCerName.setEditable(false);
        this.txtSubID.setEditable(false);
        this.txtSubName.setEditable(false);
    }

    private void enableForm() {
        this.txtCerName.setEditable(true);
        this.txtSubID.setEditable(true);
        this.txtSubName.setEditable(true);
    }

    /**
     * Creates new form SubjectJFrame
     */
    public SubjectJFrame() throws SQLException {
        initComponents();
        jLabelNotify.setVisible(false);
        String[] colsName = {"SUBJECT ID", "SUBJECT NAME", "CERTIFICATE ID", "CERTIFICATE NAME"};
// đặt tiêu đề cột cho tableModel
        tableModel.setColumnIdentifiers(colsName);
// kết nối jtable với tableModel
        jTableSubject.setModel(tableModel);
//gọi hàm ShowData để đưa dữ liệu vào tableModel
        showDataSub();
        txtSubID.setText(DBconnect.getID("EXECUTE sp_DETAIL_CERTIFICATE_identityID"));
        loadCertificate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAddSubject6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabelNotify = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnDeleteSubject = new javax.swing.JButton();
        txtSubID = new javax.swing.JTextField();
        btnSaveSubject = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnResetSubject = new javax.swing.JButton();
        txtSubName = new javax.swing.JTextField();
        btnEditSubject = new javax.swing.JButton();
        cbtCerID = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btnCloseSubject = new javax.swing.JButton();
        txtCerName = new javax.swing.JTextField();
        btnAddSubject = new javax.swing.JButton();
        btnReportSubject = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSubject = new javax.swing.JTable();

        btnAddSubject6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnAddSubject6.setText("Add");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelNotify.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabelNotify.setText("Notify: Database has been Successfully");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNotify)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabelNotify)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btnDeleteSubject.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnDeleteSubject.setText("Delete");
        btnDeleteSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteSubjectMouseClicked(evt);
            }
        });

        btnSaveSubject.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnSaveSubject.setText("Save");
        btnSaveSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveSubjectMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel2.setText("Subject Name");

        btnResetSubject.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnResetSubject.setText("Reset");
        btnResetSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetSubjectMouseClicked(evt);
            }
        });

        btnEditSubject.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnEditSubject.setText("Edit");
        btnEditSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditSubjectMouseClicked(evt);
            }
        });

        cbtCerID.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cbtCerID.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbtCerIDPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel3.setText("Certificate");

        btnCloseSubject.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnCloseSubject.setText("Close");
        btnCloseSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseSubjectMouseClicked(evt);
            }
        });

        btnAddSubject.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnAddSubject.setText("Add");
        btnAddSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddSubjectMouseClicked(evt);
            }
        });

        btnReportSubject.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnReportSubject.setText("Report");
        btnReportSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnReportSubjectMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel1.setText("Subject ID");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1)
                        .addGap(86, 86, 86)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSubID, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSubName, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAddSubject)
                                .addGap(34, 34, 34)
                                .addComponent(btnEditSubject))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnDeleteSubject)
                                .addGap(37, 37, 37)
                                .addComponent(btnSaveSubject))
                            .addComponent(cbtCerID, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtCerName, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnResetSubject)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnReportSubject)
                                .addGap(37, 37, 37)
                                .addComponent(btnCloseSubject)
                                .addGap(55, 55, 55))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtSubID, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSubName, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCerName, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(cbtCerID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddSubject)
                    .addComponent(btnEditSubject)
                    .addComponent(btnCloseSubject)
                    .addComponent(btnReportSubject)
                    .addComponent(btnResetSubject)
                    .addComponent(btnDeleteSubject)
                    .addComponent(btnSaveSubject))
                .addContainerGap())
        );

        jTableSubject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSubjectMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableSubject);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbtCerIDPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbtCerIDPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        String query = "SELECT * FROM CERTIFICATED WHERE CERTIFICATE_ID = ?";
        DBconnect.loadDataTextfield(query, this.cbtCerID, this.txtCerName, "CERTIFICATE_NAME");
    }//GEN-LAST:event_cbtCerIDPopupMenuWillBecomeInvisible

    private void jTableSubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSubjectMouseClicked
        // TODO add your handling code here:
        //setClockNameCer(true);
        try {
            //Lay chi so dong dang chon
            int row = this.jTableSubject.getSelectedRow();
            TableModel model = jTableSubject.getModel();
            String subID = (this.jTableSubject.getModel().getValueAt(row, 0)).toString();
            ResultSet rs = subObj.showIDSub(subID);//Goi ham lay du lieu theo ma loai
            if (rs.next()) {
                this.txtSubID.setText(rs.getString("SUBJECT_ID"));
                this.txtSubName.setText(rs.getString("SUBJECT_NAME"));
                this.cbtCerID.setSelectedItem(rs.getString("CERTIFICATE_ID"));
                this.txtCerName.setText(rs.getString("CERTIFICATE_NAME"));
            }
            String query = "SELECT * FROM CERTIFICATED WHERE CERTIFICATE_ID = ?";
            DBconnect.loadDataTextfield(query, this.cbtCerID, this.txtCerName, "CERTIFICATE_NAME");
        } catch (SQLException e) {
        }
    }//GEN-LAST:event_jTableSubjectMouseClicked

    private void btnAddSubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddSubjectMouseClicked
        // TODO add your handling code here:
        setClockID(true);
        setClockNameCer(true);
        setNull();//Xoa trang TextField
        setClock(false);//Mo khoa TextField
        setButton(false);//Goi ham khoa cac Button
        flag = true;//Gan cothem = true de ghi nhan trang thai them moi
    }//GEN-LAST:event_btnAddSubjectMouseClicked

    private void btnEditSubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditSubjectMouseClicked
        // TODO add your handling code here:
        setClockNameCer(true);
        String id = txtSubID.getText();
        //String name = txtCerName.getText();
        if (id.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please choose a id item for edit",
                    "Message", 1);
        } else {

            setClock(false);//Mo khoa cac TextField
            this.txtSubID.enable(false);
            setButton(false); //Khoa cac Button
            flag = false; //Gan cothem=false de ghi nhan trang thai la sua
        }
    }//GEN-LAST:event_btnEditSubjectMouseClicked

    private void btnCloseSubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseSubjectMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCloseSubjectMouseClicked

    private void btnResetSubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetSubjectMouseClicked
        // TODO add your handling code here:
        setNull();
        setClock(true);
        setButton(true);
    }//GEN-LAST:event_btnResetSubjectMouseClicked

    private void btnDeleteSubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteSubjectMouseClicked
        // TODO add your handling code here:
        jLabelNotify.setVisible(false);
        setClockNameCer(true);
        String id = txtSubID.getText();
        try {
            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "Choose a ID item for delete",
                        "Message", 1);
            } else {
                if (JOptionPane.showConfirmDialog(null, "Do you want to delete " + id + " this item ? ", "Message", 2) == 0) {
                    subObj.deleteData(id);//goi ham xoa du lieu theo ma loai
                    clearData();//Xoa du lieu trong tableModel
                    showDataSub();//Do du lieu vao table Model
                    setNull();//Xoa trang Textfield
                    jLabelNotify.setVisible(true);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fail", "Message", 1);
        }
    }//GEN-LAST:event_btnDeleteSubjectMouseClicked

    private void btnSaveSubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveSubjectMouseClicked
        // TODO add your handling code here:
        String id = txtSubID.getText();
        String name = txtSubName.getText();
        String idCer = cbtCerID.getSelectedItem().toString();
        if (idCer.length() == 0) {
            JOptionPane.showMessageDialog(null, "You must choose certificate information first!", "Message", 1);
        } else if (name.matches(".{10,100}") == false) {
            JOptionPane.showMessageDialog(null, "Subject name must be between 10 and 100 characters!", "Message", 1);
        } else {
            try {
                if (flag == true) {
                    subObj.insertData(id, name, idCer);
                    jLabelNotify.setVisible(true);
                } else {
                    subObj.editData(id, name, idCer);
                    jLabelNotify.setVisible(true);
                }
                clearData(); //goi ham xoa du lieu tron tableModel
                showDataSub(); //Do lai du lieu vao Table Model
            } catch (SQLException e) {
                e.getMessage();
            }
            setClock(false);
            setButton(true);
        }
    }//GEN-LAST:event_btnSaveSubjectMouseClicked

    private void btnReportSubjectMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportSubjectMousePressed
        // TODO add your handling code here:
        ReportSubjects.ExportExcel(jTableSubject);        
    }//GEN-LAST:event_btnReportSubjectMousePressed

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
            java.util.logging.Logger.getLogger(SubjectJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SubjectJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SubjectJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SubjectJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new SubjectJFrame().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(SubjectJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddSubject;
    private javax.swing.JButton btnAddSubject6;
    private javax.swing.JButton btnCloseSubject;
    private javax.swing.JButton btnDeleteSubject;
    private javax.swing.JButton btnEditSubject;
    private javax.swing.JButton btnReportSubject;
    private javax.swing.JButton btnResetSubject;
    private javax.swing.JButton btnSaveSubject;
    private javax.swing.JComboBox<String> cbtCerID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelNotify;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableSubject;
    private javax.swing.JTextField txtCerName;
    private javax.swing.JTextField txtSubID;
    private javax.swing.JTextField txtSubName;
    // End of variables declaration//GEN-END:variables
}