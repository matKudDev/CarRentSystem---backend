package com.carrentsystem.carrentsystem.model.repository;

import com.carrentsystem.carrentsystem.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUsername(String username);
}
