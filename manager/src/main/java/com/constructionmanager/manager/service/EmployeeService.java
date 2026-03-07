package com.constructionmanager.manager.service;

import com.constructionmanager.manager.model.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.constructionmanager.manager.repository.EmployeeRepository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
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

        if (employees.getHiredDate() == null) {
            employees.setHiredDate(LocalDate.now());
        }

        return employeeRepository.save(employees);
    }

    public Employees updateEmployee(Integer id, Employees employeesDetail) {
        Employees employees = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with this id is not found"));


        //  I know there is BeanUtils.copyProperties... but I think this version is completely fine and very straight forward so I will keep it as it is!
        //  I might implement it later
        //  TODO: BeanUtils.copyProperties... implement getNullPropertyNames for ignore argument in copy property.
        if(employeesDetail.getFirstName() != null) {
            employees.setFirstName(employeesDetail.getFirstName());
        }

        if (employeesDetail.getLastName() != null) {
            employees.setLastName(employeesDetail.getLastName());
        }

        if (employeesDetail.getPosition() != null) {
            employees.setPosition(employeesDetail.getPosition());
        }

        if (employeesDetail.getPhoneNumber() != null) {
            employees.setPhoneNumber(employeesDetail.getPhoneNumber());
        }

        if (employeesDetail.getDateOfBirth() != null) {
            employees.setDateOfBirth(employeesDetail.getDateOfBirth());
        }

        if (employeesDetail.getHiredDate() != null) {
            employees.setHiredDate(employeesDetail.getHiredDate());
        }

        return employeeRepository.save(employees);
    }

    public void deleteEmployee(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee does not exist");
        }
        employeeRepository.deleteById(id);
    }
}
