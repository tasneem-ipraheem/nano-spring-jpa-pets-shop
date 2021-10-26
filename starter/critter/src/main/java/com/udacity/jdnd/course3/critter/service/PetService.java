package com.udacity.jdnd.course3.critter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.model.entity.Customer;
import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerReprository;
import com.udacity.jdnd.course3.critter.repository.PetReprository;
import com.udacity.jdnd.course3.critter.service.exception.GeneralResponceException;
import com.udacity.jdnd.course3.critter.service.exception.UnSupportedIdParam;
import com.udacity.jdnd.course3.critter.utils.MESSAGES;

@Service
@Transactional
public class PetService {
	
	@Autowired
	PetReprository petReprository;
	
	@Autowired
	CustomerReprository customerReprository;

	public Optional<Pet>  getPetById(long petId) {
		return petReprository.findById(petId);
	}
	

	public List<Pet> getPetsForCustomer(long ownerId) {
		if (!customerReprository.existsById(ownerId))
			throw new GeneralResponceException(MESSAGES.CUSTOMER.ID_NOT_FOUND+ownerId);
		return petReprository.findByCustomerId(ownerId);
	}

	public Optional<Pet> save(Pet pet) {
		Optional<Customer> optCustomer = validatePetEntity(pet);
		
		if (optCustomer.isPresent()) {
		List<Pet> customerPets = optCustomer.get().getPets();//.contains(pet.);
		
			for (Pet p : customerPets) {
				if (p.getName().equals(pet.getName())) {
					throw new GeneralResponceException(MESSAGES.CUSTOMER.PET_NAME_ALREADY_EXIST+p.getId());

				}
			}
		
		}
		return Optional.of(petReprository.save(pet));
	}

	public List<Pet> getAllPets() {
		return petReprository.findAll();
	}
	
	Optional<Customer> validatePetEntity(Pet pet) {
		if (pet.getId() == null)
			throw new UnSupportedIdParam();

		Optional<Customer> optCustomer = customerReprository.findById(pet.getCustomer().getId());
		
		
		if (optCustomer.isEmpty())
			throw new GeneralResponceException(MESSAGES.CUSTOMER.ID_NOT_FOUND+pet.getCustomer().getId());
		

		return optCustomer;

	}


}
