package com.udacity.jdnd.course3.critter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.model.EmployeeSkillType;
import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.model.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerReprository;
import com.udacity.jdnd.course3.critter.repository.EmployeeReprository;
import com.udacity.jdnd.course3.critter.repository.PetReprository;
import com.udacity.jdnd.course3.critter.repository.ScheduleReprository;
import com.udacity.jdnd.course3.critter.service.exception.GeneralResponceException;
import com.udacity.jdnd.course3.critter.utils.MESSAGES;

@Service
@Transactional
public class ScheduleService {

	@Autowired
	ScheduleReprository scheduleReprository;

	@Autowired
	PetReprository petReprository;

	@Autowired
	EmployeeReprository employeeReprository;

	@Autowired
	CustomerReprository customerReprository;

	public List<Schedule> getAllSchedules() {
		return  scheduleReprository.findAll();
	}

	public List<Schedule> getAllSchedulesByEmployeeId(long id) {
		if (!employeeReprository.existsById(id))
			throw new GeneralResponceException(MESSAGES.EMPLOYEE.ID_NOT_FOUND+id);
		return  scheduleReprository.findAllByEmployeesId(id);
	}
	
	public List<Schedule> getAllSchedulesByPetId(long id) {
		if (!petReprository.existsById(id))
			throw new GeneralResponceException(MESSAGES.PET.ID_NOT_FOUND+id);
		return  scheduleReprository.findAllByPetsId(id);
		
	}

	public List<Schedule> getAllSchedulesByCustomerId(long customerId) {
		if (!customerReprository.existsById(customerId))
			throw new GeneralResponceException(MESSAGES.CUSTOMER.ID_NOT_FOUND+customerId);
		
		List<Pet> petsOfCustomer = petReprository.findByCustomerId(customerId);
		
		
		return  scheduleReprository.findByPetsCustomerId(customerId);
	}

	
	
	public Optional<Schedule> save(Schedule schedule_WithoutMappedListes, List<Long> employeeIds, List<Long> petIds) {
		

		// TODO : get emp by id - get his skills - add it to empActivities - check for missing activity
//		MESSAGES.EMPLOYEE.LIST_MISSING_SKILLS

//		Set<EmployeeSkillType> empActivities = new HashSet<EmployeeSkillType>();
//
//		Set<EmployeeSkillType> activities = schedule.getScheduleActivities();
//		if (activities != null && activities.size()!=0) {
//			for (EmployeeSkillType act : activities)
//				dtoActivities.add(act);
//			scheduleDTO.setActivities(dtoActivities );
//		}

		/********************************************/
		
		
		// TODO : uniqnes - empedded composit key - validate unique pk [petId, Date,
		// skills] - no empId
		// TODO : error msg : the 'petid' already has 'skillName' event on 'date' by emp
		// = 'empid'

		// find all pets with ids -> throw ex if not found
		List<Employee> employees = new ArrayList<Employee>();
		if (employeeIds != null && employeeIds.size() != 0) {
			
			
		    Set<EmployeeSkillType> actualCoveredSkills = new HashSet<EmployeeSkillType>();
			Set<EmployeeSkillType> neededActivities = schedule_WithoutMappedListes.getScheduleActivities();

			for (Long i : employeeIds) {
				Employee employee = employeeReprository.findById(i)
						.orElseThrow(() -> new GeneralResponceException(MESSAGES.EMPLOYEE.ID_NOT_FOUND + i));
				
				
				//TODO : chck if emp available at this day
				employees.add(employee);
				
				
				/*
				// TODO: 1- check if emp have no skills
				// TODO: 2- check if all skills covered

				Set<EmployeeSkillType> employeeSkills = employee.getEmployeeSkills();
				
				// store all emp skills [to check if all skills covered]
			    actualCoveredSkills.addAll(employeeSkills);
			    
			    //  check if emp have no skills
				int total = employeeSkills.size();
		    	employeeSkills.removeAll(neededActivities);

		    	if (total == employeeSkills.size())
			    	throw new GeneralResponceException( "Employee with id [ "+i+" ] don't have any of the requiered skills");
		    	
		    	*/
			    
			}
			
			/*
			 int neededSize = neededActivities.size();
			neededActivities.removeAll(actualCoveredSkills);
			
	    	if (neededSize == neededActivities.size()) {
	    		
	    		String missed = "";
	            for (EmployeeSkillType temp : neededActivities) {
	            	missed = missed +temp.values();
	            }
	    		
		    	throw new GeneralResponceException( 
		    			"The employee list don't cover this skills ["+missed+"]");
		    	
	    	}*/
		}


		// find all employee with ids -> throw ex if not found
		List<Pet> pets = new ArrayList<Pet>();
		if (petIds != null && petIds.size() != 0) {
			for (Long i : petIds) {
				Pet pet = petReprository.findById(i)
						.orElseThrow(() -> new GeneralResponceException(MESSAGES.PET.ID_NOT_FOUND + i));
//				pet.addSchedule(schedule_WithoutMappedListes); //*pad - miss emp
				pets.add(pet);
			}
		}

		// if OK
		// then insert , attach mapped entities
		schedule_WithoutMappedListes.setEmployees(employees);
		schedule_WithoutMappedListes.setPets(pets);
		
		// TODO : check if looping needed
//		 employee.addSchedule(schedule_WithoutMappedListes);
//		 pet.addSchedule(schedule_WithoutMappedListes);

		return Optional.of(scheduleReprository.save(schedule_WithoutMappedListes));

	}

	

}
