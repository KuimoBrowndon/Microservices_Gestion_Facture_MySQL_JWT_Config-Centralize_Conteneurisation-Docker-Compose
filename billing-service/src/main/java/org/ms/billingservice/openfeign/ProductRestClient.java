package org.ms.billingservice.openfeign;

import org.ms.billingservice.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

//@FeignClient(name = "INVENTORY-SERVICE", url = "https://inventory-service-ms.herokuapp.com")
@FeignClient(name = "INVENTORY-SERVICE", url = "${configs-billing.INVENTORY_LINK}")
public interface ProductRestClient {
    @GetMapping(path = "/api/products/{id}")
    Product getProduct(@RequestHeader("Authorization") String BearerToken, @PathVariable(name = "id") String id);
    @GetMapping(path = "/api/products")
    List<Product> allProducts(@RequestHeader("Authorization") String BearerToken);
}
