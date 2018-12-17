package org.isge.dao;

import java.util.List;

import org.isge.model.Employee;

public interface EmployeeDAO {
	void save(Employee employee);

	List<Employee> findAll();
	
	void remove(Employee employee);
}
