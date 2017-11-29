package com.books.models.xmlprocessing.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.books.models.Employee;
import com.books.models.xmlprocessing.Employees;
import com.books.models.xmlprocessing.EmployeetoXML;
import com.books.models.xmlprocessing.XMLtoEmployees;
import com.books.models.xmlprocessing.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public Employee getEmployeeById(long id) {
		List<Employee> empllist = XMLtoEmployees.unmarshalEmployees().getEmpllist();
		if (!empllist.isEmpty()) {
			for (int i = 0; i < empllist.size(); i++) {
				if (empllist.get(i).getEmployeePersonalNumericalCode() == id) {
					return empllist.get(i);
				}
			}
		}
		return null;
	}

	@Override
	public String addEmployee(Employee e) {
		List<Employee> empllist = XMLtoEmployees.unmarshalEmployees().getEmpllist();
		for (int i = 0; i < empllist.size(); i++) {
			if (empllist.get(i).getEmployeePersonalNumericalCode() == e.getEmployeePersonalNumericalCode()) {
				return "Pnc already registred!";
			}
		}
		empllist.add(e);
		Employees em = new Employees();
		em.setEmpllist(empllist);
		EmployeetoXML.marshall(em);

		return "Successfully Added";
	}

	@Override
	public String editEmployee(Employee e) {
		boolean found = false;
		List<Employee> empllist = XMLtoEmployees.unmarshalEmployees().getEmpllist();
		if (!empllist.isEmpty()) {
			for (int i = 0; i < empllist.size(); i++) {
				if (empllist.get(i).getEmployeePersonalNumericalCode() == e.getEmployeePersonalNumericalCode()) {
					if (e.getPassword().equals("")) {
						Employee empl = this.getEmployeeById(e.getEmployeePersonalNumericalCode());
						empllist.get(i).setPassword(empl.getPassword());
					} else {
						empllist.get(i).setPassword(e.getPassword());
					}
					empllist.get(i).setEmployeeFirstName(e.getEmployeeFirstName());
					empllist.get(i).setEmployeeLastName(e.getEmployeeLastName());
					empllist.get(i).setEmployeeIdCardNumber(e.getEmployeeIdCardNumber());
					empllist.get(i).setEmployeeRole(e.getEmployeeRole());
					empllist.get(i).setUsername(e.getUsername());
					Employees em = new Employees();
					em.setEmpllist(empllist);
					EmployeetoXML.marshall(em);
					found = true;
					break;
				}
			}
		}
		if (found)
			return "Succesfuly edited";
		else
			return "Could not find specified employee!";

	}

	@Override
	public String deleteEmployee(Employee e) {
		boolean found = false;
		List<Employee> empllist = XMLtoEmployees.unmarshalEmployees().getEmpllist();
		if (!empllist.isEmpty()) {
			for (int i = 0; i < empllist.size(); i++) {
				if (empllist.get(i).getEmployeePersonalNumericalCode() == e.getEmployeePersonalNumericalCode()) {
					empllist.remove(i);
					Employees em = new Employees();
					em.setEmpllist(empllist);
					EmployeetoXML.marshall(em);
					found = true;
					break;
				}
			}
		}

		if (found)
			return "Succesfuly deleted";
		else
			return "Could not find specified employee!";

	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> finalempllist = new ArrayList<Employee>();
		List<Employee> empllist = XMLtoEmployees.unmarshalEmployees().getEmpllist();
		if (!empllist.isEmpty()) {
			System.out.println(empllist.get(0).getEmployeeFirstName());
			System.out.println(empllist.get(1).getEmployeeFirstName());
			for (Employee e: empllist) {
				System.out.println(e);
				if (!(e.getEmployeeRole().equals("ROLE_ADMIN"))) {
					finalempllist.add(e);
				}
			}
		}
		return finalempllist;
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		List<Employee> empllist = XMLtoEmployees.unmarshalEmployees().getEmpllist();
		if (!empllist.isEmpty()) {
			for (int i = 0; i < empllist.size(); i++) {
				if (empllist.get(i).getUsername().equals(username)) {
					return empllist.get(i);
				}
			}
		}
		return null;
	}

}
