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

	public Optional<Schedule> save(Schedule schedule_WithoutMappedListes, List<Long> employeeIds, List<Long> petIds) {
//		Set<EmployeeSkillType> neededActivities = schedule_WithoutMappedListes.getScheduleActivities();

		// TODO : get emp by id - get his skills - add it to empActivities - check for missing activity
		
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

		// if not null,0
		// find all pets with ids -> throw ex
		List<Employee> employees = new ArrayList<Employee>();
		if (employeeIds != null && employeeIds.size() != 0) {
			for (Long i : employeeIds) {
				Employee employee = employeeReprository.findById(i)
						.orElseThrow(() -> new GeneralResponceException(MESSAGES.EMPLOYEE.ID_NOT_FOUND + i));
				
				employees.add(employee);
				
				/*
				// TODO: check if emp have 0 needed skills
//			    return titleOfVehicles.stream().allMatch(t -> models.stream().anyMatch(t::contains));
				List<T> list skl = new HashSet<E>();
				for (EmployeeSkillType a : neededActivities) {
					Arrays.stream(a.values()).anyMatch(null);
					
					= Collections.list(a);

				}
				
				//				employee.getEmployeeSkills().stream().anyMatch(Arrays.stream(DaysOfWeekEnum.values()););
				//		MESSAGES.EMPLOYEE.EMP_NO_SKILLS
				
				*/
			}
		}

		

//		MESSAGES.EMPLOYEE.LIST_MISSING_SKILLS
		
		// if not null,0
		// find all employee with ids -> throw ex
		List<Pet> pets = new ArrayList<Pet>();
		if (petIds != null && petIds.size() != 0) {
			for (Long i : petIds) {
				Pet pet = petReprository.findById(i)
						.orElseThrow(() -> new GeneralResponceException(MESSAGES.PET.ID_NOT_FOUND + i));
//				pet.addSchedule(schedule_WithoutMappedListes); //*pad sch - miss emp
				pets.add(pet);
			}
		}

		// if OK
		// then insert , attach mapped entities
		schedule_WithoutMappedListes.setEmployees(employees);
		schedule_WithoutMappedListes.setPets(pets);

		// TODO : check if looping needed
		// employee.addSchedule(schedule_WithoutMappedListes);
//		pet.addSchedule(schedule_WithoutMappedListes);

		return Optional.of(scheduleReprository.save(schedule_WithoutMappedListes));

	}

}
