package com.udacity.jdnd.course3.critter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.model.entity.Customer;

@Repository
@Transactional
public interface CustomerReprository  extends  JpaRepository<Customer, Long> {

	boolean existsCustomerByEmail(String email);

//	Optional<Customer> findByPetsId(long id); this also works

}
