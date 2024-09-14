package com.imaginnovate.emp.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.imaginnovate.emp.bean.TaxDeductionResponse;
import com.imaginnovate.emp.exception.EmployeeException;
import com.imaginnovate.emp.model.Employee;
import com.imaginnovate.emp.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	 
	private final EmployeeRepository repo;
	private final Logger logger;

	public EmployeeServiceImpl(EmployeeRepository repo,Logger logger) {
		this.repo=repo;
		this.logger=logger;
	}
	@Override
	public Employee saveEmployee(Employee employee) {
		this.logger.info("Inside EmployeeServiceImpl saveEmployee method for save method");
		return repo.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		this.logger.info("Inside EmployeeServiceImpl saveEmployee method for get all employee");
		return repo.findAll();
	}

	@Override
	public Employee getEmployeeById(String employeeId) {
		logger.info("Inside EmployeeServiceImpl saveEmployee method for get  employee by Id");
		return repo.findById(employeeId).orElseThrow(()->new EmployeeException("Employee Not Found for id :"+employeeId));
	}
	
	public double calculateTax(double yearlySalary) {
		logger.info("Calculate total Tax slab deducation");
        double tax = 0;
        if (yearlySalary <= 250000) {
            tax = 0;
        } else if (yearlySalary <= 500000) {
            tax = (yearlySalary - 250000) * 0.05;
        } else if (yearlySalary <= 1000000) {
            tax = (250000 * 0.05) + (yearlySalary - 500000) * 0.10;
        } else {
            tax = (250000 * 0.05) + (500000 * 0.10) + (yearlySalary - 1000000) * 0.20;
        }
        logger.info("Calculate total Tax slab deducation complete");
        return tax;
    }
	
	public double calculateCess(double yearlySalary) {
		 logger.info("Calculate total calculate Cess");
        if (yearlySalary > 2500000) {
            return (yearlySalary - 2500000) * 0.02;
        }
        return 0;
    }
	
	
	
	public List<TaxDeductionResponse> getTaxDeductionsForCurrentFinancialYear(){
		 List<Employee> employees = repo.findAll();
		 logger.info("get Deducation for current finacial year");
	        int currentYear = LocalDate.now().getYear();
	        int startMonth = 4; // April
	        int endMonth = 3; // March

	        LocalDate startOfYear = LocalDate.of(currentYear - 1, startMonth, 1);
	        LocalDate endOfYear = LocalDate.of(currentYear, endMonth, 31);

	        return employees.stream().map(employee -> {
	            double yearlySalary = calculateYearlySalary(employee, startOfYear, endOfYear);
	            double taxAmount = calculateTax(yearlySalary);
	            double cessAmount = calculateCess(yearlySalary);
	            return new TaxDeductionResponse(employee.getEmployeeId(), employee.getFirstName(),
	                                            employee.getLastName(), yearlySalary, taxAmount, cessAmount);
	        }).collect(Collectors.toList());
	}
	
	 private double calculateYearlySalary(Employee employee, LocalDate startOfYear, LocalDate endOfYear) {
	        LocalDate doj = LocalDate.parse(employee.getDoj());
	        double monthlySalary = employee.getSalary();
	        double LossOfPayPerDay=Math.round(monthlySalary/30);
            int totalDaysWorkInFinacialyear=0;
            totalDaysWorkInFinacialyear= (int) ChronoUnit.DAYS.between(doj, endOfYear); 
            double perDaySalary=LossOfPayPerDay*Math.max(totalDaysWorkInFinacialyear, 0);
            return perDaySalary;
	    }
	 //Get Employee Salary for finacial year
	@Override
	public TaxDeductionResponse getTaxDeducationForEmployee(String employeeId) {
		Employee employee=repo.findById(employeeId).orElseThrow(()->new EmployeeException("Employee Not Found"));
		 logger.info("get Tax Deducation For Employee ");
	        int currentYear = LocalDate.now().getYear();
	        int startMonth = 4; // April
	        int endMonth = 3; // March

	        LocalDate startOfYear = LocalDate.of(currentYear - 1, startMonth, 1);
	        LocalDate endOfYear = LocalDate.of(currentYear, endMonth, 31);
	        double yearlySalary = calculateYearlySalary(employee, startOfYear, endOfYear);
            double taxAmount = calculateTax(yearlySalary);
            double cessAmount = calculateCess(yearlySalary);
            
            return new TaxDeductionResponse(employee.getEmployeeId(), employee.getFirstName(),
                    employee.getLastName(), yearlySalary, taxAmount, cessAmount);
	}

}
