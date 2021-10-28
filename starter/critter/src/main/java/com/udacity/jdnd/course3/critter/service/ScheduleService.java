package com.udacity.jdnd.course3.critter.service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.udacity.jdnd.course3.critter.model.EmployeeSkillType;
import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.model.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerReprository;
import com.udacity.jdnd.course3.critter.repository.EmployeeReprository;
import com.udacity.jdnd.course3.critter.repository.PetReprository;
import com.udacity.jdnd.course3.critter.repository.ScheduleReprository;
import com.udacity.jdnd.course3.critter.service.exception.AlreadyExistException;
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
		return scheduleReprository.findAll();
	}

	public List<Schedule> getAllSchedulesByEmployeeId(long id) {
		if (!employeeReprository.existsById(id))
			throw new GeneralResponceException(MESSAGES.EMPLOYEE.ID_NOT_FOUND + id);
		return scheduleReprository.findAllByEmployeesId(id);
	}

	public List<Schedule> getAllSchedulesByPetId(long id) {
		if (!petReprository.existsById(id))
			throw new GeneralResponceException(MESSAGES.PET.ID_NOT_FOUND + id);
		return scheduleReprository.findAllByPetsId(id);

	}

	public List<Schedule> getAllSchedulesByCustomerId(long customerId) {
		if (!customerReprository.existsById(customerId))
			throw new GeneralResponceException(MESSAGES.CUSTOMER.ID_NOT_FOUND + customerId);
		return scheduleReprository.findByPetsCustomerId(customerId);

//		List<Pet> petsOfCustomer = petReprository.findByCustomerId(customerId);
	}

	public Optional<Schedule> save(Schedule schedule_WithoutMappedListes, List<Long> employeeIds, List<Long> petIds) {

		DayOfWeek nededDate = schedule_WithoutMappedListes.getDate().getDayOfWeek();
		Set<DayOfWeek> nededDateSet = new HashSet<DayOfWeek>();
		nededDateSet.add(nededDate);
		

		// find all employees with ids -> throw ex if not found
		List<Employee> employees = new ArrayList<Employee>();
		Set<EmployeeSkillType> neededSkills = schedule_WithoutMappedListes.getScheduleActivities();
		String empIdsForLog="";
		
		if (employeeIds != null && employeeIds.size() != 0) {
			
			Set<EmployeeSkillType> actualCoveredSkills = new HashSet<EmployeeSkillType>();

			for (Long i : employeeIds) {
				Employee employee = employeeReprository.findById(i)
						.orElseThrow(() -> new GeneralResponceException(MESSAGES.EMPLOYEE.ID_NOT_FOUND + i));

				// TODO : check if emp available at this day
				Set<DayOfWeek> employeeDayes = employee.getEmployeedaysAvailable();
				if (!CollectionUtils.containsAny(nededDateSet,employeeDayes)) {
					throw new GeneralResponceException("Employee with id ["+i+"] not available on ["+nededDate+"]"
							+ "Kindly choose some one else");
				} 
				
				// TODO : check if emp don't have any needed skills
				Set<EmployeeSkillType> employeeSkills = employee.getEmployeeSkills(); 
				if (!CollectionUtils.containsAny(employeeSkills,neededSkills)) {
					throw new GeneralResponceException("Employee with id ["+i+"] don't have any of needed skills,"
							+ "Kindly choose some one else");
				} 
				//TODO : check if all skills are covered by provided employees
				actualCoveredSkills.addAll(employeeSkills);
				for (EmployeeSkillType s : neededSkills) 
					if (!actualCoveredSkills.contains(s)) 
						throw new GeneralResponceException("non of this employess covere skill ["+s+"]");
				
				//if success then add
				empIdsForLog += i+",";
				employees.add(employee);
			}

		}
		
		// find all pets with ids -> throw ex if not found
		List<Pet> pets = new ArrayList<Pet>();
		
		if (petIds != null && petIds.size() != 0) {
			for (Long i : petIds) {
				Pet pet = petReprository.findById(i)
						.orElseThrow(() -> new GeneralResponceException(MESSAGES.PET.ID_NOT_FOUND + i));
				
				// check for uniqueness of schedule -> throw ex if found
				List<Schedule> petSchedualList = pet.getSchedules();
				for (Schedule s : petSchedualList) {
					// check for the full date - not day of week
					if (s.getDate().equals( schedule_WithoutMappedListes.getDate())
						 &&	s.getScheduleActivities().containsAll(neededSkills)) {
						throw new AlreadyExistException("pet with id ["+i+"] already have schedual with same "
								+ "date, skills by employees [ "
								+ empIdsForLog.substring(0, empIdsForLog.length() - 1)+" ],"
								+ "\n schedual id is [ "+s.getId()+" ]");
					}
					
				}
				
				pets.add(pet);
			}
		}

		// if OK then insert , attach mapped entities
		schedule_WithoutMappedListes.setEmployees(employees);
		schedule_WithoutMappedListes.setPets(pets);

		return Optional.of(scheduleReprository.save(schedule_WithoutMappedListes));

	}

}
