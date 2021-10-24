package com.udacity.jdnd.course3.critter.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.utils.QUERIES;

@Repository
public class EmployeeReprository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	
//
//	public EmployeeReprository(EntityManager entityManager) {
//		super();
//		this.entityManager = entityManager;
//	}



	public Employee test_findEmployeeById(Long id){
		  TypedQuery<Employee> query = entityManager.createQuery(QUERIES.test_GET_EMPLOYEE_BY_ID, Employee.class);
		   query.setParameter("id", id);
		   
		   Employee e =  query.getSingleResult();
		   
		   System.out.println( " e = "+e.toString());
		   return e;
		
	}
}
