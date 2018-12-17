package org.isge.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.isge.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {
	private SessionFactory sessionFactory;

	public EmployeeDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Employee employee) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(employee);
		session.getTransaction().commit();

	}

	@Override
	public List<Employee> findAll() {
		Session session = sessionFactory.openSession();
		List<Employee> result = session.createQuery("from Employee").list();
		return result;
	}

	@Override
	public void remove(Employee employee) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(employee);
		session.getTransaction().commit();

	}

}
