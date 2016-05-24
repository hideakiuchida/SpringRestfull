package com.valmar.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valmar.ecommerce.dao.EmployeeDao;
import com.valmar.ecommerce.model.Employee;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao dao;

	public Employee findById(int id) {
		return dao.findById(id);
	}

	public boolean findBySsn(String ssn) {
		return dao.findBySsn(ssn);
	}

	public void saveEmployee(Employee employee) {
		dao.saveEmployee(employee);
	}

	public void updateEmployee(Employee employee) {
		Employee entity = dao.findById(employee.getId());
		if (entity != null) {
			entity.setName(employee.getName());
			entity.setJoiningDate(employee.getJoiningDate());
			entity.setSalary(employee.getSalary());
			entity.setSsn(employee.getSsn());
		}
	}

	public void deleteEmployeeById(int id) {
		dao.deleteEmployeeById(id);
	}

	public List<Employee> findAllEmployees() {
		return dao.findAllEmployees();
	}

}