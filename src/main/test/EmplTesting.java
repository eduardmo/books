import java.util.List;

import com.books.models.Employee;
import com.books.models.xmlprocessing.XMLtoEmployees;
import com.books.models.xmlprocessing.service.impl.EmployeeServiceImpl;

public class EmplTesting {
	public static void main(String[] args){
		EmployeeServiceImpl emplS = new EmployeeServiceImpl();
		Employee e = emplS.getEmployeeById(1234567890123L);
		System.out.println(e.getEmployeeFirstName());
		e.setEmployeeFirstName("First3");
		e.setEmployeePersonalNumericalCode(1234567890111L);
		System.out.println(emplS.addEmployee(e));
		e.setEmployeeFirstName("First3");
		e.setEmployeePersonalNumericalCode(1234567890112L);
		System.out.println(emplS.addEmployee(e));
		System.out.println(emplS.deleteEmployee(e));
		System.out.println(emplS.deleteEmployee(e));
		e.setEmployeeFirstName("First Name3");
		e.setEmployeePersonalNumericalCode(1234567890111L);
		System.out.println(emplS.editEmployee(e));
		List<Employee> em = emplS.getAllEmployees();
		for (Employee employee : em) {
			System.out.println(employee.getEmployeeFirstName());
		}
	}
}
