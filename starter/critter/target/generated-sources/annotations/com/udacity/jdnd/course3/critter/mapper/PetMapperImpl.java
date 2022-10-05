package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Pet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-23T01:40:49+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3.1 (BellSoft)"
)
@Component
public class PetMapperImpl extends PetMapper {

    @Override
    public PetDTO toPetDTO(Pet pet) {
        if ( pet == null ) {
            return null;
        }

        PetDTO petDTO = new PetDTO();

        petDTO.setType( pet.getType() );
        petDTO.setName( pet.getName() );
        petDTO.setBirthDate( pet.getBirthDate() );
        petDTO.setNotes( pet.getNotes() );
        if ( pet.getId() != null ) {
            petDTO.setId( pet.getId() );
        }

        toPetDTO( petDTO, pet );

        return petDTO;
    }

    @Override
    public Pet toPet(PetDTO petDTO) {
        if ( petDTO == null ) {
            return null;
        }

        Pet pet = new Pet();

        pet.setId( petDTO.getId() );
        pet.setName( petDTO.getName() );
        pet.setType( petDTO.getType() );
        pet.setBirthDate( petDTO.getBirthDate() );
        pet.setNotes( petDTO.getNotes() );

        toPet( pet, petDTO );

        return pet;
    }

    @Override
    public List<PetDTO> toPetDTOList(List<Pet> petList) {
        if ( petList == null ) {
            return null;
        }

        List<PetDTO> list = new ArrayList<PetDTO>( petList.size() );
        for ( Pet pet : petList ) {
            list.add( toPetDTO( pet ) );
        }

        toPetDTOList( list, petList );

        return list;
    }
}
