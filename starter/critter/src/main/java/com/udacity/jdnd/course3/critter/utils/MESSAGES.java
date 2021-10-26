package com.udacity.jdnd.course3.critter.utils;

public class MESSAGES {
	
	public final class EXCEPTIONS {
		public static final String UN_SUPPORTED_ACTIVITY = "\"Sorry, we are not supporting this activity type \n"
				+ "Please select one Supported activity [PETTING, WALKING, FEEDING, MEDICATING, SHAVING]";
		
		
		
		public static final String  UnSupportedIdParam ="remove id param, our system offers id autogeneration ";
		
		
		
		public static final String ID_NOT_FOUND  = "Could not find Entity With id = ";



		public static final String EMAIL_ALREADY_EXIST = "Email Already Exist";
		public static final String PHONENUMBER_ALREADY_EXIST = "phoneNumber Already Exist";
		
		
		
		public static final String FAIL_SAVE ="saving operation faild, try again later";

		
	}
	
	
	public final class VALIDATIONS {
		public static final String NAME ="Name is mandatory";
		public static final String PHONENUMBER ="phoneNumber is mandatory";
		public static final String EMAIL ="email is mandatory";
		public static final String CITY ="city is mandatory";
		public static final String FULLADDRESS ="fullAddress is mandatory";
		public static final String DATE ="date is mandatory";
		public static final String OWNER_ID ="Owner id is mandatory";
		public static final String TYPE ="type is mandatory";
		public static final String ACTIVITIES = "Activities is mandatory";
		public static final String EMPLOYEE_IDS = "Employee IDs List is mandatory, we can't create schedual without assigning employee/s to it";
		public static final String PET_IDS = "Pet IDs List is mandatory, we can't create schedual without assigning Pet/s to it";

	}
	
	
	public final class EMPLOYEE {
		public static final String NOT_AVAILABLE_EMPLOYEES = "Sorry such skills are not available in this day, choose another day";
		public static final String ID_NOT_FOUND = "Can't find employee with id ";
		public static final String EMP_NO_SKILLS = "Employee with id %d don't have any of the requiered skills";
		public static final String LIST_MISSING_SKILLS = "Employee List do not contain employee for skills [ ";

	}
	
	public final class PET {
		public static final String ID_NOT_FOUND = "Can't find pet with id ";
		public static final String NO_CUSTOMER_PET = "No customers have pet with id ";

	}
	
	public final class CUSTOMER {
		public static final String ID_NOT_FOUND = "Can't find customer with id ";
		public static final String PET_NAME_ALREADY_EXIST = "this customer already have pet with the same name, the pet id = ";
	}
	
}
