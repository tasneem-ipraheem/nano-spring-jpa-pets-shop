package com.udacity.jdnd.course3.critter.model.entity;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.udacity.jdnd.course3.critter.model.EmployeeSkillType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee extends User implements Serializable{
	
//	@Id
//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//	
//	@org.hibernate.annotations.Type( type = "nstring" )
//    private String name;
    
    @ElementCollection
    @CollectionTable(name = "employeeSkills")
    private Set<EmployeeSkillType> employeeSkills = new HashSet<EmployeeSkillType>();
    
    @ElementCollection
    @CollectionTable(name = "employeedaysAvailable")
    private Set<DayOfWeek> employeedaysAvailable = new HashSet<DayOfWeek>();
    
    
    
    // employee owns set of schedules

    @ManyToMany(mappedBy = "employees")
	private List<Schedule> schedules = new ArrayList<>();
    
    
	public void addSchedule(Schedule schedule) {
		schedules.add( schedule );
		schedule.getEmployees().add( this );
	}

	public void removeSchedule(Schedule schedule) {
		schedules.remove( schedule );
		schedule.getEmployees().remove( this );
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(employeeSkills, employeedaysAvailable, schedules);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(employeeSkills, other.employeeSkills)
				&& Objects.equals(employeedaysAvailable, other.employeedaysAvailable)
				&& Objects.equals(schedules, other.schedules);
	}

	
}
