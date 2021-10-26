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
import com.udacity.jdnd.course3.critter.service.exception.AlreadyExistException;
import com.udacity.jdnd.course3.critter.service.exception.GeneralResponceException;
import com.udacity.jdnd.course3.critter.service.exception.UnSupportedIdParam;
import com.udacity.jdnd.course3.critter.utils.MESSAGES;

@Service
@Transactional
public class CustomerService {

	@Autowired
	CustomerReprository customerReprository;

	@Autowired
	PetReprository petReprository;

	public Optional<Customer> save(Customer customer, List<Long> petIds) {

		validateCustomerEntity(customer);
		if (petIds != null && petIds .size() !=0 ) {
			for (Long id : petIds) {
				Pet pet = petReprository.findById(id).orElseThrow(() -> new GeneralResponceException(MESSAGES.PET.ID_NOT_FOUND+id));
				customer.addPet(pet);
			}
		}

//		System.out.println(" repo customer list = "+customer.getPets());

		return Optional.of(customerReprository.save(customer));

	}
	
	public List<Customer> getAllCustomers (){
		return  customerReprository.findAll();
	}
	

	public Optional<Customer> getCustomerById(long id) {
		return  customerReprository.findById(id);

	}
	
	public Optional<Customer> getCustomerByPetId(long id) {
		Pet pet = petReprository.findById(id).orElseThrow(() -> new GeneralResponceException(MESSAGES.PET.ID_NOT_FOUND+id));
		return customerReprository.findById(pet.getCustomer().getId());
//		return Optional.of(customer);
	}

	void validateCustomerEntity(Customer customer) {
		if (customer.getId() == null)
			throw new UnSupportedIdParam();

		if (customerReprository.existsCustomerByEmail(customer.getEmail()))
			throw new AlreadyExistException(MESSAGES.EXCEPTIONS.EMAIL_ALREADY_EXIST);

		if (customerReprository.existsCustomerByEmail(customer.getPhoneNumber()))
			throw new AlreadyExistException(MESSAGES.EXCEPTIONS.PHONENUMBER_ALREADY_EXIST);

	}





}
