/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication;

import database.DBconnect;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author David Nghi
 */
public class ReportSubjects {
        //get object
    private final Subjects subObj = new Subjects();
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

        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Subject ID");
        formatHeader(workbook, headerCell);
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Subject name");
        formatHeader(workbook, headerCell);
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Certificate ID");
        formatHeader(workbook, headerCell);
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Certificate name");
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
            String subId = result.getString("SUBJECT_ID");
            String subName = result.getString("SUBJECT_NAME");
            String cerId = result.getString("CERTIFICATE_ID");
            String cerName = result.getString("CERTIFICATE_NAME");
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(subId);
            formatData(workbook, cell);
            cell = row.createCell(columnCount++);
            cell.setCellValue(subName);
            formatData(workbook, cell);
            cell = row.createCell(columnCount++);
            cell.setCellValue(cerId);
            formatData(workbook, cell);
            cell = row.createCell(columnCount++);
            cell.setCellValue(cerName);
            formatData(workbook, cell);
        }
    }
    public void exportELSX() throws FileNotFoundException, IOException {
        try {
            DBconnect.getConnect();
            ResultSet result = subObj.showSubject();
            JFileChooser save = new JFileChooser();
            save.setDialogTitle("Save as");
            save.setFileFilter(new FileNameExtensionFilter("xlsx", "xls", "xlsm"));
            int choose = save.showSaveDialog(null);
            if (choose == JFileChooser.APPROVE_OPTION) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("List_Subject");
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
}
