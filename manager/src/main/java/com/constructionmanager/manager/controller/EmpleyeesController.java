package com.constructionmanager.manager.controller;

import com.constructionmanager.manager.model.Employees;
import com.constructionmanager.manager.repository.EmployeeRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/empluyees")
public class EmpleyeesController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employees> getEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employees getEmployeeById(@PathVariable Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employees createEmployee(@RequestBody Employees employees) {
        return employeeRepository.save(employees);
    }

    @PutMapping("/{id}")
    public Employees updateEmployee(@PathVariable Integer id, @RequestBody Employees employeesDetails) {
        Employees employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee does not exist"));

        employee.setFirstName(employeesDetails.getFirstName());
        employee.setLastName(employeesDetails.getFirstName());
        employee.setDateOfBirth(employeesDetails.getDateOfBirth());
        employee.setHiredDate(employeesDetails.getHiredDate());
        employee.setPosition(employeesDetails.getPosition());
        employee.setPhoneNumber(employeesDetails.getPhoneNumber());

        return employeeRepository.save(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        Employees employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee does not exist"));
        employeeRepository.deleteById(id);
    }


}
