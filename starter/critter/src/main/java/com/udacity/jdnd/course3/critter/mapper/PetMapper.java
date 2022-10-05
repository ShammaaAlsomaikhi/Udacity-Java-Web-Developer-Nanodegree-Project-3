package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PetMapper {
    @Autowired
    private CustomerRepository customerRepository;
    @Mapping(target = "ownerId", ignore = true)
    public abstract PetDTO toPetDTO(Pet pet);

    public abstract Pet toPet(PetDTO petDTO);

    public abstract List<PetDTO> toPetDTOList(List<Pet> petList);

    @AfterMapping
    void toPetDTO(@MappingTarget PetDTO petDTO, Pet pet){
        petDTO.setOwnerId(pet.getOwner().getId());
    }
    @AfterMapping
    void toPet(@MappingTarget Pet pet, PetDTO petDTO){
        pet.setOwner(customerRepository.findById(petDTO.getOwnerId()).orElseThrow(() -> new EntityNotFoundException()));
    }

    @AfterMapping
    void toPetDTOList(@MappingTarget List<PetDTO> petDTOList, List<Pet> petList){
        if(petList != null) {
            for (int i = 0; i < petList.size(); i++) {
                petDTOList.get(i).setOwnerId(petList.get(i).getOwner().getId());
            }
        }
    }
}
