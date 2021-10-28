package com.udacity.jdnd.course3.critter.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.udacity.jdnd.course3.critter.model.EmployeeSkillType;
import com.udacity.jdnd.course3.critter.utils.MESSAGES;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ScheduleDTORequest implements Serializable{

	  	private Long id;
	    
	    @NotNull(message = MESSAGES.VALIDATIONS.PET_IDS)
	    private List<Long> petIds;
	    
	    @NotNull(message = MESSAGES.VALIDATIONS.DATE)
	    private LocalDate date;

	    @NotNull(message = MESSAGES.VALIDATIONS.ACTIVITIES)
	    private Set<EmployeeSkillType> activities;

		@Override
		public int hashCode() {
			return Objects.hash(activities, date, id, petIds);
		}
		
		
		

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ScheduleDTORequest other = (ScheduleDTORequest) obj;
			return Objects.equals(activities, other.activities) && Objects.equals(date, other.date)
					&& Objects.equals(id, other.id) && Objects.equals(petIds, other.petIds);
		}

		@Override
		public String toString() {
			return "ScheduleDTORequest [id=" + id + ", petIds=" + petIds + ", date=" + date + ", activities="
					+ activities + "]";
		}
	    

	    
	}

