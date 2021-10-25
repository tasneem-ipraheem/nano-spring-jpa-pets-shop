package com.udacity.jdnd.course3.critter.model.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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
	@Override
	public int hashCode() {
		return Objects.hash(activities, date, employeeIds, id, petIds);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleDTO other = (ScheduleDTO) obj;
		return Objects.equals(activities, other.activities) && Objects.equals(date, other.date)
				&& Objects.equals(employeeIds, other.employeeIds) && id == other.id
				&& Objects.equals(petIds, other.petIds);
	}
	@Override
	public String toString() {
		return "ScheduleDTO [id=" + id + ", employeeIds=" + employeeIds + ", petIds=" + petIds + ", date=" + date
				+ ", activities=" + activities + "]";
	}

    
}
