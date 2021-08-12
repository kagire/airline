package com.kagire.controllers;

import com.kagire.DepartmentService;
import com.kagire.EmployeeService;
import com.kagire.entity.Employee;
import com.kagire.webServices.DepartmentServiceWebImpl;
import com.kagire.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    EmployeeService employeeService;
    
    @GetMapping
    public final String departmentsPage(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return "departments";
    }

    @GetMapping("{id}")
    public final String departmentPage(Model model, @PathVariable long id) {
        List<Employee> employeeList = employeeService.findByDepartmentId((int)id);
        model.addAttribute("relatedEmployees", employeeList);
        if(employeeList.isEmpty()) model.addAttribute("isDeletable", "true");
        else model.addAttribute("isDeletable", "false");
        model.addAttribute("department", departmentService.findById(id).get());
        return "department";
    }

    @GetMapping("/new")
    public final String newDepartmentPage(Model model) {
        model.addAttribute("department", new Department());
        return "new_department";
    }

    @PostMapping("/new")
    public final String newDepartment(Department department) {
        departmentService.create(department);
        return "redirect:/departments";
    }

    @PostMapping("{id}")
    public final String updateDepartment(Department department) {
        departmentService.update(department);
        return "redirect:/departments";
    }

    @GetMapping("{id}/delete")
    public final String deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
        return "redirect:/departments";
    }
}
