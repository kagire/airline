package com.kagire.controllers;

import com.kagire.DepartmentService;
import com.kagire.EmployeeService;
import com.kagire.EmployeeService;
import com.kagire.entity.Department;
import com.kagire.entity.Employee;
import com.kagire.webServices.EmployeeServiceWebImpl;
import com.kagire.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public final String employeesPage(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employees";
    }

    @GetMapping("{id}")
    public final String employeePage(Model model, @PathVariable long id) {
        List<Department> departmentList = departmentService.findAll();
        model.addAttribute("availableDepartments", departmentList);
        model.addAttribute("employee", employeeService.findById(id).get());
        return "employee";
    }

    @GetMapping("/new")
    public final String newEmployeePage(Model model) {
        List<Department> departmentList = departmentService.findAll();
        model.addAttribute("availableDepartments", departmentList);
        if(departmentList.isEmpty()) model.addAttribute("isDepartmentsAccessible", "false");
        else model.addAttribute("isDepartmentsAccessible", "true");
        model.addAttribute("employee", new Employee());
        return "new_employee";
    }

    @PostMapping("/new")
    public final String newEmployee(Employee employee) {
        employeeService.create(employee);
        return "redirect:/employees";
    }

    @PostMapping("{id}")
    public final String updateEmployee(Employee employee) {
        employeeService.update(employee);
        return "redirect:/employees";
    }

    @GetMapping("{id}/delete")
    public final String deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }
}