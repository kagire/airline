package com.kagire;

import com.kagire.config.MailConfig;
import com.kagire.config.MockBeanConfig;
import com.kagire.entity.Department;
import com.kagire.entity.Employee;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MailServiceImpl.class)
@ContextConfiguration(classes = MockBeanConfig.class)
class SpreadsheetServiceTest {

    @Autowired
    SpreadsheetService spreadsheetService;

    @Test
    void workbookToFile() {
        Assertions.assertDoesNotThrow(() -> spreadsheetService.workbookToFile(new XSSFWorkbook()));
    }

    @Test
    void fileToWorkbook() {
        Assertions.assertThrows(RuntimeException.class,() -> spreadsheetService.fileToWorkbook(File.createTempFile("spreadsheet-", String.valueOf(LocalDateTime.now()))));
    }

    @Test
    void exportDatabaseToWorkbook() {
        Assertions.assertDoesNotThrow(() -> spreadsheetService.exportDatabaseToWorkbook());
    }

    @Test
    void importFromWorkbookToDatabase() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet employeeSpreadsheet = workbook.createSheet( "Employees");
        XSSFSheet departmentSpreadsheet = workbook.createSheet( "Departments");

        XSSFRow row;
        row = employeeSpreadsheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(1);
        cell = row.createCell(1);
        cell.setCellValue("a");
        cell = row.createCell(2);
        cell.setCellValue("1999-10-10");
        cell = row.createCell(3);
        cell.setCellValue(111);
        cell = row.createCell(4);
        cell.setCellValue(1);

        row = departmentSpreadsheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue(1);
        cell = row.createCell(1);
        cell.setCellValue("wow");
        cell = row.createCell(2);
        cell.setCellValue(1);

        Assertions.assertThrows(RuntimeException.class, () -> spreadsheetService.importFromWorkbookToDatabase(workbook));
    }
}