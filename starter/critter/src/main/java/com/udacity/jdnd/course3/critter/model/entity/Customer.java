package com.udacity.jdnd.course3.critter.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Customer {
	
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
    private String name;
    private String phoneNumber;
    private String notes;
    
    
    //bi-directional
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
