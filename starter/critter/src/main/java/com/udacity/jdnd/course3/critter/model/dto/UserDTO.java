package com.udacity.jdnd.course3.critter.model.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

import com.udacity.jdnd.course3.critter.utils.MESSAGES;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private long id;
    
    @NotBlank(message = MESSAGES.VALIDATIONS.NAME)
    private String name;
	
    @NotBlank(message = MESSAGES.VALIDATIONS.PHONENUMBER)
    private String phoneNumber;
    
    @NotBlank(message = MESSAGES.VALIDATIONS.EMAIL)
    private String email;
    
    @NotBlank(message = MESSAGES.VALIDATIONS.CITY)
    private String city;
    
    @NotBlank(message = MESSAGES.VALIDATIONS.FULLADDRESS)
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
