package org.ms.customerservice.web;

import org.ms.customerservice.dto.CustomerRequestDTO;
import org.ms.customerservice.dto.CustomerResponseDTO;
import org.ms.customerservice.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CustomerRestAPI {
    private final CustomerService customerService;

    public CustomerRestAPI(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping(path = "/customers")
    public List<CustomerResponseDTO> allCustomers(){
        return customerService.listCustomers();
    }
    @PostMapping(path = "/customers")
    public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO){
        return customerService.save(customerRequestDTO);
    }
    @GetMapping(path = "/customers/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable String id){
        return customerService.getCustomer(id);
    }
    @PutMapping(path = "/customers/{id}")
    public CustomerResponseDTO updateCustomer(@PathVariable String id, @RequestBody CustomerRequestDTO customerRequestDTO){
        return customerService.update(id, customerRequestDTO);
    }
    @DeleteMapping(path = "/customers/{id}")
    public void delete(@PathVariable String id){
        customerService.delete(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
