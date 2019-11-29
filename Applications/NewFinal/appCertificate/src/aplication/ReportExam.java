/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication;

import java.awt.HeadlessException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author David Nghi
 */
public class ReportExam {
    public static void ExportExcel(JTable tbl) {

        JFileChooser save = new JFileChooser();
        save.setDialogTitle("Save as");
        save.setFileFilter(new FileNameExtensionFilter("xlsx", "xls", "xlsm"));
        int choose = save.showSaveDialog(null);

        if (choose == JFileChooser.APPROVE_OPTION) {
            XSSFWorkbook export = new XSSFWorkbook();
            XSSFSheet sheet1 = export.createSheet("List Register");

            try {
                TableModel tableModel = tbl.getModel();
                String[] value = {"ID Subject", "Subject name", "ID Number", "Student ID", "Mark"};
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    XSSFRow newRow = sheet1.createRow(i);
                    XSSFRow newCol = sheet1.createRow(0);
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        XSSFCell newCell = newRow.createCell((short) j);
                        newCol.createCell((short) j).setCellValue(value[j]);
                        if (i == tableModel.getRowCount()) {
                            XSSFCellStyle style = export.createCellStyle();
                            style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
                            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            style.setBorderBottom(BorderStyle.THIN);
                            style.setBorderTop(BorderStyle.THIN);
                            style.setBorderLeft(BorderStyle.THIN);
                            style.setBorderRight(BorderStyle.THIN);
                            newCell.setCellStyle(style);
                            newCell.setCellValue(tableModel.getColumnName(j));
                        } else {
                            XSSFCellStyle style = export.createCellStyle();
                            style.setBorderBottom(BorderStyle.THIN);
                            style.setBorderTop(BorderStyle.THIN);
                            style.setBorderLeft(BorderStyle.THIN);
                            style.setBorderRight(BorderStyle.THIN);
                            newCell.setCellStyle(style);
                            newCell.setCellValue(tableModel.getValueAt(i, j).toString());
                        }
                    }
                }

                try (FileOutputStream otp = new FileOutputStream(save.getSelectedFile() + ".xlsx"); BufferedOutputStream bos = new BufferedOutputStream(otp)) {
                    export.write(bos);
                }

                JOptionPane.showMessageDialog(null, "File of report have created successful!");
            } catch (HeadlessException | IOException e) {
                JOptionPane.showMessageDialog(null, "File cann't report!");
            }
        }
    }
}