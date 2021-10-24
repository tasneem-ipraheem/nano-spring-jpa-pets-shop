package com.udacity.jdnd.course3.critter.model.dto;

import java.time.DayOfWeek;
import java.util.Set;

import com.udacity.jdnd.course3.critter.model.EmployeeSkillType;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents the form that employee request and response data takes. Does not map
 * to the database directly.
 */

@Getter
@Setter
public class EmployeeDTO {

    private long id;
    private String name;
    private Set<EmployeeSkillType> skills;
    private Set<DayOfWeek> daysAvailable;

}
