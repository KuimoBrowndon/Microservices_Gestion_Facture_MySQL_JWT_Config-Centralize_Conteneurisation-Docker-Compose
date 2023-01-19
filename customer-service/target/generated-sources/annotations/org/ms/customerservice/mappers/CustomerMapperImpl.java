package org.ms.customerservice.mappers;

import javax.annotation.Generated;
import org.ms.customerservice.dto.CustomerRequestDTO;
import org.ms.customerservice.dto.CustomerResponseDTO;
import org.ms.customerservice.entities.Customer;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-04T12:08:45+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerResponseDTO customerToCustomerResponseDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();

        customerResponseDTO.setId( customer.getId() );
        customerResponseDTO.setName( customer.getName() );
        customerResponseDTO.setEmail( customer.getEmail() );

        return customerResponseDTO;
    }

    @Override
    public Customer customerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO) {
        if ( customerRequestDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setName( customerRequestDTO.getName() );
        customer.setEmail( customerRequestDTO.getEmail() );

        return customer;
    }
}
