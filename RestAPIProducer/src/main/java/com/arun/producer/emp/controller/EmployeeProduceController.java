package com.arun.producer.emp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arun.producer.emp.model.Employee;


@RestController
@RequestMapping("/employee")
public class EmployeeProduceController {

	List<Employee> empList = new ArrayList<>();

	@PostMapping("/addEmployee")
	public Employee addEmployee(@RequestBody Employee emp) {
		empList.add(emp);
		return emp;
	}

	@GetMapping("/searchEmployee/{empNo}")
	public Employee searchEmployee(@PathVariable("empNo") int empNo) {
		return empList.stream().filter(p -> p.getEmpNo() == empNo).findAny().orElse(null);
	}

	@GetMapping("getAllEmployees")
	public List<Employee> getAllEmployees() {
		return empList;
	}

}
