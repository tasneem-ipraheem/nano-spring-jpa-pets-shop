package com.udacity.jdnd.course3.critter.model.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter
@Setter
@MappedSuperclass
public class User implements Serializable{
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@org.hibernate.annotations.Type( type = "nstring" )
    private String name;
	
    private String phoneNumber;
    private String email;
    private String city;
    private String fullAddress;
	@Override
	public int hashCode() {
		return Objects.hash(city, email, fullAddress, id, name, phoneNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(city, other.city) && Objects.equals(email, other.email)
				&& Objects.equals(fullAddress, other.fullAddress) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + ", city="
				+ city + ", fullAddress=" + fullAddress + "]";
	}


    
}
