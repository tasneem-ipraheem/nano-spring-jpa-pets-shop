package com.udacity.jdnd.course3.critter.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer extends User implements Serializable{
	
//	@Id
//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//	
//	@org.hibernate.annotations.Type( type = "nstring" )
//    private String name;
//	
//    private String phoneNumber;
    
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(notes, pets);
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
		Customer other = (Customer) obj;
		return Objects.equals(notes, other.notes) && Objects.equals(pets, other.pets);
	}


	
	
	
}
