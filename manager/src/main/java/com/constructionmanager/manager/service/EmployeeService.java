package com.constructionmanager.manager.service;

import com.constructionmanager.manager.model.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.constructionmanager.manager.repository.EmployeeRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employees> getAllEmployees() {return employeeRepository.findAll();}

    public Employees getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no Employee with this id"));
    }

    public Employees createEmployee(Employees employees) {
        return employeeRepository.save(employees);
    }

    public Employees updateEmployee(Integer id, Employees employeesDetail) {
        Employees employees = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with this id is not found"));

        employees.setFirstName(employeesDetail.getFirstName());
        employees.setLastName(employeesDetail.getLastName());
        employees.setPosition(employeesDetail.getPosition());
        employees.setPhoneNumber(employeesDetail.getPhoneNumber());
        employees.setHiredDate(employeesDetail.getHiredDate());
        employees.setDateOfBirth(employeesDetail.getDateOfBirth());

        return employeeRepository.save(employees);
    }

    public void deleteEmployee(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee does not exist");
        }
        employeeRepository.deleteById(id);
    }
}
