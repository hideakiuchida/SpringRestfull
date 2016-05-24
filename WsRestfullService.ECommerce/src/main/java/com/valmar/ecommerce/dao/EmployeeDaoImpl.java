package com.valmar.ecommerce.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.valmar.ecommerce.model.Employee;

@Repository("employeeDao")
@EnableTransactionManagement
public class EmployeeDaoImpl extends AbstractDao<Integer, Employee> implements EmployeeDao {

	public Employee findById(int id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (Employee) criteria.uniqueResult();
	}

	public boolean findBySsn(String ssn) {
		 Criteria criteria = createEntityCriteria();
	     criteria.add(Restrictions.eq("ssn", ssn));
	     Employee employee = (Employee) criteria.uniqueResult();
	     if(employee!=null)
	    	 return true;
	     return false;
	}

	public void saveEmployee(Employee employee) {
		try {
			persist(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteEmployeeById(int id) {
		try {
			Query query = getSession().createSQLQuery("delete from Employee where id = :id");
	        query.setInteger("id", id);
	        query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployees() {
		try {
			Criteria criteria = createEntityCriteria();
	        return (List<Employee>) criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * @PersistenceContext private EntityManager entityManager;
	 */

	/*
	 * public Employee findById(int id) { Employee employee = null; try {
	 * employee = (Employee) entityManager.find(Employee.class, id); } catch
	 * (Exception e) { e.printStackTrace(); } return employee; }
	 * 
	 * public boolean findBySsn(String ssn) { try { TypedQuery<Employee> query =
	 * entityManager.createQuery("SELECT e FROM Employee e WHERE e.ssn = :ssn",
	 * Employee.class); Object queryResult = query.setParameter("ssn",
	 * ssn).getSingleResult(); if (queryResult != null) return true; } catch
	 * (Exception e) { e.printStackTrace(); } return false; }
	 * 
	 * public void saveEmployee(Employee employee) { try {
	 * entityManager.persist(employee); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 * 
	 * public void deleteEmployeeById(int id) { try { Employee emp = (Employee)
	 * entityManager.find(Employee.class, id); entityManager.remove(emp);
	 * entityManager.getTransaction().commit(); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 * 
	 * @SuppressWarnings("unchecked") public List<Employee> findAllEmployees() {
	 * try { return (List<Employee>) entityManager.createQuery(
	 * "Select e from Employee as e").getResultList(); } catch (Exception e) {
	 * e.printStackTrace(); } return null; }
	 */

}
