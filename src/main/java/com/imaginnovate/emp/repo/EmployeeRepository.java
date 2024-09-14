package com.imaginnovate.emp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imaginnovate.emp.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,String> {

}
