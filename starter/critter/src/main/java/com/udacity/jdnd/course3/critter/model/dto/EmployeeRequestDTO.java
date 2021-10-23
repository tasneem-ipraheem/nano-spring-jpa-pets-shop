package com.udacity.jdnd.course3.critter.model.dto;

import java.time.LocalDate;
import java.util.Set;

import com.udacity.jdnd.course3.critter.model.EmployeeSkillType;

/**
 * Represents a request to find available employees by skills. Does not map
 * to the database directly.
 */
public class EmployeeRequestDTO {
    private Set<EmployeeSkillType> skills;
    private LocalDate date;

    public Set<EmployeeSkillType> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkillType> skills) {
        this.skills = skills;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
