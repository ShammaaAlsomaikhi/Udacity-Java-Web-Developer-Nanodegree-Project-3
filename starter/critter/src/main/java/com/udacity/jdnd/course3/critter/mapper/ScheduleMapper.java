package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.entity.user.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ScheduleMapper {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PetRepository petRepository;

    public abstract ScheduleDTO toScheduleDTO(Schedule schedule);

    public abstract Schedule toSchedule(ScheduleDTO scheduleDTO);

    public abstract List<ScheduleDTO> toScheduleDTOList(List<Schedule> scheduleList);

    @AfterMapping
    void toScheduleDTO(@MappingTarget ScheduleDTO scheduleDTO, Schedule schedule){
        if(schedule.getEmployees() != null) {
            List<Long> employeesIds = new ArrayList<>();
            for (int i = 0; i < schedule.getEmployees().size(); i++) {
                employeesIds.add(i,schedule.getEmployees().get(i).getId());
            }
            scheduleDTO.setEmployeeIds(employeesIds);
        }

        if(schedule.getPets() != null) {
            List<Long> petIds = new ArrayList<>();
            for (int i = 0; i < schedule.getPets().size(); i++) {
                petIds.add(i,schedule.getPets().get(i).getId());
            }
            scheduleDTO.setPetIds(petIds);
        }
    }

    @AfterMapping
    void toSchedule(@MappingTarget Schedule schedule, ScheduleDTO scheduleDTO){
        if(scheduleDTO.getEmployeeIds() != null) {
            List<Employee> employees = new ArrayList<>();
            for (int i = 0; i < scheduleDTO.getEmployeeIds().size(); i++) {
                employees.add(i, employeeRepository.findById(scheduleDTO.getEmployeeIds().get(i)).orElseThrow(() -> new EntityNotFoundException()));
            }
            schedule.setEmployees(employees);
        }

        if(scheduleDTO.getPetIds() != null) {
            List<Pet> pets = new ArrayList<>();
            for (int i = 0; i < scheduleDTO.getPetIds().size(); i++) {
                pets.add(i, petRepository.findById(scheduleDTO.getPetIds().get(i)).orElseThrow(() -> new EntityNotFoundException()));
            }
            schedule.setPets(pets);
        }
    }


}
