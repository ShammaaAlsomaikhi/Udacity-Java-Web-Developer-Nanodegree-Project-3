package com.udacity.jdnd.course3.critter.mapper;


import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.user.Customer;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper {

    @Autowired
    private PetRepository petRepository;

    public abstract CustomerDTO toCustomerDTO(Customer customer);

    public abstract Customer toCustomer(CustomerDTO customerDTO);

    public abstract List<CustomerDTO> toCustomerDTOList(List<Customer> customerList);

    @AfterMapping
    void toCustomerDTO(@MappingTarget CustomerDTO customerDTO, Customer customer){
      if(customer.getPets() != null) {
          List<Long> petIds = new ArrayList<>();
          for (int i = 0; i < customer.getPets().size(); i++) {
              petIds.add(i, customer.getPets().get(i).getId());
          }
          customerDTO.setPetIds(petIds);
      }
    }
    @AfterMapping
    void toCustomer(@MappingTarget Customer customer, CustomerDTO customerDTO){
        if(customerDTO.getPetIds() != null) {
            List<Pet> pets = new ArrayList<>();
            for (int i = 0; i < customerDTO.getPetIds().size(); i++) {
                pets.add(i,petRepository.findById(customerDTO.getPetIds().get(i)).orElseThrow(() -> new EntityNotFoundException()));
            }
            customer.setPets(pets);
        }
    }

    @AfterMapping
    void toCustomerDTOList(@MappingTarget List<CustomerDTO> customerDTOList, List<Customer> customerList){
        if (customerList != null) {
            for (int i = 0; i < customerList.size(); i++) {
                List<Long> petIds = new ArrayList<>();
                if (customerList.get(i).getPets() != null) {
                    for (int j = 0; j < customerList.get(i).getPets().size(); j++) {
                        petIds.add(i, customerList.get(i).getPets().get(j).getId());
                    }
                    customerDTOList.get(i).setPetIds(petIds);
                }
            }
        }
    }

}
