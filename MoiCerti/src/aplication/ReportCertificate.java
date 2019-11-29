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
public class ReportCertificate {
    
        //get object
    private final Certificated cerObj = new Certificated();
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
        headerCell.setCellValue("Certificate ID");
        formatHeader(workbook, headerCell);
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Certificate name");
        formatHeader(workbook, headerCell);
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Certificate num");
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
            String cerId = result.getString("CERTIFICATE_ID");
            String cerName = result.getString("CERTIFICATE_NAME");
            String cerNum = result.getString("CERTIFICATE_NUM");
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(cerId);
            formatData(workbook, cell);
            cell = row.createCell(columnCount++);
            cell.setCellValue(cerName);
            formatData(workbook, cell);
            cell = row.createCell(columnCount++);
            cell.setCellValue(cerNum);
            formatData(workbook, cell);
            
        }
    }
    public void exportELSX() throws FileNotFoundException, IOException {
        try {
            DBconnect.getConnect();
            ResultSet result = cerObj.showCertificate();
            JFileChooser save = new JFileChooser();
            save.setDialogTitle("Save as");
            save.setFileFilter(new FileNameExtensionFilter("xlsx", "xls", "xlsm"));
            int choose = save.showSaveDialog(null);
            if (choose == JFileChooser.APPROVE_OPTION) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("List_Certificate");
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
            XSSFSheet sheet1 = export.createSheet("List_Certificates");

            try {
                TableModel tableModel = tbl.getModel();
                String[] value = {"Certificate ID", "Certificate name","Certificate num"};

                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    XSSFRow newRow = sheet1.createRow(i);
                    //XSSFRow newCol = sheet1.createRow(0);
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {

                        XSSFCell newCell = newRow.createCell((short) j);
                        //newCol.createCell((short) j).setCellValue(value[j]);
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
                            //newCol.setRowStyle(style);
                            //newCol.createCell((short) j).setCellValue(value[j]);
                        } else {
                            XSSFCellStyle style = export.createCellStyle();
                            style.setBorderBottom(BorderStyle.THIN);
                            style.setBorderTop(BorderStyle.THIN);
                            style.setBorderLeft(BorderStyle.THIN);
                            style.setBorderRight(BorderStyle.THIN);
                            newCell.setCellStyle(style);
                            newCell.setCellValue(tableModel.getValueAt(i, j).toString());
                            //newCol.createCell((short) j).setCellValue(value[j]);
                        }
                        //newCol.createCell((short) j).setCellValue(value[j]);
                    }
                    //newCol.createCell((short) i).setCellValue(value[i]);
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
