package com.kagire.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping(value = "/employees")
    public final String employees(Model model) {
        return "employees";
    }

    @GetMapping(value = "/employee")
    public final String employee(Model model) {
        return "employee";
    }
}
