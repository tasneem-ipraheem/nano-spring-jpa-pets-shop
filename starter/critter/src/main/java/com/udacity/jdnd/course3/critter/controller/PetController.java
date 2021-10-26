package com.udacity.jdnd.course3.critter.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.critter.model.dto.PetDTO;
import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
//import com.udacity.jdnd.course3.critter.model.entity.Customer;
import com.udacity.jdnd.course3.critter.service.exception.EntityNotFoundException;
import com.udacity.jdnd.course3.critter.service.exception.GeneralServerException;
import com.udacity.jdnd.course3.critter.utils.DtoDaoAdaptor;
import com.udacity.jdnd.course3.critter.utils.MESSAGES;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
	
	@Autowired
	PetService petService;

	@Validated
    @PostMapping
    public PetDTO savePet(@Valid @RequestBody PetDTO petDTO) {
		
		Pet pet = petService.save(DtoDaoAdaptor.getPetFromDto(petDTO))
				.orElseThrow(() -> new GeneralServerException(MESSAGES.EXCEPTIONS.FAIL_SAVE));
		return DtoDaoAdaptor.getDtoFromPet(pet);   
	}

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
		Pet pet = petService.getPetById(petId)
				.orElseThrow(() -> new EntityNotFoundException(petId));
		return DtoDaoAdaptor.getDtoFromPet(pet);	    
    }

    @GetMapping
    public List<PetDTO> getPets(){
		return DtoDaoAdaptor.getListOfDtoFromPet(petService.getAllPets());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        throw new UnsupportedOperationException();
    }
}
