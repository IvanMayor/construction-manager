package com.constructionmanager.manager.controller;

import com.constructionmanager.manager.model.Employees;
import com.constructionmanager.manager.repository.EmployeeRepository;
import com.constructionmanager.manager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employees> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employees getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employees createEmployee(@RequestBody Employees employees) {
        return employeeService.createEmployee(employees);
    }

    @PutMapping("/{id}")
    public Employees updateEmployee(@PathVariable Integer id, @RequestBody Employees employeesDetails) {
        return employeeService.updateEmployee(id, employeesDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }


}
