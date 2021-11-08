package com.kagire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class MailService {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SpreadsheetService spreadsheetService;

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendInfoEmail(String addressEmail){
        StringBuilder emailContent = new StringBuilder();
        // System.lineSeparator() not working for some reason
        emailContent.append("Here is department-app info table:")
                .append(System.lineSeparator())
                .append("Departments total: ")
                .append(departmentService.findAll().size())
                .append(System.lineSeparator())
                .append("Employees total: ")
                .append(employeeService.findAll().size());

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setSubject("Department-app info");
            helper.setTo(addressEmail);
            helper.setText(emailContent.toString(), true);
            helper.addAttachment("database-backup.xlsx",
                    spreadsheetService.workbookToFile(spreadsheetService.createInfoWorkbook()));
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | IOException ex){
            ex.printStackTrace();
        }
    }
}
