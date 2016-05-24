package com.valmar.ecommerce.services;

import java.util.List;

import com.valmar.ecommerce.model.Employee;

public interface EmployeeService {

	Employee findById(int id);

	boolean findBySsn(String ssn);

	void saveEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployeeById(int id);

	List<Employee> findAllEmployees();

}
