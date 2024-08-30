package com.jh.ecommerce.product.product;

import com.jh.ecommerce.product.exception.ProductException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;


    public ProductResponse createProduct(ProductCreateRequest request) {
        Product product = mapper.toProduct(request);
        return mapper.toProductResponse(productRepository.save(product));
    }


    public ProductResponse findById(Long id) {
        return productRepository.findById(id).map(mapper::toProductResponse).orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: " + id));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }


    @Transactional(rollbackFor = ProductException.class)
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        List<Long> productIds = request.stream().map(ProductPurchaseRequest::productId).toList();
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);

        if (productIds.size() != storedProducts.size()) {
            throw new ProductException("One or more products does not exist");
        }
        List<ProductPurchaseRequest> sortedRequest = request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();

        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();
        for (int i = 0; i < storedProducts.size(); i++) {
            Product product = storedProducts.get(i);
            ProductPurchaseRequest productRequest = sortedRequest.get(i);
            if (product.getQuantityInStock() < productRequest.quantity()) {
                throw new ProductException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }
            int newAvailableQuantity = product.getQuantityInStock() - productRequest.quantity();
            product.setQuantityInStock(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }
}
