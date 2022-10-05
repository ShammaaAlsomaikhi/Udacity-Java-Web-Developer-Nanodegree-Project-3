package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.mapper.ScheduleMapper;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleMapper scheduleMapper;

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO){
        return scheduleMapper.toScheduleDTO(scheduleRepository.save(scheduleMapper.toSchedule(scheduleDTO)));
    }

    public List<ScheduleDTO> getAllSchedules(){
        return scheduleMapper.toScheduleDTOList(scheduleRepository.findAll());
    }

    public List<ScheduleDTO> getScheduleForPet(long petId){
        return scheduleMapper.toScheduleDTOList(scheduleRepository.getScheduleForPet(petId));
    }

    public List<ScheduleDTO> getScheduleForEmployee(long employeeId){
        return scheduleMapper.toScheduleDTOList(scheduleRepository.getScheduleForEmployee(employeeId));
    }

    public List<ScheduleDTO> getScheduleForCustomer(long customerId){
        return scheduleMapper.toScheduleDTOList(scheduleRepository.getScheduleForCustomer(customerId));
    }
}
