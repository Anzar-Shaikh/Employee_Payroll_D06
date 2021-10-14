package com.bridgelabz.employeepayrollapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.exception.EmployeePayrollException;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;

@Service
public class EmployeePayrollService implements IEmployeePayrollServices{

	@Autowired
	private EmployeePayrollRepository employeeRepository;
	
	/**
	 * @return list of employee information from DB 
	 */
	@Override
	public List<EmployeePayrollData> getEmployeePayrollData() {
		return (List<EmployeePayrollData>) employeeRepository.findAll();
	}

	/**
	 * @param empId - represents employee id
	 * @return employee information with same empId 
	 */
	@Override
	public EmployeePayrollData getEmployeePayrollDataById(int empId) {
		return employeeRepository.findById(empId).orElseThrow(() -> new EmployeePayrollException("Employee with employee Id"+empId
													+"  does not exist!!"));
	}

	/**
	 * accepts the employee data in the form of EmployeePayrollDTO and stores it in DB
	 * @param empPayrollDTO - represents object of EmployeePayrollDTO class
	 * @return accepted employee information in JSON format
	 */

	@Override
	public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData employeePayrollData = null;
		employeePayrollData = new EmployeePayrollData(empPayrollDTO);
		return employeeRepository.save(employeePayrollData);
	}

	/**
	 * accepts the employee data in the form of EmployeePayrollDTO and 
	 * updates the employee having same empId from database
	 * @param empId - represents employee id
	 * @param empPayrollDTO - represents object of EmployeePayrollDTO class
	 * @return	updated employee information in JSON format
	 */
	@Override
	public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
		empData.updateEmployeePayrollData(empPayrollDTO);
		return employeeRepository.save(empData);
	}

	/**accepts the empId and deletes the data of that employee from DB
	 * @param empId - represents employee id
	 * @return empId and Acknowledgment message
	 */

	@Override
	public void deleteEmployeePayrollData(int empId) {
		EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
		employeeRepository.delete(empData);
	}

}
