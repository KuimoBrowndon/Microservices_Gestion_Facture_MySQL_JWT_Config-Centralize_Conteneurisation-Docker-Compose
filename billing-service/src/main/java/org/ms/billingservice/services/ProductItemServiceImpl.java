package org.ms.billingservice.services;

import org.ms.billingservice.dto.ProductItemRequestDTO;
import org.ms.billingservice.dto.ProductItemResponseDTO;
import org.ms.billingservice.entities.Product;
import org.ms.billingservice.entities.ProductItem;
import org.ms.billingservice.exceptions.InvoiceNotFoundException;
import org.ms.billingservice.mappers.ProductItemMapper;
import org.ms.billingservice.openfeign.ProductRestClient;
import org.ms.billingservice.repositories.InvoiceRepository;
import org.ms.billingservice.repositories.ProductItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductItemServiceImpl implements ProductItemService {
    private final ProductItemRepository productItemRepository;
    private final ProductItemMapper productItemMapper;
    private final InvoiceRepository invoiceRepository;
    private final ProductRestClient productRestClient;


    public ProductItemServiceImpl(ProductItemRepository productItemRepository, ProductItemMapper productItemMapper, InvoiceRepository invoiceRepository, ProductRestClient productRestClient) {
        this.productItemRepository = productItemRepository;
        this.productItemMapper = productItemMapper;
        this.invoiceRepository = invoiceRepository;
        this.productRestClient = productRestClient;
    }

    @Override
    public ProductItemResponseDTO saveProductItem(String BearerToken, ProductItemRequestDTO productItemRequestDTO) {
        if(productItemRequestDTO.getProductId().equals("") ||
                productItemRequestDTO.getPrice().equals(BigDecimal.valueOf(0)) ||
                        productItemRequestDTO.getInvoiceId().equals(""))
            throw new InvoiceNotFoundException("Vous n'avez pas envoyé toutes les données requises");
        if (invoiceRepository.findById(productItemRequestDTO.getInvoiceId()).orElse(null) == null)
            throw new InvoiceNotFoundException("Cette facture n'existe pas");
        Product product = productRestClient.getProduct(BearerToken,productItemRequestDTO.getProductId());
        if (product == null)
            throw new InvoiceNotFoundException("Ce produit n'existe pas.");
        ProductItem productItem = productItemMapper.ProductItemRequestDTOToProductItem(productItemRequestDTO);
        productItem.setId(UUID.randomUUID().toString());
        ProductItem productItemSave = productItemRepository.save(productItem);
        productItemSave.setProduct(product);
        return productItemMapper.ProductItemToProductItemResponseDTO(productItemSave);
    }

    @Override
    public ProductItemResponseDTO getProductItem(String BearerToken, String id) {
        ProductItem productItem = productItemRepository.findById(id).orElse(null);
        if(productItem == null)
            throw new InvoiceNotFoundException("Item de produit introuvable");
        Product product = productRestClient.getProduct(BearerToken,productItem.getProductId());
        productItem.setProduct(product);
        return productItemMapper.ProductItemToProductItemResponseDTO(productItem);
    }

    @Override
    public List<ProductItemResponseDTO> listProductItem(String BearerToken) {
        List<ProductItem> productItems = productItemRepository.findAll();
        for (ProductItem productItem:productItems){
            Product product = productRestClient.getProduct(BearerToken,productItem.getProductId());
            productItem.setProduct(product);
        }
        return productItems.stream()
                .map(productItemMapper::ProductItemToProductItemResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductItemResponseDTO> productItemByInvoiceId(String BearerToken, String invoiceId) {
        if (invoiceRepository.findById(invoiceId).orElse(null) == null)
            throw new InvoiceNotFoundException("Cette facture n'existe pas");
        List<ProductItem> productItems = productItemRepository.findByInvoiceId(invoiceId);
        for (ProductItem productItem:productItems){
            Product product = productRestClient.getProduct(BearerToken,productItem.getProductId());
            productItem.setProduct(product);
        }
        return productItems.stream()
                .map(productItemMapper::ProductItemToProductItemResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductItemResponseDTO> productItemByProductId(String BearerToken, String productId) {
        Product product = productRestClient.getProduct(BearerToken,productId);
        if (product == null)
            throw new InvoiceNotFoundException("Ce produit n'est pas dans un item de facture");
        List<ProductItem> productItems = productItemRepository.findByProductId(productId);
        for (ProductItem productItem:productItems)
            productItem.setProduct(product);
        return productItems.stream()
                .map(productItemMapper::ProductItemToProductItemResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductItemResponseDTO updateProductItem(String BearerToken, String productItemId, ProductItemRequestDTO productItemRequestDTO) {
        if(productItemRequestDTO.getProductId().equals("") ||
                productItemRequestDTO.getPrice().equals(BigDecimal.valueOf(0)) ||
                productItemRequestDTO.getInvoiceId().equals(""))
            throw new InvoiceNotFoundException("Vous n'avez pas envoyé toutes les données requises");
        ProductItem productItemUpdate = productItemRepository.findById(productItemId).orElse(null);
        if(productItemUpdate == null)
            throw  new InvoiceNotFoundException("Cet item de facture n'existe pas");
        if (invoiceRepository.findById(productItemRequestDTO.getInvoiceId()).orElse(null) == null)
            throw new InvoiceNotFoundException("Cette facture n'existe pas");Product product = productRestClient.getProduct(BearerToken,productItemRequestDTO.getProductId());
        if (product == null)
            throw new InvoiceNotFoundException("Ce produit n'existe pas.");
        ProductItem productItem = productItemMapper.ProductItemRequestDTOToProductItem(productItemRequestDTO);
        productItem.setId(productItemId);
        productItemUpdate = productItemRepository.save(productItem);
        productItemUpdate.setProduct(product);
        return productItemMapper.ProductItemToProductItemResponseDTO(productItemUpdate);
    }

    @Override
    public void deleteProductItem(String productItemId) {
        if(productItemRepository.findById(productItemId).isPresent())
            productItemRepository.deleteById(productItemId);
        else throw new InvoiceNotFoundException("Cet item de produit n'existe pas");

    }
}
