package com.udacity.jdnd.course3.critter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.model.entity.Customer;

@Repository
@Transactional
public interface CustomerReprository  extends  JpaRepository<Customer, Long> {

	boolean existsCustomerByEmail(String email);

	
//	private static final String GET_CUSTOMER_BY_PET_ID =
//		    "select c from Customer c " +
//		    "where c.favoriteComposer like :favoriteComposer";
//			
//			
//			private static final String FIND_HUMANOID_BY_OUTFIT =
//				       "select h from Humanoid h " +
//				       "where :outfit member of h.outfits";
//	@Query()
//	Customer findByPetId(long id);
	
//	Customer findByOutfitsContaining(long id);


}
