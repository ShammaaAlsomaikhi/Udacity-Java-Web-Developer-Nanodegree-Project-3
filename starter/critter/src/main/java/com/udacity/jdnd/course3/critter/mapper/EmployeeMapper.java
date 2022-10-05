package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entity.user.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    public abstract EmployeeDTO toEmployeeDTO(Employee employee);

    public abstract Employee toEmployee(EmployeeDTO employeeDTO);

    public abstract List<EmployeeDTO> toEmployeeDTOList(List<Employee> employeeList);
}
