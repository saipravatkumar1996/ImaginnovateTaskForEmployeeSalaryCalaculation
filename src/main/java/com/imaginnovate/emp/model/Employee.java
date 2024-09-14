package com.imaginnovate.emp.model;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee {
	
	@NotEmpty(message = "Employee ID cannot be empty")
	@Pattern(regexp = "^[A-Z]{3}-\\d{4}$", message = "Employee ID must be in the format AAA-1234")
	@Id
    @Column(name = "employee_id")
    private String employeeId;

	@NotEmpty(message = "First name cannot be empty")
    @Column(name = "first_name")
    private String firstName;

	@NotEmpty(message = "Last name cannot be empty")
    @Column(name = "last_name")
    private String lastName;

	@NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @ElementCollection
    @CollectionTable(name = "phone_numbers")
    @Column(name = "phone_number")
    private List<@Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be valid")String> phoneNumbers;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of joining should be in YYYY-MM-DD format")
    @Column(name = "doj")
    private String doj;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary should be a positive number")
    @Column(name = "salary")
    private double salary;


}
