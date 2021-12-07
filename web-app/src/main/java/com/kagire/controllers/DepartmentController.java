package com.kagire.controllers;

import com.kagire.DepartmentService;
import com.kagire.EmployeeService;
import com.kagire.entity.Department;
import com.kagire.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    EmployeeService employeeService;
    
    @GetMapping
    public final String departmentsPage(Model model, @RequestParam("page") Optional<Integer> page) {
        int currentPage = page.orElse(1);
        int pageSize = 3;

        Page<Department> listPage = departmentService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        
        model.addAttribute("departmentsPage", listPage);

        int totalPages = listPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
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
