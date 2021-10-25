package com.udacity.jdnd.course3.critter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.model.entity.Schedule;

@Repository
@Transactional
public interface ScheduleReprository extends  JpaRepository<Schedule, Long> {


}
