package com.kagire.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartmentController {

    @GetMapping(value = "/departments")
    public final String departments(Model model) {
        return "departments";
    }

    @GetMapping(value = "/department")
    public final String department(Model model) {
        return "department";
    }

}
