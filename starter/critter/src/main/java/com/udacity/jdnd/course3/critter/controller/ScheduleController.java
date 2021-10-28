package com.udacity.jdnd.course3.critter.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.udacity.jdnd.course3.critter.model.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.model.dto.ScheduleDTORequest;
import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.model.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.service.exception.GeneralServerException;
import com.udacity.jdnd.course3.critter.utils.DtoDaoAdaptor;
import com.udacity.jdnd.course3.critter.utils.MESSAGES;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
@Autowired
	ScheduleService scheduleService;
	
	@Validated
    @PostMapping
    public ScheduleDTO createSchedule(@Valid @RequestBody ScheduleDTO scheduleDTO) {
		Schedule schedule = scheduleService
				.save(DtoDaoAdaptor
						.getScheduleWithoutMappedListesFromDto(scheduleDTO)
						,scheduleDTO.getEmployeeIds()
						,scheduleDTO.getPetIds())
				.orElseThrow(() -> new GeneralServerException(MESSAGES.EXCEPTIONS.FAIL_SAVE));
		return DtoDaoAdaptor.getDtoFromSchedule(schedule);   
    }
	
	@Validated
    @PutMapping("/employee/{employeeId}")
    public ScheduleDTO addScheduleForEmployee(@PathVariable long employeeId
    		,@Valid @RequestBody ScheduleDTORequest scheduleDTORequest) {
		
		List<Long> empIds = new ArrayList<Long>();
		empIds.add(employeeId);
		
		Schedule schedule = scheduleService
				.save(DtoDaoAdaptor
						.getScheduleWithoutMappedListesForAdd(scheduleDTORequest)
						,empIds
						,scheduleDTORequest.getPetIds())
				.orElseThrow(() -> new GeneralServerException(MESSAGES.EXCEPTIONS.FAIL_SAVE));
		return DtoDaoAdaptor.getDtoFromSchedule(schedule);   
    }

	//postman name: Add Employee Schedule
    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
		return DtoDaoAdaptor.getListOfDtoFromSchedule(scheduleService.getAllSchedules());

    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
		return DtoDaoAdaptor.getListOfDtoFromSchedule(scheduleService.getAllSchedulesByPetId(petId));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
		return DtoDaoAdaptor.getListOfDtoFromSchedule(scheduleService.getAllSchedulesByEmployeeId(employeeId));
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
		return DtoDaoAdaptor.getListOfDtoFromSchedule(scheduleService.getAllSchedulesByCustomerId(customerId));
    }
    
}
