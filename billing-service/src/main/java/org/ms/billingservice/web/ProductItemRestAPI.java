package org.ms.billingservice.web;

import org.ms.billingservice.dto.ProductItemRequestDTO;
import org.ms.billingservice.dto.ProductItemResponseDTO;
import org.ms.billingservice.services.ProductItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class ProductItemRestAPI {
    private final ProductItemService productItemService;

    public ProductItemRestAPI(ProductItemService productItemService){
        this.productItemService = productItemService;
    }

    @GetMapping(path = "/productitems/{id}")
    public ProductItemResponseDTO getProductItem(@RequestHeader("Authorization") String BearerToken, @PathVariable(name = "id") String productItemId){
        return productItemService.getProductItem(BearerToken, productItemId);
    }

    @GetMapping(path = "/productitems")
    public List<ProductItemResponseDTO> listProductItems(@RequestHeader("Authorization") String BearerToken){
        return productItemService.listProductItem(BearerToken);
    }

    @GetMapping(path = "/productitems/ByInvoice/{invoiceId}")
    public List<ProductItemResponseDTO> getProductItemByInvoice(@RequestHeader("Authorization") String BearerToken, @PathVariable String invoiceId){
        return productItemService.productItemByInvoiceId(BearerToken, invoiceId);
    }

    @GetMapping(path = "/productitems/ByProduct/{productId}")
    public List<ProductItemResponseDTO> getProductItemByProduct(@RequestHeader("Authorization") String BearerToken, @PathVariable String productId){
        return productItemService.productItemByProductId(BearerToken, productId);
    }

    @PostMapping(path = "/productitems")
    public ProductItemResponseDTO save(@RequestHeader("Authorization") String BearerToken, @RequestBody ProductItemRequestDTO productItemRequestDTO){
        return productItemService.saveProductItem(BearerToken, productItemRequestDTO);
    }

    @PutMapping(path = "/productitems/{id}")
    public ProductItemResponseDTO updateProductItem(@RequestHeader("Authorization") String BearerToken, @PathVariable String id, @RequestBody ProductItemRequestDTO productItemRequestDTO){
        return productItemService.updateProductItem(BearerToken, id, productItemRequestDTO);
    }

    @DeleteMapping(path = "/productitems/{id}")
    public void delete(@PathVariable String id){
        productItemService.deleteProductItem(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}