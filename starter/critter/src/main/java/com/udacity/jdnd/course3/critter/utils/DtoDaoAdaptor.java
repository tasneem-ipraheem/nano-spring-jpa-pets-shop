package com.udacity.jdnd.course3.critter.utils;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//org.springframework.beans.BeanUtils.copyProperties(Object source, Object target)
//org.apache.commons.beanutils.BeanUtils.copyProperties(Object dest, Object orig)

import org.springframework.beans.BeanUtils;

import com.udacity.jdnd.course3.critter.model.EmployeeSkillType;
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

	/********************************* Pet ********************************/

	public static PetDTO getDtoFromPet(Pet pet) {
		PetDTO petDTO = new PetDTO();
		BeanUtils.copyProperties(pet, petDTO);
		petDTO.setOwnerId(pet.getCustomer().getId());
		return petDTO;
	}

	public static Pet getPetFromDto(PetDTO petDTO) {
		Pet pet = new Pet();
		BeanUtils.copyProperties(petDTO, pet);
		
		Customer customer = new Customer();
		customer.setId(petDTO.getOwnerId());
		pet.setCustomer(customer);
		
		return pet;
	}

	public static List<PetDTO> getListOfDtoFromPet(List<Pet> allPets) {
		List<PetDTO> petsListDTO = new ArrayList<PetDTO>();

		for (Pet pet : allPets)
			petsListDTO.add(getDtoFromPet(pet));

		return petsListDTO;
	}
	
	/****************************** Customer ****************************/

	public static CustomerDTO getDtoFromCustomer(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(customer, customerDTO);

		List<Long> ids = new ArrayList<Long>();

		List<Pet> pets = customer.getPets();
		if (pets != null && pets.size() != 0) {
			
			for (Pet pet : pets) {
				ids.add(pet.getId());
			}
		}
		customerDTO.setPetIds(ids);
		
//		System.out.println(" adapter dto list = "+customerDTO.getPetIds());
		return customerDTO;
	}

	public static Customer getCustomerWithoutPetsFromDto(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO, customer);
		return customer;
	}
	
	
	public static List<CustomerDTO> getListOfDtoFromCustomer(List<Customer> allCustomers) {
		List<CustomerDTO> custListDTO = new ArrayList<CustomerDTO>();

		for (Customer cust : allCustomers)
			custListDTO.add(getDtoFromCustomer(cust));

		return custListDTO;
	}


	/****************************** Employee ****************************/

	public static EmployeeDTO getDtoFromEmployee(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(employee, employeeDTO);

		Set<EmployeeSkillType> employeeDTOSkills = employeeDTO.getSkills();
		employeeDTOSkills.addAll(employee.getEmployeeSkills());
		employeeDTO.setSkills(employeeDTOSkills);

		Set<DayOfWeek> employeeAvailableDays = employeeDTO.getDaysAvailable();
		employeeAvailableDays.addAll(employee.getEmployeedaysAvailable());
		employeeDTO.setDaysAvailable(employeeAvailableDays);

		return employeeDTO;
	}

	public static Employee getEmployeeFromDto(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDTO, employee);

		Set<EmployeeSkillType> employeeSkills = employee.getEmployeeSkills();
		employeeSkills.addAll(employeeDTO.getSkills());
		employee.setEmployeeSkills(employeeSkills);

		Set<DayOfWeek> employeeAvailableDays = employee.getEmployeedaysAvailable();
		employeeAvailableDays.addAll(employeeDTO.getDaysAvailable());
		employee.setEmployeedaysAvailable(employeeAvailableDays);

		return employee;
	}

	public static List<EmployeeDTO> getListDtoFromEmployee(List<Employee> employeeList) {
		List<EmployeeDTO> empListDTO = new ArrayList<EmployeeDTO>();

		for (Employee emp : employeeList)
			empListDTO.add(getDtoFromEmployee(emp));

		return empListDTO;
	}

	/****************************** Schedule ****************************/

	public static ScheduleDTO getDtoFromSchedule(Schedule schedule) {
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		BeanUtils.copyProperties(schedule, scheduleDTO);
		return scheduleDTO;
	}

	public static Schedule getScheduleFromDto(ScheduleDTO scheduleDTO) {
		Schedule schedule = new Schedule();
		BeanUtils.copyProperties(scheduleDTO, schedule);
		return schedule;
	}




}
