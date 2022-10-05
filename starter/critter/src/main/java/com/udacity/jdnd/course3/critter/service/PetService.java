package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.mapper.PetMapper;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetMapper petMapper;

    public PetDTO savePet(PetDTO petDTO){
        return petMapper.toPetDTO(petRepository.save(petMapper.toPet(petDTO)));
    }

    public PetDTO getPet(long petId){
        return petMapper.toPetDTO(petRepository.findById(petId).orElseThrow(() -> new EntityNotFoundException()));
    }

    public List<PetDTO> getPets(){
        return petMapper.toPetDTOList(petRepository.findAll());
    }

    public List<PetDTO> getPetsByOwner(long ownerId){
        return petMapper.toPetDTOList(petRepository.findByOwnerId(ownerId));
    }

    public PetDTO toPestDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }
}
