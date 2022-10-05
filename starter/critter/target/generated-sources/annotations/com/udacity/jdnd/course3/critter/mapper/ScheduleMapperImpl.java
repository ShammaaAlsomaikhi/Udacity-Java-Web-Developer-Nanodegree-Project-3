package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-23T01:40:49+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3.1 (BellSoft)"
)
@Component
public class ScheduleMapperImpl extends ScheduleMapper {

    @Override
    public ScheduleDTO toScheduleDTO(Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }

        ScheduleDTO scheduleDTO = new ScheduleDTO();

        if ( schedule.getId() != null ) {
            scheduleDTO.setId( schedule.getId() );
        }
        scheduleDTO.setDate( schedule.getDate() );
        Set<EmployeeSkill> set = schedule.getActivities();
        if ( set != null ) {
            scheduleDTO.setActivities( new HashSet<EmployeeSkill>( set ) );
        }

        toScheduleDTO( scheduleDTO, schedule );

        return scheduleDTO;
    }

    @Override
    public Schedule toSchedule(ScheduleDTO scheduleDTO) {
        if ( scheduleDTO == null ) {
            return null;
        }

        Schedule schedule = new Schedule();

        schedule.setId( scheduleDTO.getId() );
        schedule.setDate( scheduleDTO.getDate() );
        Set<EmployeeSkill> set = scheduleDTO.getActivities();
        if ( set != null ) {
            schedule.setActivities( new HashSet<EmployeeSkill>( set ) );
        }

        toSchedule( schedule, scheduleDTO );

        return schedule;
    }

    @Override
    public List<ScheduleDTO> toScheduleDTOList(List<Schedule> scheduleList) {
        if ( scheduleList == null ) {
            return null;
        }

        List<ScheduleDTO> list = new ArrayList<ScheduleDTO>( scheduleList.size() );
        for ( Schedule schedule : scheduleList ) {
            list.add( toScheduleDTO( schedule ) );
        }

        return list;
    }
}
