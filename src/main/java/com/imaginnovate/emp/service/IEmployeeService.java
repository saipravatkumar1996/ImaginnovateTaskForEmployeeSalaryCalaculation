package com.imaginnovate.emp.service;

import java.util.List;

import com.imaginnovate.emp.bean.TaxDeductionResponse;
import com.imaginnovate.emp.model.Employee;

public interface IEmployeeService {

	 public Employee saveEmployee(Employee employee);
	 public List<Employee> getAllEmployees();
	 public Employee getEmployeeById(String employeeId);
	 public List<TaxDeductionResponse> getTaxDeductionsForCurrentFinancialYear();
	 public TaxDeductionResponse getTaxDeducationForEmployee(String employeeId);
}
