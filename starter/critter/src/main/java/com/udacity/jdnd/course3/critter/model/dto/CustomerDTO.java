package com.udacity.jdnd.course3.critter.model.dto;

import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */

@Getter
@Setter
public class CustomerDTO extends UserDTO{
	
    private String notes;
    private List<Long> petIds;
    
	@Override
	public String toString() {
		return "CustomerDTO [notes=" + notes + ", petIds=" + petIds + ", toString()=" + super.toString() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(notes, petIds);
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
		CustomerDTO other = (CustomerDTO) obj;
		return Objects.equals(notes, other.notes) && Objects.equals(petIds, other.petIds);
	}

  
}
