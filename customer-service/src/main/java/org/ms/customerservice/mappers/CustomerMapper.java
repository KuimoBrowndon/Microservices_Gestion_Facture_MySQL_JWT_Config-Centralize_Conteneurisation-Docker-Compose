package org.ms.customerservice.mappers;

import org.mapstruct.Mapper;
import org.ms.customerservice.dto.CustomerRequestDTO;
import org.ms.customerservice.dto.CustomerResponseDTO;
import org.ms.customerservice.entities.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponseDTO customerToCustomerResponseDTO(Customer customer);
    Customer customerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO);
}
