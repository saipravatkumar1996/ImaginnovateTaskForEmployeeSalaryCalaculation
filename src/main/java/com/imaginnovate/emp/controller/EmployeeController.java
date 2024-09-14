package com.imaginnovate.emp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.emp.bean.TaxDeductionResponse;
import com.imaginnovate.emp.model.Employee;
import com.imaginnovate.emp.service.IEmployeeService;
import com.imaginnovate.emp.util.Validation;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

/**
 * 
 * @author Pravat.Kumar
 * @Date 14-09-2024 01:06 PM 
 * @Description this controller used for employee creation and calculation tax deducation 
 *
 */
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	private final IEmployeeService employeeService;
	private final Logger logger;
	private final Validation validate;

	public EmployeeController(IEmployeeService employeeService, Logger logger,Validation validate) {
		this.employeeService = employeeService;
		this.logger = logger;
		this.validate=validate;
	}
	/**
	 * 
	 * @param employee required for new employee created
	 * @return return success or failed status
	 */

	@PostMapping(value = "newEmployeeCreated")
	public ResponseEntity<String> saveEmployee(@Valid @RequestBody Employee employee) {
		logger.info("Inside EmployeeController saveEmployee method for save employee");
		try {
			validate.validateEmployee(employee);
			employeeService.saveEmployee(employee);
			logger.info("Employee saved successfully");
			return ResponseEntity.ok("Employee saved successfully");
		} 
		catch (ValidationException validateException) {
            logger.error("Exception in save method: " + validateException.getMessage());
            return ResponseEntity.badRequest().body(validateException.getMessage());
        }
		catch (IllegalArgumentException e) {
			logger.error("Exception in save method for IlligalArgumentException "+e.toString());
			return ResponseEntity.badRequest().body(e.getMessage());
		}catch (Exception e) {
			logger.error("Exception in save method for "+e.toString());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	/**
	 * @author pravat.behera
	 * @return all employee annual salary and tax deducation salary with css calculation value 
	 */
	@GetMapping("/tax-deductions-all-employee")
    public ResponseEntity<?> getTaxDeductionsAllEmp() {
		try {
        List<TaxDeductionResponse> responses = employeeService.getTaxDeductionsForCurrentFinancialYear();
        return ResponseEntity.ok(responses);
		}
		catch (Exception e) {
			logger.error("Exception in getTaxDeductionsAllEmp method for "+e.toString());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
	
	/**
	 * 
	 * @param employeeId
	 * @return return employee salary slab with particular employee
	 */
	@GetMapping("{employeeId}/tax-deductions")
    public ResponseEntity<?> getTaxDeductionsEmployee(@PathVariable String employeeId) {
		try {
        TaxDeductionResponse responses = employeeService.getTaxDeducationForEmployee(employeeId);
        return ResponseEntity.ok(responses);
		}
		catch (Exception e) {
			logger.error("Exception in getTaxDeductionsAllEmp method for "+e.toString());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
	
	

}
