package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(
            "SELECT s from Schedule s" +
                    " INNER JOIN s.pets sp" +
                    " WHERE sp.id = :petID"
    )
    List<Schedule> getScheduleForPet(@Param("petID") Long petID);

    @Query(
            "SELECT s from Schedule s" +
                    " INNER JOIN s.employees se" +
                    " WHERE se.id = :employeeID"
    )
    List<Schedule> getScheduleForEmployee(@Param("employeeID") Long employeeID);

    @Query(
            "SELECT s from Schedule s" +
                    " INNER JOIN s.pets sp" +
                    " INNER JOIN sp.owner c" +
                    " WHERE c.id = :customerID"
    )
    List<Schedule> getScheduleForCustomer(@Param("customerID") Long customerID);

}
