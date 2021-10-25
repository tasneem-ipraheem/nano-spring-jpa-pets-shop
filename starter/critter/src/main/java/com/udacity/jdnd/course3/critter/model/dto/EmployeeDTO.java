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
public class EmployeeDTO extends UserDTO {

//    private long id;
//    
//    @NotBlank(message = "Name is mandatory")
//    private String name;
    
    private Set<EmployeeSkillType> skills = new HashSet<EmployeeSkillType>();
    private Set<DayOfWeek> daysAvailable =new HashSet<DayOfWeek>();
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(daysAvailable, skills);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDTO other = (EmployeeDTO) obj;
		return Objects.equals(daysAvailable, other.daysAvailable) && Objects.equals(skills, other.skills);
	}
	@Override
	public String toString() {
		return "EmployeeDTO [skills=" + skills + ", daysAvailable=" + daysAvailable + ", toString()=" + super.toString()
				+ "]";
	}
    

	
}
