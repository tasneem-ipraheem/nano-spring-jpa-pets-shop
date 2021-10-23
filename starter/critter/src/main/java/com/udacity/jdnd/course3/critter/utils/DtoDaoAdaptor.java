package com.udacity.jdnd.course3.critter.utils;

//org.springframework.beans.BeanUtils.copyProperties(Object source, Object target)
//org.apache.commons.beanutils.BeanUtils.copyProperties(Object dest, Object orig)

import org.springframework.beans.BeanUtils;

import com.udacity.jdnd.course3.critter.model.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.model.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.model.dto.PetDTO;
import com.udacity.jdnd.course3.critter.model.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.model.entity.Customer;
import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.model.entity.Schedule;

//copyProperties(Object source, Object target)

public class DtoDaoAdaptor {

	/*********************************		Pet		********************************/
	
	public static PetDTO getDtoFromPet(Pet pet) {
		PetDTO petDTO = new PetDTO();
		BeanUtils.copyProperties(pet, petDTO);
		return petDTO;
	}
	
	public static Pet getPetFromDto(PetDTO petDTO) {
		Pet pet = new Pet();
		BeanUtils.copyProperties(petDTO,pet);
		return pet;
	}
	
	/******************************		Customer		****************************/

	public static CustomerDTO getDtoFromCustomer(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(customer, customerDTO);
		return customerDTO;
	}
	
	
	public static Customer getCustomerFromDto(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO,customer);
		return customer;
	}
	
	/******************************		Employee		****************************/

	public static EmployeeDTO getDtoFromEmployee(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(employee, employeeDTO);
		return employeeDTO;
	}
	
	
	public static Employee getCustomerFromDto(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDTO,employee);
		return employee;
	}

	/******************************		Schedule		****************************/

	public static ScheduleDTO getDtoFromSchedule(Schedule schedule) {
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		BeanUtils.copyProperties(schedule, scheduleDTO);
		return scheduleDTO;
	}
	
	public static Schedule getScheduleFromDto(ScheduleDTO scheduleDTO) {
		Schedule schedule = new Schedule();
		BeanUtils.copyProperties(scheduleDTO,schedule);
		return schedule;
	}

}
