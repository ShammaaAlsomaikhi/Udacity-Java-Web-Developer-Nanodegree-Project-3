package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(
            "SELECT c from Customer c" +
                    " INNER JOIN Pet p on c.id = p.owner.id" +
                    " WHERE p.id = :petID"
    )
    Customer getCustomerByPetID(@Param("petID") Long petID);
}
