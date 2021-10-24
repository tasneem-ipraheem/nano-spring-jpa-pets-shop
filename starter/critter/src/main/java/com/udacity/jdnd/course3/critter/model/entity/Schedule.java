package com.udacity.jdnd.course3.critter.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @ManyToMany(cascade = CascadeType.ALL )//,fetch = FetchType.EAGER)
	private List<Employee> employees = new ArrayList<>();
    
    
    // schedule belongs to set of pets
    @ManyToMany(cascade = CascadeType.ALL)//,fetch = FetchType.EAGER)
    private List<Pet> pets = new ArrayList<>();
    
    
    
    private LocalDate date;
    
    @ElementCollection
    @CollectionTable(name = "Schedule_activities")
    private Set<EmployeeSkillType> ScheduleActivities = new HashSet<EmployeeSkillType>();

	@Override
	public int hashCode() {
		return Objects.hash(ScheduleActivities, date, employees, id, pets);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		return Objects.equals(ScheduleActivities, other.ScheduleActivities) && Objects.equals(date, other.date)
				&& Objects.equals(employees, other.employees) && id == other.id && Objects.equals(pets, other.pets);
	}

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", employees=" + employees + ", pets=" + pets + ", date=" + date
				+ ", ScheduleActivities=" + ScheduleActivities + "]";
	}
    
    

}
