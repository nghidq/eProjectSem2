/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication;

import database.DBconnect;
import java.awt.HeadlessException;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author David Nghi
 */
public class ReportStudent {
//get object

    private final Students stuObj = new Students();

    private static void formatHeader(XSSFWorkbook workbook, Cell h) {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        h.setCellStyle(style);
    }
//

    private static void writeHeaderLine(XSSFWorkbook workbook, XSSFSheet sheet) {
//String[] value = {"ID Student", "Full name", "Identity Card", "Gender", "DOB", "Phone", "Email", "Address"};
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Student ID");
        formatHeader(workbook, headerCell);
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Full name");
        formatHeader(workbook, headerCell);
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("ID Card");
        formatHeader(workbook, headerCell);
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Date of birth");
        formatHeader(workbook, headerCell);
        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Gender");
        formatHeader(workbook, headerCell);
        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("Phone");
        formatHeader(workbook, headerCell);
        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("Email");
        formatHeader(workbook, headerCell);
        headerCell = headerRow.createCell(7);
        headerCell.setCellValue("Address");
        formatHeader(workbook, headerCell);
    }

    private static void formatData(XSSFWorkbook workbook, Cell d) {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        d.setCellStyle(style);
    }

    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
            XSSFSheet sheet) throws SQLException {
        int rowCount = 1;
        while (result.next()) {
            String stuId = result.getString("STUDENT_ID");
            String stuName = result.getString("STUDENT_NAME");
            String stuCard = result.getString("IDENTITY_CARD");
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            date = result.getDate("DOB");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String stuDOB = sdf.format(date);
            String stuSex = result.getString("GENDER");
            String stuPhone = result.getString("PHONE");
            String stuEmail = result.getString("EMAIL");
            String stuAdd = result.getString("STUDENT_ADD");
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(stuId);
            formatData(workbook, cell);
            cell = row.createCell(columnCount++);
            cell.setCellValue(stuName);
            formatData(workbook, cell);
            cell = row.createCell(columnCount++);
            cell.setCellValue(stuCard);
            formatData(workbook, cell);
            cell = row.createCell(columnCount++);
            cell.setCellValue(stuDOB);
            formatData(workbook, cell);
            cell = row.createCell(columnCount++);
            cell.setCellValue(stuSex);
            formatData(workbook, cell);
            cell = row.createCell(columnCount++);
            cell.setCellValue(stuPhone);
            formatData(workbook, cell);
            cell = row.createCell(columnCount++);
            cell.setCellValue(stuEmail);
            formatData(workbook, cell);
            cell = row.createCell(columnCount++);
            cell.setCellValue(stuAdd);
            formatData(workbook, cell);
        }
    }

    public void exportELSX() throws FileNotFoundException, IOException {
        try {
            DBconnect.getConnect();
            ResultSet result = stuObj.showStudent();
            JFileChooser save = new JFileChooser();
            save.setDialogTitle("Save as");
            save.setFileFilter(new FileNameExtensionFilter("xlsx", "xls", "xlsm"));
            int choose = save.showSaveDialog(null);
            if (choose == JFileChooser.APPROVE_OPTION) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("List_Student");
                writeHeaderLine(workbook, sheet);
                writeDataLines(result, workbook, sheet);
                try (FileOutputStream otp = new FileOutputStream(save.getSelectedFile() + ".xlsx"); BufferedOutputStream bos = new BufferedOutputStream(otp)) {
                    workbook.write(bos);
                }
                JOptionPane.showMessageDialog(null, "File of report have created successful!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "File cann't report!");
        }
    }

    public static void ExportExcel(JTable tbl) {

        JFileChooser save = new JFileChooser();
        save.setDialogTitle("Save as");
        save.setFileFilter(new FileNameExtensionFilter("xlsx", "xls", "xlsm"));
        int choose = save.showSaveDialog(null);

        if (choose == JFileChooser.APPROVE_OPTION) {
            XSSFWorkbook export = new XSSFWorkbook();
            XSSFSheet sheet1 = export.createSheet("List Student");

            try {
                TableModel tableModel = tbl.getModel();
                String[] value = {"ID Student", "Full name", "Identity Card", "Gender", "DOB", "Phone", "Email", "Address"};
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

                FileOutputStream otp = new FileOutputStream(save.getSelectedFile() + ".xlsx");
                BufferedOutputStream bos = new BufferedOutputStream(otp);
                export.write(bos);
                bos.close();
                otp.close();

                JOptionPane.showMessageDialog(null, "File of report have created successful!");
            } catch (HeadlessException | IOException e) {
                JOptionPane.showMessageDialog(null, "File cann't report!");
            }
        }
    }
}
