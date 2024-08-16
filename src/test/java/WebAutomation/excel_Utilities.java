package WebAutomation;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class excel_Utilities {
    public static FileInputStream fis;
    public static FileOutputStream fos;
    public static XSSFWorkbook book;
    public static DataFormatter dataformetter;
    public static Sheet sheet;
    public static Row row;
    public static Cell cell;

    public int getNumberOfRows(String ExcelFilePath, String sheetName) throws IOException {
        fis = new FileInputStream(ExcelFilePath);
        book = new XSSFWorkbook(fis);
        sheet = book.getSheet(sheetName);
        int row = sheet.getPhysicalNumberOfRows();
        return row;
    }

    public int getNumberOfColumn(String ExcelFilePath, String sheetName, int rowNumber) throws IOException {
        fis = new FileInputStream(ExcelFilePath);
        book = new XSSFWorkbook(fis);
        sheet = book.getSheet(sheetName);
        row = sheet.getRow(rowNumber);//to get particular row data from user
        int column = row.getPhysicalNumberOfCells();//from above rownumber here we will get number of physical cell
        return column;
    }

    public static String getValue(String ExcelFilePath, String sheetName, int rowNumber, int cellNumber) throws IOException {
        fis = new FileInputStream(ExcelFilePath);
        book = new XSSFWorkbook(fis);
        sheet = book.getSheet(sheetName);
        row  = sheet.getRow(rowNumber);//to get particular row data from user
        cell = row.getCell(cellNumber);//to get particular cell data.
        dataformetter = new DataFormatter();//to formate all the value to desired formate.
        String cellValue = dataformetter.formatCellValue(cell);
        return cellValue;
    }

}
