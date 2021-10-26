package com.udacity.jdnd.course3.critter.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.model.entity.Schedule;

@Repository
@Transactional
public interface ScheduleReprository extends  JpaRepository<Schedule, Long> {

	@Query("SELECT schedule"
			+ "  FROM schedule  JOIN schedule_employees "
			+ "  ON  schedule.id = schedule_employees.schedules_id"
			+ "  and schedule_employees.employees_id = :id")
	List<Schedule> findAllByEmployeesId(long id);

	@Query("SELECT distinct schedule"
			+ "  FROM schedule JOIN schedule_pets "
			+ "  ON  schedule.id = schedule_pets.schedules_id "
			+ "  and schedule_pets.pets_id =  :id")
	List<Schedule> findAllByPetsId(long id);


}
