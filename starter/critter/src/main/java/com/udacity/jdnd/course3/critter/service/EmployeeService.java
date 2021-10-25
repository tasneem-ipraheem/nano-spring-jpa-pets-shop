package com.udacity.jdnd.course3.critter.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.model.EmployeeSkillType;
import com.udacity.jdnd.course3.critter.model.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeReprository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeReprository employeeReprository;


	public Optional<Employee> getEmployeeById(Long id){
		
		return employeeReprository.findById(id);
	}


//	public Employee save(Employee employee) {
		// TODO Auto-generated method stub
//		Set<EmployeeSkillType> z = employee.getEmployeeSkills();
//		if (employee) {
//			
//		}
//		Employee x = employeeReprository.save(employee);
//	}
	
	/*
	Test test;
	public EmployeeService(Test test) {
		super();
		this.test = test;
	}
	
	public EmployeeDTO  test(Long id){
		Employee emp = test.test_findEmployeeById(id);
		
		EmployeeDTO dto = DtoDaoAdaptor.getDtoFromEmployee(emp);
		System.out.println("dto = "+dto.toString());
		return dto;
	}
	*/

}
