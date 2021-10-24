package com.udacity.jdnd.course3.critter.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.udacity.jdnd.course3.critter.model.PetType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pet implements Serializable{
	
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
    private PetType type;
    
    
	@org.hibernate.annotations.Type( type = "nstring" )
    private String name;
    private LocalDate birthDate;
    private String notes;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    
    
    
    // pet owns set of schedules
    
    @ManyToMany(mappedBy = "pets")
	private List<Schedule> schedules = new ArrayList<>();
    
    
	public void addSchedule(Schedule schedule) {
		schedules.add( schedule );
		schedule.getPets().add( this );
	}

	public void removeSchedule(Schedule schedule) {
		schedules.remove( schedule );
		schedule.getPets().remove( this );
	}  

}
