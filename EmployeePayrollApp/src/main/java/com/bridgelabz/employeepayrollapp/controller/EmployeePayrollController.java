package com.bridgelabz.employeepayrollapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employeePayroll")
@Slf4j
public class EmployeePayrollController {
	@Autowired
	private EmployeePayrollService employeePayrollService;

	/**
	 * @return list of employee information from DB in JSON format
	 */

	@RequestMapping(value = {"","/","/employees"}, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> getEmployeePayrollData() {
		List<EmployeePayrollData> empDataList = null;
		empDataList = employeePayrollService.getEmployeePayrollData();
		ResponseDTO respDTO = new ResponseDTO("Get Call Success", empDataList);
		return new ResponseEntity<ResponseDTO> (respDTO, HttpStatus.OK);
	}

	/**
	 * @param empId - represents employee id
	 * @return employee information with same empId in JSON format
	 */

	@GetMapping("/employee/{empId}")
	public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId") int empId) {
		EmployeePayrollData employeePayrollData = null;
		employeePayrollData = employeePayrollService.getEmployeePayrollDataById(empId);
		ResponseDTO respDTO = new ResponseDTO("Get Call Success", employeePayrollData);
		return new ResponseEntity<ResponseDTO> (respDTO, HttpStatus.OK);
	}

	/**
	 * @apiNote accepts the employee data in JSON format and stores it in DB
	 * @param empPayrollDTO - represents object of EmployeePayrollDTO class
	 * @return accepted employee information in JSON format
	 */

	@PostMapping(path = "/create", consumes = {"application/json"})
	public ResponseEntity<ResponseDTO> addEmployeePayrollData(@Valid @RequestBody EmployeePayrollDTO empPayrollDTO) {
		log.debug("Employee DTO"+empPayrollDTO.toString());
		EmployeePayrollData employeePayrollData = employeePayrollService.createEmployeePayrollData(empPayrollDTO);
		ResponseDTO respDTO = new ResponseDTO("Created Employee Data ", employeePayrollData);
		return new ResponseEntity<ResponseDTO> (respDTO, HttpStatus.OK);
	}

	/**
	 * @apiNote accepts the employee data in JSON format and updates the employee having same empId from database
	 * @param empId - represents employee id
	 * @param empPayrollDTO - represents object of EmployeePayrollDTO class
	 * @return	updated employee information in JSON format
	 */

	@PutMapping(path = "/update/{empId}", consumes = {"application/json"})
	public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") int empId, @RequestBody EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData employeePayrollData = employeePayrollService.updateEmployeePayrollData(empId, empPayrollDTO);
		ResponseDTO respDTO = new ResponseDTO("Updated Employee payroll Data for: ", employeePayrollData);
		return new ResponseEntity<ResponseDTO> (respDTO, HttpStatus.OK);
	}

	/**@apiNote accepts the empId and deletes the data of that employee from DB
	 * @param empId - represents employee id
	 * @return empId and Acknowledgment message
	 */

	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId) {
		employeePayrollService.deleteEmployeePayrollData(empId);
		ResponseDTO respDTO = new ResponseDTO("Deleted Employee Data ", empId);
		return new ResponseEntity<ResponseDTO> (respDTO, HttpStatus.OK);	
	}
}
