package com.imaginnovate.emp.util;

import org.springframework.stereotype.Component;

import com.imaginnovate.emp.model.Employee;

@Component
public class Validation {

	public void validateEmployee(Employee employee) {
        if (employee.getEmployeeId() == null || employee.getFirstName() == null ||
            employee.getLastName() == null || employee.getEmail() == null ||
            employee.getPhoneNumbers() == null || employee.getDoj() == null ||
            employee.getSalary() <= 0) {
            throw new IllegalArgumentException("Invalid employee data");
        }
    }
}
