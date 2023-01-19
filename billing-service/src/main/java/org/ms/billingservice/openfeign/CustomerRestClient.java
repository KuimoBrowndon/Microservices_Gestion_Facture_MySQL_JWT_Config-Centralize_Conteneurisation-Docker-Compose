package org.ms.billingservice.openfeign;

import org.ms.billingservice.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

//@FeignClient(name = "CUSTOMER-SERVICE", url = "https://customer-service-msbk.herokuapp.com/")
@FeignClient(name = "CUSTOMER-SERVICE", url = "${configs-billing.CUSTOMER_LINK}")
public interface CustomerRestClient {
    @GetMapping(path = "/api/customers/{id}")
    Customer getCustomer(@RequestHeader("Authorization") String BearerToken, @PathVariable(name = "id") String id);
    @GetMapping(path = "/api/customers")
    List<Customer> allCustomers(@RequestHeader("Authorization") String BearerToken);
}
