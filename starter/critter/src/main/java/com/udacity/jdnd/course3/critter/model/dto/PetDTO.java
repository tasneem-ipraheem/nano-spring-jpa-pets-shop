package com.udacity.jdnd.course3.critter.model.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.udacity.jdnd.course3.critter.model.PetType;
import com.udacity.jdnd.course3.critter.utils.MESSAGES;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */

@Getter
@Setter
public class PetDTO {
	
    private long id;
    
    @NotNull(message = MESSAGES.VALIDATIONS.TYPE)
    private PetType type;
    
    @NotBlank(message = MESSAGES.VALIDATIONS.NAME)
    private String name;
    
    @NotNull (message = MESSAGES.VALIDATIONS.OWNER_ID)
    private Long ownerId;
    private LocalDate birthDate;
    private String notes;
    
    
	@Override
	public int hashCode() {
		return Objects.hash(birthDate, id, name, notes, ownerId, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PetDTO other = (PetDTO) obj;
		return Objects.equals(birthDate, other.birthDate) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(notes, other.notes) && ownerId == other.ownerId && type == other.type;
	}
	@Override
	public String toString() {
		return "PetDTO [id=" + id + ", type=" + type + ", name=" + name + ", ownerId=" + ownerId + ", birthDate="
				+ birthDate + ", notes=" + notes + "]";
	}

    
}
