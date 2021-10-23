package com.udacity.jdnd.course3.critter.model.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import com.udacity.jdnd.course3.critter.model.PetType;
import lombok.Getter;
import lombok.Setter;


@Entity

@Setter
@Getter
public class Pet {
	
		private long id;
	    private PetType type;
	    private String name;
	    private long ownerId;
	    private LocalDate birthDate;
	    private String notes;
	    
	    private String test;

}
