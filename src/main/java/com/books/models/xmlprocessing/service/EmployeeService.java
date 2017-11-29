package com.books.models.xmlprocessing.service;

import java.util.List;

import com.books.models.Employee;

public interface EmployeeService {
	public Employee getEmployeeById(long id);
	public String addEmployee(Employee e);
	public String editEmployee(Employee e);
	public String deleteEmployee(Employee e);
	public List<Employee> getAllEmployees();
	public Employee getEmployeeByUsername(String username);
}
