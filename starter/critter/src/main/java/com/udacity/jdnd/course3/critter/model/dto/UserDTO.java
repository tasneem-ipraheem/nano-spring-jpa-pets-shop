package com.udacity.jdnd.course3.critter.model.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private long id;
    
    @NotBlank(message = "Name is mandatory")
    private String name;
	
    @NotBlank(message = "phoneNumber is mandatory")
    private String phoneNumber;
    
    @NotBlank(message = "email is mandatory")
    private String email;
    
    @NotBlank(message = "city is mandatory")
    private String city;
    
    @NotBlank(message = "fullAddress is mandatory")
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
		UserDTO other = (UserDTO) obj;
		return Objects.equals(city, other.city) && Objects.equals(email, other.email)
				&& Objects.equals(fullAddress, other.fullAddress) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + ", city="
				+ city + ", fullAddress=" + fullAddress + "]";
	}

    
    

    
}
