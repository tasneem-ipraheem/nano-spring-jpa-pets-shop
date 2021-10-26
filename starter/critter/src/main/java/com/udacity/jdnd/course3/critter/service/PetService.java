package com.udacity.jdnd.course3.critter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.PetReprository;

@Service
@Transactional
public class PetService {
	
	@Autowired
	PetReprository petReprository;

	public Optional<Pet>  getPetById(long petId) {
		return petReprository.findById(petId);
	}

}
