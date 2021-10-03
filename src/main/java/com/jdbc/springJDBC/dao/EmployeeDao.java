package com.jdbc.springJDBC.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jdbc.springJDBC.model.Employee;
import com.jdbc.springJDBC.model.Team;

//access db  
@Repository  //equivalent to component. database related component
public class EmployeeDao {
	@Autowired
	private JdbcTemplate jdbctemplate;
	//hw multiple employees 
	public String saveEmployee(Employee employee) {
		String sql = "insert into employee(name,city,teamid) values(?,?,?)";   //
		jdbctemplate.update(sql, new Object[] {employee.getName(),employee.getCity(),employee.getTeamid()});
		return "employee added";
	}
	
	public List<Employee> getAllEmployees(){
		String sql = "select * from employee";
		return jdbctemplate.query(sql,new BeanPropertyRowMapper<Employee>(Employee.class));  //mapping with respective objects ex.employee in this case
		//return jdbctemplate.query(sql,List<Employee>);
	}
	
	public Employee getEmployeeById(Integer id) {
		String sql = "select * from employee where id = ?";
		return jdbctemplate.queryForObject(sql, new Object[] {id}, new int[] {Types.INTEGER}, new BeanPropertyRowMapper<Employee>(Employee.class)); //deprecated
	}
	
	public Integer updateEmployee(Employee employee) {
		String sql = "update employee set city = ?, name = ? where id = ? ";
		System.out.println(employee.getName()+" "+employee.getCity()+" "+employee.getId());
		return jdbctemplate.update(sql, employee.getName(),employee.getCity(),employee.getId());		
	}
	
	public Integer deleteEmployee(Integer id) {
		String sql = "delete from employee where id = ? ";
		return jdbctemplate.update(sql, new Object[] {id});
	} 
	
	public String addEmployeeAndTeam(Employee employee, Team team) {
		String sql= "insert into employee(name, city) values(?,?);" +
			    "insert into team(name, leader) values(?,?)";
		//https://stackoverflow.com/questions/24589138/inserting-data-into-multiple-tables-with-spring-jdbctemplate
		int[] arr = jdbctemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
		    @Override
		    public int getBatchSize() {
		        return 1;
		    }

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
			        ps.setObject(1, employee.getName());
			        ps.setObject(2, employee.getCity());
			        ps.setObject(3, team.getName());
			        ps.setObject(4, team.getLeader());

				
			}
		});
		return "employee and team added";
	}
	
	//add column to employee for joining 
	//ALTER TABLE employee
	//ADD COLUMN teamid INT NOT NULL;
	public List<Employee> getAllEmployeesAndTeams(){
		String sql = "select * from employee inner join team on employee.teamid = team.id";
		return jdbctemplate.query(sql,new BeanPropertyRowMapper<Employee>(Employee.class));  //I cannot use Employee&Team at the same time...so I used inheritance..
	}
}
