package com.udacity.jdnd.course3.critter.service;

import java.time.DayOfWeek;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeReprository;
import com.udacity.jdnd.course3.critter.service.exception.AlreadyExistException;
import com.udacity.jdnd.course3.critter.service.exception.EntityNotFoundException;
import com.udacity.jdnd.course3.critter.service.exception.UnSupportedIdParam;
import com.udacity.jdnd.course3.critter.utils.MESSAGES;

//should not know any thing about dto
// Validation  here : throw an exception from the Service layer
// can deal with multi-repos and tables
@Service
@Transactional
public class EmployeeService {

	@Autowired
	EmployeeReprository employeeReprository;

	public Optional<Employee> getEmployeeById(Long id) {

		return employeeReprository.findById(id);
	}

	public Optional<Employee> save(Employee employee) {

		validateEmployeeEntity(employee);
		return Optional.of(employeeReprository.save(employee));

	}

	public Employee setAvailability(Set<DayOfWeek> daysAvailable, long id) {

		Employee employee = employeeReprository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(id));

		employee.setEmployeedaysAvailable(daysAvailable);
		return employeeReprository.save(employee);
	}

	void validateEmployeeEntity(Employee employee) {
		if (employee.getId() == null)
			throw new UnSupportedIdParam();

		if (employeeReprository.existsEmployeeByEmail(employee.getEmail()))
			throw new AlreadyExistException(MESSAGES.EXCEPTIONS.EMAIL_ALREADY_EXIST);

		if (employeeReprository.existsEmployeeByPhoneNumber(employee.getPhoneNumber()))
			throw new AlreadyExistException(MESSAGES.EXCEPTIONS.PHONENUMBER_ALREADY_EXIST);

	}

	/*
	 * Test test; public EmployeeService(Test test) { super(); this.test = test; }
	 * 
	 * public EmployeeDTO test(Long id){ Employee emp =
	 * test.test_findEmployeeById(id);
	 * 
	 * EmployeeDTO dto = DtoDaoAdaptor.getDtoFromEmployee(emp);
	 * System.out.println("dto = "+dto.toString()); return dto; }
	 */

}
