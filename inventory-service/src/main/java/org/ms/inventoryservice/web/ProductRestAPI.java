package org.ms.inventoryservice.web;

import org.ms.inventoryservice.dto.ProductRequestDTO;
import org.ms.inventoryservice.dto.ProductResponseDTO;
import org.ms.inventoryservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;
@RestController
@RequestMapping(path = "/api")
public class ProductRestAPI {
    private final ProductService productService;

    public ProductRestAPI(ProductService productService){
        this.productService = productService;
    }
    @GetMapping(path = "/products")
    public ResponseEntity<List<ProductResponseDTO>> allProducts(){
        return status(HttpStatus.OK).body(productService.listProducts());
    }
    @PostMapping(path = "/products")
    public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody ProductRequestDTO productRequestDTO){
        return status(HttpStatus.OK).body(productService.saveProduct(productRequestDTO));
    }
    @GetMapping(path = "/products/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable String id){
        return status(HttpStatus.OK).body(productService.getProduct(id));
    }
    @PutMapping(path = "/products/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable String id, @RequestBody ProductRequestDTO productRequestDTO){
        return status(HttpStatus.OK).body(productService.updateProduct(id, productRequestDTO));
    }
    @DeleteMapping(path = "/products/{id}")
    public void delete(@PathVariable String id){
        productService.deleteProduct(id);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
