package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.entity.user.Employee;
import com.udacity.jdnd.course3.critter.mapper.EmployeeMapper;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        return employeeMapper.toEmployeeDTO(employeeRepository.save(employeeMapper.toEmployee(employeeDTO)));
    }

    public EmployeeDTO getEmployee(long employeeId) {
        return employeeMapper.toEmployeeDTO(employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException()));
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException());
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO requestDTO){
        List<Employee> availableEmployees = new ArrayList<>();
        List<Employee> employees = employeeRepository.findEmployeesByDaysAvailable(requestDTO.getDate().getDayOfWeek());

        for (Employee employee : employees) {
            if (employee.getSkills().containsAll(requestDTO.getSkills())) {
                availableEmployees.add(employee);
            }
        }
        return employeeMapper.toEmployeeDTOList(availableEmployees);
    }
}
