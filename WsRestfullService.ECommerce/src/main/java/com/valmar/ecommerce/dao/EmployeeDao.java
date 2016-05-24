package com.valmar.ecommerce.dao;

import java.util.List;

import com.valmar.ecommerce.model.Employee;

public interface EmployeeDao {
	Employee findById(int id);
	
	boolean findBySsn(String ssn); 
	 
    void saveEmployee(Employee employee);
     
    void deleteEmployeeById(int id);
     
    List<Employee> findAllEmployees();
}
