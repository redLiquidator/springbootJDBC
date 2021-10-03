package com.jdbc.springJDBC.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdbc.springJDBC.model.Employee;
import com.jdbc.springJDBC.model.Team;
import com.jdbc.springJDBC.serviceImpl.EmployeeServiceImpl;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	@PostMapping("/save")
	public String saveEmployee(@RequestBody Employee employee) {
		return employeeServiceImpl.addEmployee(employee);
	}
	
	@GetMapping("/getAllEmployees")
	public List<Employee>getAllEmployees() {
		return employeeServiceImpl.getAllEmployees();
	}
	
	/*
	 * @GetMapping("/getEmployeeById") public Employee getEmployeeById(@RequestParam(required = false)    
	 * //@RequestParam(required = false) > parameters can be optional. but, parameter is always mandatory when using @PathVariable 
	 * Integer id) { //int(x). use wrapper classes 
	 * return employeeServiceImpl.getEmployeeById(id); }
	 */
	
	@GetMapping("/getEmployeeById/{id}") //http://localhost:8080/getEmployeeById/1  @PathVariable-> cannot be optional
	public Employee getEmployeeById(@PathVariable Integer id) {      //int (x) use wrapper classes
		return employeeServiceImpl.getEmployeeById(id);
	}
	
	@PostMapping("/updateEmployee")
	public Integer updateEmployee(@RequestBody Employee employee) {
		return employeeServiceImpl.updateEmployee(employee);
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public Integer deleteEmployee(@PathVariable Integer id) {
		return employeeServiceImpl.deleteEmployee(id);
	}
	
	@PostMapping("/addEmployeeAndTeam")
	public String addEmployeeAndTeam(@RequestBody Map<String, Object> map) {    
		//{employee={name=moanna, city=island}, team={name=team1, leader=hela}} -> is this json or map? how to convert...?
		Map<String, Object> map1 = (Map<String, Object>) map.get("employee");
		Employee employee= new Employee();
		employee.setName(map1.get("name").toString());
		employee.setCity(map1.get("city").toString());
		
		Map<String, Object> map2 = (Map<String, Object>) map.get("team");
		Team team = new Team();
		team.setName(map2.get("name").toString());
		team.setLeader(map2.get("leader").toString());

		return employeeServiceImpl.addEmployeeAndTeam(employee, team);
	}
	
	@GetMapping("/getAllEmployeesAndTeams")
	public List<Employee> getAllEmployeesAndTeams() {
		return employeeServiceImpl.getAllEmployeesAndTeams();
	}
	
}
