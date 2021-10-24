package com.udacity.jdnd.course3.critter.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer implements Serializable{
	
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@org.hibernate.annotations.Type( type = "nstring" )
    private String name;
	
    private String phoneNumber;
    
	@org.hibernate.annotations.Type( type = "materialized_nclob" )
    private String notes;
    
    
    //bi-directional - the owner(1:m) table = mapped by --> add & remove
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets = new ArrayList<>();
    
    
	public void addPet(Pet pet) {
		pets.add( pet );
		pet.setCustomer(this);
	}

	public void removePet(Pet pet) {
		pets.remove( pet );
		pet.setCustomer( null );
	}
	
	
	
	
	
	
	/*****************************/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
	
	
	
}
