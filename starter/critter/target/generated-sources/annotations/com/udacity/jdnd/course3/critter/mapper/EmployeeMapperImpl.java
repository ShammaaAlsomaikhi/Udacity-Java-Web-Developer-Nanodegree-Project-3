package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entity.user.Employee;
import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import java.time.DayOfWeek;
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
public class EmployeeMapperImpl extends EmployeeMapper {

    @Override
    public EmployeeDTO toEmployeeDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        if ( employee.getId() != null ) {
            employeeDTO.setId( employee.getId() );
        }
        employeeDTO.setName( employee.getName() );
        Set<EmployeeSkill> set = employee.getSkills();
        if ( set != null ) {
            employeeDTO.setSkills( new HashSet<EmployeeSkill>( set ) );
        }
        Set<DayOfWeek> set1 = employee.getDaysAvailable();
        if ( set1 != null ) {
            employeeDTO.setDaysAvailable( new HashSet<DayOfWeek>( set1 ) );
        }

        return employeeDTO;
    }

    @Override
    public Employee toEmployee(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeDTO.getId() );
        employee.setName( employeeDTO.getName() );
        Set<EmployeeSkill> set = employeeDTO.getSkills();
        if ( set != null ) {
            employee.setSkills( new HashSet<EmployeeSkill>( set ) );
        }
        Set<DayOfWeek> set1 = employeeDTO.getDaysAvailable();
        if ( set1 != null ) {
            employee.setDaysAvailable( new HashSet<DayOfWeek>( set1 ) );
        }

        return employee;
    }

    @Override
    public List<EmployeeDTO> toEmployeeDTOList(List<Employee> employeeList) {
        if ( employeeList == null ) {
            return null;
        }

        List<EmployeeDTO> list = new ArrayList<EmployeeDTO>( employeeList.size() );
        for ( Employee employee : employeeList ) {
            list.add( toEmployeeDTO( employee ) );
        }

        return list;
    }
}
