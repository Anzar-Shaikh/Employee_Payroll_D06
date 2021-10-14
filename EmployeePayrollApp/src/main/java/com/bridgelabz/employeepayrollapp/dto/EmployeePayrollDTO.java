package com.bridgelabz.employeepayrollapp.dto;

import javax.validation.constraints.Min;

import javax.validation.constraints.Pattern;


import lombok.ToString;

@ToString
public class EmployeePayrollDTO {
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee name Invalid")
	public String name;
	
	@Min(value = 5000, message = "min Wage should be more than 5000")
	public long salary;
	
	@Pattern(regexp = "male|female", message = "gender needs to be male or female")
	public String gender;
}
