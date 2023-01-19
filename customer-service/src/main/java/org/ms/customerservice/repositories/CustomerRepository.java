package org.ms.customerservice.repositories;

import org.ms.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
    Customer findCustomerByEmail(String email);
}
