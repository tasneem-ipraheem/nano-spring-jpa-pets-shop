package com.udacity.jdnd.course3.critter.controller;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.critter.model.EmployeeSkillType;
import com.udacity.jdnd.course3.critter.model.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.model.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.model.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.model.entity.Customer;
import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.exception.EntityNotFoundException;
import com.udacity.jdnd.course3.critter.service.exception.GeneralServerException;
import com.udacity.jdnd.course3.critter.utils.DtoDaoAdaptor;
import com.udacity.jdnd.course3.critter.utils.MESSAGES;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into
 * separate user and customer controllers would be fine too, though that is not
 * part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	CustomerService customerService;

	@Validated
	@PostMapping("/customer")
	public CustomerDTO saveCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
		
		Customer customer = customerService.save(DtoDaoAdaptor.getCustomerWithoutPetsFromDto(customerDTO),customerDTO.getPetIds())
				.orElseThrow(() -> new GeneralServerException(MESSAGES.EXCEPTIONS.FAIL_SAVE));

		return DtoDaoAdaptor.getDtoFromCustomer(customer);
	}

	@GetMapping("/customer")
	public List<CustomerDTO> getAllCustomers() {
		
		return DtoDaoAdaptor.getListOfDtoFromCustomer(customerService.getAllCustomers());

	}
	
	@GetMapping("/customer/{customerId}")
	public CustomerDTO getOwnerByPet(@PathVariable long petId) {
		throw new UnsupportedOperationException();
	}



	/********************** employee ****************************/

	@Validated
	@PostMapping("/employee")
	public EmployeeDTO saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {

		Employee employee = employeeService.save(DtoDaoAdaptor.getEmployeeFromDto(employeeDTO))
				.orElseThrow(() -> new GeneralServerException(MESSAGES.EXCEPTIONS.FAIL_SAVE));

		return DtoDaoAdaptor.getDtoFromEmployee(employee);
	}

	@GetMapping("/employee/{employeeId}")
	public EmployeeDTO getEmployee(@PathVariable long employeeId) {

		Employee employee = employeeService.getEmployeeById(employeeId)
				.orElseThrow(() -> new EntityNotFoundException(employeeId));

		return DtoDaoAdaptor.getDtoFromEmployee(employee);

	}

	// postman name :  Employee set Availability
	@PutMapping("/employee/{id}")
	public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long id) {
		employeeService.setAvailability(daysAvailable, id);

	}

	// postman name : Check Employee Schedule
	@GetMapping("/employee/availability")
	public List<EmployeeDTO> findEmployeesForService( @RequestBody EmployeeRequestDTO employeeRequestDTO) {
		
		List<Employee> employeeList = employeeService.getEmployeesForService(employeeRequestDTO);
		return DtoDaoAdaptor.getListDtoFromEmployee(employeeList);

	}

}
