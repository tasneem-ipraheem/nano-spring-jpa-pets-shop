package com.udacity.jdnd.course3.critter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.model.entity.Employee;

//always retern only employee type
//can use multi table
//@Transactional // if @transaction herer -> trans/req
@Repository
public interface EmployeeReprository extends  JpaRepository<Employee, Long> {

	boolean existsEmployeeByEmail(String email);
	boolean  existsEmployeeByPhoneNumber(String phoneNumber);

	
	
	/*
   //you can reference associations and attributes by chaining
   //attribute names. Here we reference Humanoid.outfits.hat
   List<Humanoid> findAllByOutfitsHat(String hat);

   //you can provide specific JPQL Queries
   @Query("select h from Humanoid h where :outfit member of h.outfits ")
   List<Humanoid> findAllByOutfit(@Param("outfit") Outfit outfit);

   //does the same as above
   List<Humanoid> findAllByOutfitsContaining(Outfit outfit);

   //automatically uses query named Humanoid.findAllNamedQuery
   List<Humanoid> findAllNamedQuery(Outfit outfit);
	 */
}
