package com.udacity.jdnd.course3.critter.service;

import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.model.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeReprository;
import com.udacity.jdnd.course3.critter.utils.DtoDaoAdaptor;

@Service
public class EmployeeService {
	
	EmployeeReprository employeeReprository;
	DtoDaoAdaptor adaptor = new DtoDaoAdaptor();

	public EmployeeService(EmployeeReprository employeeReprository) {
		super();
		this.employeeReprository = employeeReprository;
	}
	
	public EmployeeDTO  test_findEmployeeById(Long id){
		Employee emp = employeeReprository.test_findEmployeeById(id);
		return DtoDaoAdaptor.getDtoFromEmployee(emp);
	}

}
