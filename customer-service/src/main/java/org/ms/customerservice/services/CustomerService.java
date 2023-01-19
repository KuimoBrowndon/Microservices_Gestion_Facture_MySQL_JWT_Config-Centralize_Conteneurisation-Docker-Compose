package org.ms.customerservice.services;

import org.ms.customerservice.dto.CustomerRequestDTO;
import org.ms.customerservice.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO getCustomer(String id);
    CustomerResponseDTO update(String id, CustomerRequestDTO customerRequestDTO);
    List<CustomerResponseDTO> listCustomers();
    void delete(String id);
}