package com.bridgelabz.employeepayrollapp.repository;

import java.util.Optional;


import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import org.springframework.data.repository.CrudRepository;

public interface EmployeePayrollRepository extends CrudRepository<EmployeePayrollData, Integer> {


	@Override
	<S extends EmployeePayrollData> S save(S s);

	@Override
	<S extends EmployeePayrollData> Iterable<S> saveAll(Iterable<S> iterable);

	@Override
	Optional<EmployeePayrollData> findById(Integer integer);

	@Override
	boolean existsById(Integer integer);

	@Override
	Iterable<EmployeePayrollData> findAll();

	@Override
	Iterable<EmployeePayrollData> findAllById(Iterable<Integer> iterable);

	@Override
	long count();

	@Override
	void deleteById(Integer integer);

	@Override
	void delete(EmployeePayrollData employeePayrollData);

	@Override
	void deleteAllById(Iterable<? extends Integer> iterable);

	@Override
	void deleteAll(Iterable<? extends EmployeePayrollData> iterable);

	@Override
	void deleteAll();
}
