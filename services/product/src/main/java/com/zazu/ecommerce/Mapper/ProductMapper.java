package com.zazu.ecommerce.Mapper;

import com.zazu.ecommerce.Dtos.Request.ProductRequest;
import com.zazu.ecommerce.Dtos.Response.ProductPurchaseResponse;
import com.zazu.ecommerce.Dtos.Response.ProductResponse;
import com.zazu.ecommerce.Entity.Product;
import com.zazu.ecommerce.Entity.ProductCategory;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest productRequest) {
        return Product.builder().id(productRequest.id()).name(productRequest.name()).description(productRequest.description()).availableQuantity(productRequest.availableQuantity()).price(productRequest.price()).productCategory(ProductCategory.builder().id(productRequest.product_category_id()).build()).build();
    }

    public ProductResponse  toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getProductCategory().getId(),
                product.getProductCategory().getName(),
                product.getProductCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailableQuantity()
        );
    }
}
