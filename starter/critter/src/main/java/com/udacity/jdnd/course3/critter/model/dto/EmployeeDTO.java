package com.udacity.jdnd.course3.critter.model.dto;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Objects;
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
    private Set<EmployeeSkillType> skills = new HashSet<EmployeeSkillType>();
    private Set<DayOfWeek> daysAvailable =new HashSet<DayOfWeek>();
    
	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", name=" + name + ", skills=" + skills + ", daysAvailable=" + daysAvailable
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(daysAvailable, id, name, skills);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDTO other = (EmployeeDTO) obj;
		return Objects.equals(daysAvailable, other.daysAvailable) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(skills, other.skills);
	}

    
}
