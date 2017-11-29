package com.books.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(namespace = "com.books.xmlprocessing.Employees")
public class Employee {

	private long employeePersonalNumericalCode;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeeIdCardNumber;
	private String employeeRole;
	private String username;
	private String password;
	
	
	public long getEmployeePersonalNumericalCode() {
		return employeePersonalNumericalCode;
	}
	@XmlElement
	public void setEmployeePersonalNumericalCode(long employeePersonalNumericalCode) {
		this.employeePersonalNumericalCode = employeePersonalNumericalCode;
	}
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	@XmlElement
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	public String getEmployeeLastName() {
		return employeeLastName;
	}

	@XmlElement
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public String getEmployeeIdCardNumber() {
		return employeeIdCardNumber;
	}

	@XmlElement
	public void setEmployeeIdCardNumber(String employeeIdCardNumber) {
		this.employeeIdCardNumber = employeeIdCardNumber;
	}
	public String getEmployeeRole() {
		return employeeRole;
	}

	@XmlElement
	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}
	public String getUsername() {
		return username;
	}
	
	@XmlElement
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}

	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}
	
}
