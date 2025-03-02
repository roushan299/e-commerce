package com.roushan.ecommerce.mapper;

import com.roushan.ecommerce.dto.ProductPurchaseResponse;
import com.roushan.ecommerce.dto.ProductRequest;
import com.roushan.ecommerce.dto.ProductResponse;
import com.roushan.ecommerce.model.Category;
import com.roushan.ecommerce.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        Product product = Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .availableQuantity(request.availableQuantity())
                .category(Category.builder()
                        .id(request.category().getId())
                        .description(request.category().getDescription())
                        .name(request.category().getName())
                        .build()).build();
        return product;
    }

    public static ProductResponse toProductResponse(Product product) {
        ProductResponse response = new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription());
        return response;
    }

    public ProductPurchaseResponse toPurchasePurchaseResponse(Product product, double quantity) {
            ProductPurchaseResponse productPurchaseResponse = new ProductPurchaseResponse(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    quantity
            );
            return productPurchaseResponse;
    }
}
