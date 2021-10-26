package com.udacity.jdnd.course3.critter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.model.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.model.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.service.exception.GeneralServerException;
import com.udacity.jdnd.course3.critter.utils.DtoDaoAdaptor;
import com.udacity.jdnd.course3.critter.utils.MESSAGES;

import java.util.List;

import javax.validation.Valid;

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

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
		return DtoDaoAdaptor.getListOfDtoFromSchedule(scheduleService.getAllSchedules());

    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        throw new UnsupportedOperationException();
    }
}
