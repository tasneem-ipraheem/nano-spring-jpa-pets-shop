package com.udacity.jdnd.course3.critter.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, customer, id, name, notes, schedules, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(customer, other.customer) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(notes, other.notes)
				&& Objects.equals(schedules, other.schedules) && type == other.type;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", type=" + type + ", name=" + name + ", birthDate=" + birthDate + ", notes=" + notes
				+ ", customer=" + customer + ", schedules=" + schedules + "]";
	}  

	
	
}
