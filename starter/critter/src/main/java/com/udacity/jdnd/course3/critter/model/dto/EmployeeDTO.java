package com.udacity.jdnd.course3.critter.model.dto;

import java.time.DayOfWeek;
import java.util.Set;

import com.udacity.jdnd.course3.critter.model.EmployeeSkillType;

/**
 * Represents the form that employee request and response data takes. Does not map
 * to the database directly.
 */


public class EmployeeDTO {

    private long id;
    private String name;
    private Set<EmployeeSkillType> skills;
    private Set<DayOfWeek> daysAvailable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkillType> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkillType> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
