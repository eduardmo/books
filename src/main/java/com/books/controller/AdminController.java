package com.books.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.books.controller.PasswordEncryption;
import com.books.models.Employee;
import com.books.models.xmlprocessing.Employees;
import com.books.models.xmlprocessing.XMLtoEmployees;
import com.books.models.xmlprocessing.service.EmployeeService;

@Controller
public class AdminController {
	@Autowired
	EmployeeService emplS;

	@RequestMapping(value = "adminactions", method = RequestMethod.GET, params = { "action" })
	public String search(@RequestParam("action") String action) {
		switch (action.toLowerCase()) {
		case "book management":
			return "redirect:/adminbooks";
		case "employees management":
			return "redirect:/employee";
		case "reports":
			return "redirect:/reports";
		}
		return "redirect:/admin";
	}

	@RequestMapping("/admin")
	public String admin(Map<String, Object> map) {
		return "admin";
	}

	@RequestMapping("/employee")
	public String empl(Map<String, Object> map) {
		map.put("showEdit", "false");
		System.out.println(emplS.getAllEmployees().isEmpty());
		map.put("employeeList", emplS.getAllEmployees());
		Employee employeeResult = new Employee();
		map.put("employee", employeeResult);
		return "employee";
	}

	@RequestMapping(value = "employee.do", method = RequestMethod.POST)
	public String add(@RequestParam("employeePersonalNumericalCode") long employeePersonalNumericalCode,
			@RequestParam("action") String action, @ModelAttribute("employee") Employee employee, BindingResult result,
			Map<String, Object> map) {
		System.out.println("trace0");
		switch (action.toLowerCase()) {
		case "add":
			System.out.println("trace1");
			boolean ok = true;
			if (emplS.getEmployeeById(employee.getEmployeePersonalNumericalCode()) != null) {
				map.put("pncErrorMessage", "Pesronal numerical code already registred!");
				ok = false;
				System.out.println("trace2");
			}
			if (emplS.getEmployeeByUsername(employee.getUsername()) != null) {
				map.put("usernameErrorMessage", "Username already taken!");
				ok = false;
				System.out.println("trace3");
			}

			if (ok) {
				System.out.println("trace4");
				employee.setPassword(PasswordEncryption.bcryptGen(employee.getPassword()));
				employee.setEmployeeRole("ROLE_USER");
				emplS.addEmployee(employee);
				map.put("employee", new Employee());
				map.put("showEdit", "true");
				map.put("employeel", employee);
			} else {
				map.put("showEdit", "false");
				map.put("employee", employee);
			}
			break;
		case "edit":
			ok = true;
			if (emplS.getEmployeeByUsername(employee.getUsername()) != null
					&& (emplS.getEmployeeByUsername(employee.getUsername())
							.getEmployeePersonalNumericalCode() != employee.getEmployeePersonalNumericalCode())) {
				map.put("usernameError", "Username already taken!");
				ok = false;
				map.put("showEdit", "true");
				map.put("employeel", employee);
				map.put("employee", new Employee());
			}
			if (ok) {
				if (!employee.getPassword().equals("")) {
					employee.setEmployeeRole("ROLE_USER");
					employee.setPassword(PasswordEncryption.bcryptGen(employee.getPassword()));
					emplS.editEmployee(employee);
					map.put("employee", new Employee());
					map.put("successED", "Successfully Edited!");
				} else {
					employee.setEmployeeRole("ROLE_USER");
					emplS.editEmployee(employee);
					map.put("employee", new Employee());
					map.put("failED", "Could Not Edit!");
				}
				map.put("showEdit", "false");
				map.put("employeeList", emplS.getAllEmployees());
			}
			break;
		case "delete":
			emplS.deleteEmployee(employee);
			map.put("successED", "Successfully Deleted!");
			map.put("showEdit", "false");
			break;
		}
		return "employee";
	}

	@RequestMapping(value = "employee.get", method = RequestMethod.GET, params = { "employeePersonalNumericalCode",
			"action" })
	public String search(@RequestParam("employeePersonalNumericalCode") long employeePersonalNumericalCode,
			@RequestParam("action") String action, @ModelAttribute("employee") Employee employee, BindingResult result,
			Map<String, Object> map) {
		switch (action.toLowerCase()) {
		case "search":
			System.out.println("ceva acolo fix aici1");
			Employee searcherdEmployee = emplS.getEmployeeById(employeePersonalNumericalCode);
			
			if (searcherdEmployee != null) {
			
				map.put("employeel", searcherdEmployee);
				System.out.println("ceva acolo fix aici2");
				map.put("showEdit", "true");
				break;
			} else {
				map.put("showEdit", "false");
				map.put("message", "Employee not found");
				map.put("employeeList", emplS.getAllEmployees());
				break;
			}
		case "show all":
			map.put("showEdit", "false");
			map.put("employeeList", emplS.getAllEmployees());
			break;
		}
		Employee employeeResult = new Employee();
		map.put("employee", employeeResult);
		return "employee";
	}
}
