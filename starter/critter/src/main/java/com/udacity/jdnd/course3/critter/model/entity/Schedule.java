package com.udacity.jdnd.course3.critter.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

//schedules that associate pets and employees

@Entity
@Getter
@Setter
public class Schedule  implements Serializable{
	
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	
    // schedule belongs to set of employees

    @ManyToMany(cascade = CascadeType.ALL)
	private List<Employee> employees = new ArrayList<>();
    
    
    // schedule belongs to set of pets

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Pet> pets = new ArrayList<>();
    
    
    
    private LocalDate date;
    
    @ElementCollection
    @CollectionTable(name = "Schedule_activities")
    private List<EmployeeSkillType> ScheduleActivities = new ArrayList<>();

}
