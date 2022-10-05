package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.entity.user.Customer;
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
public class CustomerMapperImpl extends CustomerMapper {

    @Override
    public CustomerDTO toCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        if ( customer.getId() != null ) {
            customerDTO.setId( customer.getId() );
        }
        customerDTO.setName( customer.getName() );
        customerDTO.setPhoneNumber( customer.getPhoneNumber() );
        customerDTO.setNotes( customer.getNotes() );

        toCustomerDTO( customerDTO, customer );

        return customerDTO;
    }

    @Override
    public Customer toCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerDTO.getId() );
        customer.setName( customerDTO.getName() );
        customer.setPhoneNumber( customerDTO.getPhoneNumber() );
        customer.setNotes( customerDTO.getNotes() );

        toCustomer( customer, customerDTO );

        return customer;
    }

    @Override
    public List<CustomerDTO> toCustomerDTOList(List<Customer> customerList) {
        if ( customerList == null ) {
            return null;
        }

        List<CustomerDTO> list = new ArrayList<CustomerDTO>( customerList.size() );
        for ( Customer customer : customerList ) {
            list.add( toCustomerDTO( customer ) );
        }

        toCustomerDTOList( list, customerList );

        return list;
    }
}
