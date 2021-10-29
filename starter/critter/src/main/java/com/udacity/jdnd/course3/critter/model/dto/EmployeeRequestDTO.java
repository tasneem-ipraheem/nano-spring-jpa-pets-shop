package com.udacity.jdnd.course3.critter.model.dto;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.udacity.jdnd.course3.critter.model.EmployeeSkillType;
import com.udacity.jdnd.course3.critter.utils.MESSAGES;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a request to find available employees by skills. Does not map
 * to the database directly.
 */
@Getter
@Setter
public class EmployeeRequestDTO implements Serializable{
	
	
    @NotNull(message = MESSAGES.VALIDATIONS.SKILLS)
    private Set<EmployeeSkillType> skills;
    
    @NotNull(message = MESSAGES.VALIDATIONS.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

	@Override
	public int hashCode() {
		return Objects.hash(date, skills);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeRequestDTO other = (EmployeeRequestDTO) obj;
		return Objects.equals(date, other.date) && Objects.equals(skills, other.skills);
	}

	@Override
	public String toString() {
		return "EmployeeRequestDTO [skills=" + skills + ", date=" + date + "]";
	}


    
}
