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

@Entity
public class Employee implements Serializable{
	
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	
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

	
	/*****************************/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<EmployeeSkillType> getEmployeeSkills() {
		return employeeSkills;
	}

	public void setEmployeeSkills(List<EmployeeSkillType> employeeSkills) {
		this.employeeSkills = employeeSkills;
	}

	public List<DayOfWeek> getEmployeedaysAvailable() {
		return employeedaysAvailable;
	}

	public void setEmployeedaysAvailable(List<DayOfWeek> employeedaysAvailable) {
		this.employeedaysAvailable = employeedaysAvailable;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
	
	

}
