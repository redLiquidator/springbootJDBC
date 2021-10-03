package com.jdbc.springJDBC.service;

import java.util.List;

import com.jdbc.springJDBC.model.Employee;
import com.jdbc.springJDBC.model.Team;

public interface EmployeeService {
	String addEmployee(Employee employee);
	List<Employee> getAllEmployees();
	Employee getEmployeeById(Integer id);
	
	
	//h.w 3 fetch the data by joining the tables.
	//h.w 1 update, delete
	Integer updateEmployee(Employee employee);
	Integer deleteEmployee(Integer id);

	//h.w 2 (spring jdbc) insert into multiple tables  
	String addEmployeeAndTeam(Employee employee, Team team);
	
	//h.w 3 fetch the data by joining the tables.
	List<Employee> getAllEmployeesAndTeams();
}
