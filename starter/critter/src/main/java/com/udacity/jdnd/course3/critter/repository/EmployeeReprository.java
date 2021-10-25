package com.udacity.jdnd.course3.critter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.model.entity.Employee;

@Repository
@Transactional // if @transaction herer -> trans/req
public interface EmployeeReprository extends  JpaRepository<Employee, Long> {

	
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
