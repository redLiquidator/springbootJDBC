package com.jdbc.springJDBC.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdbc.springJDBC.dao.EmployeeDao;
import com.jdbc.springJDBC.model.Employee;
import com.jdbc.springJDBC.model.Team;
import com.jdbc.springJDBC.service.EmployeeService;

@Service     //similar to component
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public String addEmployee(Employee employee) {
		return employeeDao.saveEmployee(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		return employeeDao.getEmployeeById(id);
	}

	@Override
	public Integer updateEmployee(Employee employee) {
		return employeeDao.updateEmployee(employee);
	}

	@Override
	public Integer deleteEmployee(Integer id) {
		return employeeDao.deleteEmployee(id);
	}

	@Override
	public String addEmployeeAndTeam(Employee employee, Team team) {
		return employeeDao.addEmployeeAndTeam(employee, team);
	}

	@Override
	public List<Employee> getAllEmployeesAndTeams() {
		return employeeDao.getAllEmployeesAndTeams();
	}
	
	
	
}
