package com.udacity.jdnd.course3.critter.utils;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
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
import com.udacity.jdnd.course3.critter.model.dto.ScheduleDTORequest;
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
		Set<EmployeeSkillType> dtoActivities = new HashSet<EmployeeSkillType>();
	    List<Long> employeeIds = new ArrayList<Long>();
	    List<Long> petIds = new ArrayList<Long>();
	    
		BeanUtils.copyProperties(schedule, scheduleDTO);

		List<Employee> employees = schedule.getEmployees();
		if (employees != null && employees.size()!=0) {
			for (Employee emp : employees)
				employeeIds.add(emp.getId());
			scheduleDTO.setEmployeeIds(employeeIds);
		}
		
		List<Pet> pets = schedule.getPets();
		if (pets != null && pets.size()!=0) {
			for (Pet p : pets)
				petIds.add(p.getId());
			scheduleDTO.setPetIds(petIds);
		}
		
		Set<EmployeeSkillType> activities = schedule.getScheduleActivities();
		if (activities != null && activities.size()!=0) {
			for (EmployeeSkillType act : activities)
				dtoActivities.add(act);
			scheduleDTO.setActivities(dtoActivities );
		}
		
		return scheduleDTO;
	}

	public static Schedule getScheduleWithoutMappedListesFromDto(ScheduleDTO scheduleDTO) {
		
		Schedule schedule = new Schedule();
		BeanUtils.copyProperties(scheduleDTO, schedule);
		
		Set<EmployeeSkillType> dtoActivities = scheduleDTO.getActivities();
		Set<EmployeeSkillType> activities = new HashSet<EmployeeSkillType>();
		
		if (dtoActivities != null && dtoActivities.size()!=0) {
			for (EmployeeSkillType act : dtoActivities) {
				activities.add(act);
//			System.out.println("* act : "+act.values());
		}
			schedule.setScheduleActivities(activities);
			
		}
//		System.out.println("* activities : "+activities);
//		System.out.println("* activities size : "+activities.size());

		
		return schedule;
	}


	public static Schedule getScheduleWithoutMappedListesForAdd(ScheduleDTORequest scheduleDTORequest) {
		
		Schedule schedule = new Schedule();
		BeanUtils.copyProperties(scheduleDTORequest, schedule);
		
//		Employee employee = new Employee();
//		employee.setId(employeeId);
//		List<Employee> employees = new ArrayList<>();
//		employees.add(employee);
//		schedule.setEmployees(employees);
		
		Set<EmployeeSkillType> dtoActivities = scheduleDTORequest.getActivities();
		Set<EmployeeSkillType> activities = new HashSet<EmployeeSkillType>();
		
		if (dtoActivities != null && dtoActivities.size()!=0) {
			for (EmployeeSkillType act : dtoActivities) {
				activities.add(act);
		}
			schedule.setScheduleActivities(activities);
			
		}

		
		return schedule;
	}

	public static List<ScheduleDTO> getListOfDtoFromSchedule(List<Schedule> allSchedules) {
		List<ScheduleDTO> scheduleListDTO = new ArrayList<ScheduleDTO>();

		for (Schedule schedule : allSchedules)
			scheduleListDTO.add(getDtoFromSchedule(schedule));

		return scheduleListDTO;
	}



}
