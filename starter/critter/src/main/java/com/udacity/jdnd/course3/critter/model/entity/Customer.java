package com.udacity.jdnd.course3.critter.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer extends User implements Serializable{
    
	@org.hibernate.annotations.Type( type = "nstring" )
    private String notes;
    
    
    //bi-directional - the owner(1:m) table = mapped by --> add & remove
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Pet> pets = new HashSet<Pet>();

    
	public void addPet(Pet pet) {
		pets.add( pet );
		pet.setCustomer(this);
	}

	public void removePet(Pet pet) {
		pets.remove( pet );
		pet.setCustomer( null );
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = super.hashCode();
//		result = prime * result + Objects.hash(notes, pets);
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (!super.equals(obj))
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Customer other = (Customer) obj;
//		return Objects.equals(notes, other.notes) && Objects.equals(pets, other.pets);
//	}

	@Override
	public String toString() {
		return "Customer [notes=" + notes + ", pets=" + pets.size() + ", id=" + super.getId() + "]";
	}


	
	
	
}
