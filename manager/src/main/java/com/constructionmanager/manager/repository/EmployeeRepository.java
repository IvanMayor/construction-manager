package com.constructionmanager.manager.repository;

import com.constructionmanager.manager.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employees, Integer> { }
