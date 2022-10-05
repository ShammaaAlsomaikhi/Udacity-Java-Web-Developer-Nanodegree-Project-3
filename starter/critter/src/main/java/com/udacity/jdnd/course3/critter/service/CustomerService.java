package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.mapper.CustomerMapper;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        return customerMapper.toCustomerDTO(customerRepository.save(customerMapper.toCustomer(customerDTO)));
    }

    public List<CustomerDTO> getAllCustomers(){
        return customerMapper.toCustomerDTOList(customerRepository.findAll());
    }

    public CustomerDTO getOwnerByPet(long petId){
        return customerMapper.toCustomerDTO(customerRepository.getCustomerByPetID(petId));
    }

}
