package com.udacity.jdnd.course3.critter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.model.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeReprository;
import com.udacity.jdnd.course3.critter.repository.Test;
import com.udacity.jdnd.course3.critter.utils.DtoDaoAdaptor;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeReprository employeeReprository;

	DtoDaoAdaptor adaptor = new DtoDaoAdaptor();

	public EmployeeDTO  getEmployeeById(Long id){
		
		Employee emp = employeeReprository.getOne(id);
		
		EmployeeDTO dto = DtoDaoAdaptor.getDtoFromEmployee(emp);
		System.out.println("dto = "+dto.toString());
		return dto;
	}
	
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
