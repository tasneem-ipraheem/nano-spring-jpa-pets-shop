package com.udacity.jdnd.course3.critter.model.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.udacity.jdnd.course3.critter.model.EmployeeSkillType;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */

@Getter
@Setter
public class ScheduleDTO {
    private long id;
    private List<Long> employeeIds;
    private List<Long> petIds;
    private LocalDate date;
    private Set<EmployeeSkillType> activities;

}
