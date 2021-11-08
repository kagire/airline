package com.kagire;

import com.kagire.entity.Department;
import com.kagire.entity.Employee;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SpreadsheetService {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    public File workbookToFile(XSSFWorkbook workbook) throws IOException {
            File tempFile = File.createTempFile("spreadsheet-", String.valueOf(LocalDateTime.now()));
            FileOutputStream out = new FileOutputStream(tempFile);
            workbook.write(out);
            return tempFile;
    }

    public XSSFWorkbook createInfoWorkbook(){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet employeeSpreadsheet = workbook.createSheet( "Employees");
        XSSFSheet departmentSpreadsheet = workbook.createSheet( "Departments");
        List<Department> departmentList = departmentService.findAll();
        List<Employee> employeeList = employeeService.findAll();

        XSSFRow row;
        int rowCount = 0;
        for (Employee employee : employeeList){
            row = employeeSpreadsheet.createRow(rowCount++);
            Cell cell = row.createCell(0);
            cell.setCellValue(employee.getId());
            cell = row.createCell(1);
            cell.setCellValue(employee.getName());
            cell = row.createCell(2);
            cell.setCellValue(employee.getDateOfBirth());
            cell = row.createCell(3);
            cell.setCellValue(employee.getSalary());
            cell = row.createCell(4);
            cell.setCellValue(employee.getDepartmentId());
        }

        rowCount = 0;
        for (Department department : departmentList){
            row = departmentSpreadsheet.createRow(rowCount++);
            Cell cell = row.createCell(0);
            cell.setCellValue(department.getId());
            cell = row.createCell(1);
            cell.setCellValue(department.getName());
            cell = row.createCell(2);
            cell.setCellValue(department.getEmployeeCount());
        }

        return workbook;
    }
}
