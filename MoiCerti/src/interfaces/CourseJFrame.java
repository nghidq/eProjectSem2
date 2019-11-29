/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import aplication.Courses;
import aplication.ReportCourse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author David Nghi
 */
public class CourseJFrame extends javax.swing.JFrame {

    /**
     * Creates new form CourseJFrame
     */
    private final Courses courseObj = new Courses();
    private boolean flag = true;
    private final DefaultTableModel tableModel = new DefaultTableModel();
    
    private void showDataCourse() throws SQLException {
        ResultSet result = courseObj.showCourse();
        try {
            while (result.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[2];
                rows[0] = result.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã so)
                rows[1] = result.getString(2);
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

    private void setNull() {
//Xoa trang cac JtextField

        this.txtExamDate.setDate(null);
        this.txtCourseName.setText(null);
        this.txtCourseName.requestFocus();
    }
//Ham khoa cac TextField

    private void setClockID(boolean a) {
//Khoa hoac mo khoa cho Cac JTextField
        this.txtExamDate.setEnabled(!a);
    }

    private void setClock(boolean a) {
//Khoa hoac mo khoa cho Cac JTextField
        this.txtExamDate.setEnabled(!a);
        this.txtCourseName.setEnabled(!a);
    }
//Ham khoa cac Button

    private void setButton(boolean a) {
//Vo hieu hoac co hieu luc cho cac JButton
        this.btnCourseAdd.setEnabled(a);
        this.btnCourseDelete.setEnabled(a);
        this.btnCourseEdit.setEnabled(a);
        this.btnCourseSave.setEnabled(!a);
        this.btnCourseReport.setEnabled(!a);
        this.btnCourseReset.setEnabled(!a);
        this.btnCourseClose.setEnabled(a);
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

    public CourseJFrame() throws SQLException {
        initComponents();
        jLabelNotify.setVisible(false);
        String[] colsName = {"EXAM DATE", "COURSE NAME"};
// đặt tiêu đề cột cho tableModel
        tableModel.setColumnIdentifiers(colsName);
// kết nối jtable với tableModel
        jTableCourses.setModel(tableModel);
//gọi hàm ShowData để đưa dữ liệu vào tableModel
        showDataCourse();
         long millis = System.currentTimeMillis();
        java.sql.Date currentDate = new java.sql.Date(millis);
        txtExamDate.setDate(currentDate);
        this.Cu.hide();
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
        txtExamDate = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCourseName = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        btnCourseAdd = new javax.swing.JButton();
        btnCourseEdit = new javax.swing.JButton();
        btnCourseDelete = new javax.swing.JButton();
        btnCourseSave = new javax.swing.JButton();
        btnCourseReset = new javax.swing.JButton();
        btnCourseReport = new javax.swing.JButton();
        btnCourseClose = new javax.swing.JButton();
        Cu = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCourses = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelNotify.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabelNotify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/notify.png"))); // NOI18N
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
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/date.png"))); // NOI18N
        jLabel1.setText("Exam of Date");

        txtCourseName.setColumns(20);
        txtCourseName.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtCourseName.setRows(3);
        jScrollPane2.setViewportView(txtCourseName);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/course.png"))); // NOI18N
        jLabel2.setText("Course of Exam");

        btnCourseAdd.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCourseAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        btnCourseAdd.setText("Add");
        btnCourseAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCourseAddMouseClicked(evt);
            }
        });

        btnCourseEdit.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCourseEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        btnCourseEdit.setText("Edit");
        btnCourseEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCourseEditMouseClicked(evt);
            }
        });

        btnCourseDelete.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCourseDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnCourseDelete.setText("Delete");
        btnCourseDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCourseDeleteMouseClicked(evt);
            }
        });

        btnCourseSave.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCourseSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png"))); // NOI18N
        btnCourseSave.setText("Save");
        btnCourseSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCourseSaveMouseClicked(evt);
            }
        });

        btnCourseReset.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCourseReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reset1.png"))); // NOI18N
        btnCourseReset.setText("Reset");
        btnCourseReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCourseResetMouseClicked(evt);
            }
        });

        btnCourseReport.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCourseReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/report.png"))); // NOI18N
        btnCourseReport.setText("Report");
        btnCourseReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCourseReportMousePressed(evt);
            }
        });

        btnCourseClose.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCourseClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/close.png"))); // NOI18N
        btnCourseClose.setText("Close");
        btnCourseClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCourseCloseMouseClicked(evt);
            }
        });

        Cu.setText("jLabel3");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtExamDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addComponent(Cu)
                .addGap(80, 80, 80))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnCourseAdd)
                .addGap(18, 18, 18)
                .addComponent(btnCourseEdit)
                .addGap(31, 31, 31)
                .addComponent(btnCourseDelete)
                .addGap(44, 44, 44)
                .addComponent(btnCourseSave)
                .addGap(37, 37, 37)
                .addComponent(btnCourseReset)
                .addGap(53, 53, 53)
                .addComponent(btnCourseReport)
                .addGap(35, 35, 35)
                .addComponent(btnCourseClose)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtExamDate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCourseAdd)
                            .addComponent(btnCourseEdit)
                            .addComponent(btnCourseDelete)
                            .addComponent(btnCourseSave)
                            .addComponent(btnCourseReset)
                            .addComponent(btnCourseReport)
                            .addComponent(btnCourseClose)))
                    .addComponent(Cu))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTableCourses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Exam of Date", "Course name"
            }
        ));
        jTableCourses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCoursesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCourses);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCourseAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseAddMouseClicked
        // TODO add your handling code here:
        setClockID(true);
        setNull();//Xoa trang TextField
        setClock(false);//Mo khoa TextField
        setButton(false);//Goi ham khoa cac Button
        flag = true;//Gan cothem = true de ghi nhan trang thai them moi
    }//GEN-LAST:event_btnCourseAddMouseClicked

    private void jTableCoursesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCoursesMouseClicked
        // TODO add your handling code here:
        try {
            //Lay chi so dong dang chon this.txtExamDate.setEnabled(!a);
            // this.txtCourseName.setEnabled(!a);

            int row = this.jTableCourses.getSelectedRow();
            TableModel model = jTableCourses.getModel();
            String examD = (this.jTableCourses.getModel().getValueAt(row, 0)).toString();
            String courseName = (this.jTableCourses.getModel().getValueAt(row, 1)).toString();
            ResultSet rs = courseObj.showIDCer(examD);//Goi ham lay du lieu theo ma loai

            if (rs.next()) {
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                this.txtExamDate.setDate(rs.getDate("EXAM_DATE"));
                this.txtCourseName.setText(rs.getString("COURSE_NAME"));
                this.Cu.setText(rs.getDate("EXAM_DATE").toString());
            }
        } catch (SQLException e) {
        }
    }//GEN-LAST:event_jTableCoursesMouseClicked

    private void btnCourseEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseEditMouseClicked
        // TODO add your handling code here:
        Date dateExam = txtExamDate.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dExam = sdf.format(dateExam);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateExam);
        //JDateChooser ch = new JDateChooser(dateExam);
        //ch.isEditable(false);
        //this.txtExamDate.setEnabled(false);
        //String id = txtExamDate.getDateFormatString();
        //String name = txtCerName.getText();
        if (dExam.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please choose a exam of date item for edit",
                    "Message", 1);
        } else {
            setClock(false);//Mo khoa cac TextField
            //this.txtExamDate.enable(false);
            //this.txtExamDate.setEnabled(false);
            setButton(false); //Khoa cac Button
            flag = false; //Gan cothem=false de ghi nhan trang thai la sua
        }
    }//GEN-LAST:event_btnCourseEditMouseClicked

    private void btnCourseDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseDeleteMouseClicked
        // TODO add your handling code here:
        jLabelNotify.setVisible(false);
        Date dateExam = txtExamDate.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dExam = sdf.format(dateExam);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateExam);
        
        try {
            if (dExam.length() == 0) {
                JOptionPane.showMessageDialog(null, "Choose a exame of date item for delete",
                        "Message", 1);
            } else {
                if (JOptionPane.showConfirmDialog(null, "Do you want to delete " + dExam + " this item ? ", "Message", 2) == 0) {
                    courseObj.deleteData(dExam);//goi ham xoa du lieu theo ma loai
                    clearData();//Xoa du lieu trong tableModel
                    showDataCourse();//Do du lieu vao table Model
                    setNull();//Xoa trang Textfield
                    jLabelNotify.setVisible(true);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fail", "Message", 1);
        }
    }//GEN-LAST:event_btnCourseDeleteMouseClicked

    private void btnCourseSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseSaveMouseClicked
        // TODO add your handling code here:
        Date dateExam = txtExamDate.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String id = sdf.format(dateExam);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateExam);        
        String name = txtCourseName.getText();
        if(getDateExam(calendar) <= 1 && getDayExam(calendar) < 20){
            JOptionPane.showMessageDialog(null, "Please enter a exam of date difference with current date and for between 21 days and 1 year",
                    "Message", 1);
        }else if (name.length() < 10 || name.length() > 100 || name.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Name cannot empty. Name include contain from 20 to 100 characters!", "Message", 1);
            txtCourseName.requestFocus();
        } else {
            try {
                if (flag == true) {
                    courseObj.insertData(id, name);
                    jLabelNotify.setVisible(true);
                } else {
                    
                    courseObj.editData(id,name,Cu.getText());
                    
                    jLabelNotify.setVisible(true);

                }
                clearData(); //goi ham xoa du lieu tron tableModel
                showDataCourse(); //Do lai du lieu vao Table Model
            } catch (SQLException e) {
            }
            setClock(false);
            setButton(true);
        }
    }//GEN-LAST:event_btnCourseSaveMouseClicked

    private void btnCourseResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseResetMouseClicked
        // TODO add your handling code here:
        setNull();
        setClock(true);
        setButton(true);
    }//GEN-LAST:event_btnCourseResetMouseClicked

    private void btnCourseCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseCloseMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new HomeJFrame().setVisible(true);
    }//GEN-LAST:event_btnCourseCloseMouseClicked

    private void btnCourseReportMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseReportMousePressed
        try {
            // TODO add your handling code here:exportELSX()
            new ReportCourse().exportELSX();
        } catch (IOException ex) {
            Logger.getLogger(CourseJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        //ReportCourse.ExportExcel(jTableCourses);
    }//GEN-LAST:event_btnCourseReportMousePressed

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
            java.util.logging.Logger.getLogger(CourseJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CourseJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CourseJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CourseJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new CourseJFrame().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(CourseJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cu;
    private javax.swing.JButton btnCourseAdd;
    private javax.swing.JButton btnCourseClose;
    private javax.swing.JButton btnCourseDelete;
    private javax.swing.JButton btnCourseEdit;
    private javax.swing.JButton btnCourseReport;
    private javax.swing.JButton btnCourseReset;
    private javax.swing.JButton btnCourseSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelNotify;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableCourses;
    private javax.swing.JTextArea txtCourseName;
    private com.toedter.calendar.JDateChooser txtExamDate;
    // End of variables declaration//GEN-END:variables
}