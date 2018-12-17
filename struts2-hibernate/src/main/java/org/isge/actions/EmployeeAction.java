package org.isge.actions;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.SessionFactory;
import org.isge.dao.EmployeeDAO;
import org.isge.dao.EmployeeDAOImpl;
import org.isge.listener.HibernateListener;
import org.isge.model.Employee;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class EmployeeAction extends ActionSupport implements ModelDriven<Employee> {

	private Employee employee = new Employee();
	private List<Employee> employeeList;

	@Override
	public String execute() throws Exception {
		return this.listEmployees();
	}

	@Action(value="registerEmployee", results= { @Result(name="success", location="employee.jsp")})
	public String addEmployee() {
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext()
				.getAttribute(HibernateListener.KEY_NANE);
		EmployeeDAO employeeDAO = new EmployeeDAOImpl(sessionFactory);
		employeeDAO.save(employee);
		return SUCCESS;
	}

	public String listEmployees() {
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext()
				.getAttribute(HibernateListener.KEY_NANE);
		EmployeeDAO employeeDAO = new EmployeeDAOImpl(sessionFactory);
		this.employeeList = employeeDAO.findAll();
		return SUCCESS;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	@Override
	public Employee getModel() {
		return employee;
	}

}
