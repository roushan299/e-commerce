package com.roushan.ecommerce.service;

import com.roushan.ecommerce.dto.ProductPurchaseRequest;
import com.roushan.ecommerce.dto.ProductPurchaseResponse;
import com.roushan.ecommerce.dto.ProductRequest;
import com.roushan.ecommerce.dto.ProductResponse;
import com.roushan.ecommerce.exception.ProductPurchaseException;
import com.roushan.ecommerce.mapper.ProductMapper;
import com.roushan.ecommerce.model.Product;
import com.roushan.ecommerce.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    public Integer createProduct(ProductRequest request) {
        Product product =  mapper.toProduct(request);
        repository.save(product);
        return product.getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        List<Integer> productIds = request.stream().map(ProductPurchaseRequest::id).toList();

        List<Product> storedProducts = repository.findAllByIdInOrderById(productIds);

        if(storedProducts.size() != productIds.size()){
            throw new ProductPurchaseException("One or more products doesn't exits");
        }
        List<ProductPurchaseRequest> storedRequest = request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::id))
                .toList();
        ArrayList<ProductPurchaseResponse> purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for(int i=0;i<storedRequest.size();i++){
            Product product = storedProducts.get(i);
            ProductPurchaseRequest productRequest = storedRequest.get(i);
            if(product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with id::"+productRequest.id());
            }
            double newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchasedProducts.add(mapper.toPurchasePurchaseResponse(product, productRequest.quantity()));

        }
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        ProductResponse response = repository.findById(productId)
                .map(ProductMapper::toProductResponse)
                .orElseThrow(()->new EntityNotFoundException("Product not found with the Id:: "+productId));
        return response;
    }

    public List<ProductResponse> finAll() {
        List<Product> productList = repository.findAll();
        List<ProductResponse> productResponseList = productList.stream()
                .map(ProductMapper::toProductResponse)
                .collect(Collectors.toList());
        return productResponseList;

    }
}
