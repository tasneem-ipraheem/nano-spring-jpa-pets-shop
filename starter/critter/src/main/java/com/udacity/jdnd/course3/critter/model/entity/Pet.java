package com.udacity.jdnd.course3.critter.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.udacity.jdnd.course3.critter.model.PetType;


@Entity
public class Pet {
	
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
    private PetType type;
    private String name;
    private LocalDate birthDate;
    private String notes;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    
    
    
    
    
    
    
    
    
    
    
	/*****************************/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PetType getType() {
		return type;
	}

	public void setType(PetType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
    
    

}
