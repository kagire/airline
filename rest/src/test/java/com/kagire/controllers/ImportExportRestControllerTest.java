package com.kagire.controllers;

import com.kagire.SpreadsheetService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImportExportRestController.class)
class ImportExportRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SpreadsheetService spreadsheetService;

    @Test
    void sendInfoMail() throws Exception {
        when(spreadsheetService.exportDatabaseToWorkbook()).thenReturn(new XSSFWorkbook());
        this.mvc.perform(get("/import"))
                .andExpect(status().isOk())
                .andExpect(content().string("Sent successfully"));
    }
}