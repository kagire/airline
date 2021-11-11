package com.kagire.controllers;

import com.kagire.SpreadsheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin
@RestController
public class ImportExportRestController {

    @Autowired
    SpreadsheetService spreadsheetService;

    @GetMapping("/import")
    public ResponseEntity<String> sendInfoMail() throws ParseException {
        spreadsheetService.importFromWorkbookToDatabase(spreadsheetService.exportDatabaseToWorkbook());
        return new ResponseEntity<>("Sent successfully", HttpStatus.OK);
    }
}
