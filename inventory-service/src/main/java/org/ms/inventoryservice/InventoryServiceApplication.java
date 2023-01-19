package org.ms.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    /*@Bean
    CommandLineRunner start(ProductService productService){
        return args -> {
            productService.saveProduct(new ProductRequestDTO("Clavier", BigDecimal.valueOf(1500)));
            productService.saveProduct(new ProductRequestDTO("Souris sans fils",BigDecimal.valueOf(3000)));
        };
    }*/

}
