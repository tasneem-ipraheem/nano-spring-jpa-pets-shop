package com.udacity.jdnd.course3.critter.controller;

import java.time.DayOfWeek;
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

import com.udacity.jdnd.course3.critter.model.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.model.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.model.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.model.entity.Employee;
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

	@PostMapping("/customer")
	public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
		throw new UnsupportedOperationException();
	}

	@GetMapping("/customer")
	public List<CustomerDTO> getAllCustomers() {
		throw new UnsupportedOperationException();
	}

	@GetMapping("/customer/pet/{petId}")
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

	@PutMapping("/employee/{id}")
	public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long id) {
		employeeService.setAvailability(daysAvailable, id);
		
	}

	@GetMapping("/employee/availability")
	public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
		throw new UnsupportedOperationException();
	}

	/*
	 * @GetMapping("/test/{id}") public EmployeeDTO getTest(@PathVariable long id) {
	 * return employeeService.test(id); // throw new
	 * UnsupportedOperationException(); }
	 */

//    @Validated
//    @PostMapping("/employee")
//    public EmployeeDTO saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {

//    	    return employeeService.save(employeeDTO);

//    	EmployeeSkillType[] x = EmployeeSkillType.values();
//    	Set<EmployeeSkillType> y = employeeDTO.getSkills();
//    	
//    	
//    	if () {
//			
//		}
//    	if (.) {
//			
//		}

//    	Set<EmployeeSkillType> x = employeeDTO.getSkills();
//    	List<EmployeeSkillType> y = Arrays.asList(EmployeeSkillType.values());
//    	x.removeAll(y);
//    	if (x.size() != 0 ) {
//    		System.out.println(" xxxx "+x);
//    		System.out.println(" yyyy "+y);
//
//    		System.out.println(" ffff "+employeeDTO.toString());
//    		throw new UnSupportedActivityException();
//		}
//    	

//    	return new EmployeeDTO();

//    }
}
