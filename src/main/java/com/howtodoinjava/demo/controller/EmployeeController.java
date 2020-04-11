package com.howtodoinjava.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.howtodoinjava.demo.model.Employee;

@RestController
public class EmployeeController {
	
	@RequestMapping("/")
    public List<Employee> getEmployees() 
    {
		List<Employee> employeesList = new ArrayList<Employee>();
		employeesList.add(new Employee(1,"lokesh","gupta","howtodoinjava@gmail.com"));
		employeesList.add(new Employee(2,"Sushanth","Babu Kasarla","sushi@hotmail.com"));
		employeesList.add(new Employee(3,"Krish","Datla","kris@krish.com"));
		return employeesList;
    }
	
	@RequestMapping("/employee")
    public Employee getEmployee() 
    {
		Employee employee = new Employee();
		employee.setFirstName("Lathesh");
		employee.setEmail("karkeralathesh@gmail.com");
		employee.setLastName("Karkera");
		return employee;
    }
	
	@RequestMapping("/health")
    public String getHealth() 
    {
		return "Am fine";
    }

}
