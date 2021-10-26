package com.udacity.jdnd.course3.critter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public Optional<Pet> save(Pet pet) {
		validatePetEntity(pet);
		return Optional.of(petReprository.save(pet));
	}

	public List<Pet> getAllPets() {
		return petReprository.findAll();
	}
	
	void validatePetEntity(Pet pet) {
		if (pet.getId() == null)
			throw new UnSupportedIdParam();

		if (!customerReprository.existsById(pet.getCustomer().getId()))
			throw new GeneralResponceException(MESSAGES.CUSTOMER.ID_NOT_FOUND+pet.getCustomer().getId());


	}

}
