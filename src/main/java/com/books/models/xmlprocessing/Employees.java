package com.books.models.xmlprocessing;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.books.models.Employee;

@XmlRootElement
public class Employees {
	private List<Employee> empllist = new ArrayList<Employee>();

	public List<Employee> getEmpllist() {
		return empllist;
	}
	@XmlElement
	public void setEmpllist(List<Employee> empl) {
		this.empllist = empl;
	}
}
