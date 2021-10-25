package com.udacity.jdnd.course3.critter.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A example list of employee skills that could be included on an employee or a
 * schedule request.
 */

//@Entity
public enum EmployeeSkillType {
	PETTING, WALKING, FEEDING, MEDICATING, SHAVING;
	
//	  public static boolean contains(String s)
//	  {
//	      for(EmployeeSkillType choice:values())
//	           if (choice.name().equals(s)) 
//	              return true;
//	      return false;
//	  } 

//	@Id
//	public String name = toString();
}
