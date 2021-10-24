package com.udacity.jdnd.course3.critter.model.entity;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.udacity.jdnd.course3.critter.model.EmployeeSkillType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee implements Serializable{
	
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@org.hibernate.annotations.Type( type = "nstring" )
    private String name;
    
    @ElementCollection
    @CollectionTable(name = "employeeSkills")
    private List<EmployeeSkillType> employeeSkills = new ArrayList<>();
    
    @ElementCollection
    @CollectionTable(name = "employeedaysAvailable")
    private List<DayOfWeek> employeedaysAvailable = new ArrayList<>();
    
    
    
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
	
}
