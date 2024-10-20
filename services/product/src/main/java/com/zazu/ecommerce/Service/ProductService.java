package com.zazu.ecommerce.Service;

import com.zazu.ecommerce.Dtos.Request.ProductPurchaseRequest;
import com.zazu.ecommerce.Dtos.Request.ProductRequest;
import com.zazu.ecommerce.Dtos.Response.ProductPurchaseResponse;
import com.zazu.ecommerce.Dtos.Response.ProductResponse;
import com.zazu.ecommerce.Exception.ProductPurchaseException;
import com.zazu.ecommerce.Mapper.ProductMapper;
import com.zazu.ecommerce.Repository.ProductRepository;
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
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    public Integer createProduct(ProductRequest productRequest) {
        var product =productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> purchaseRequestList) {
        var productIds = purchaseRequestList.stream().map(ProductPurchaseRequest::productId).toList();
        var storedProducts =productRepository.findAllByIdInOrderById(productIds);
        if(productIds.size() !=storedProducts.size()){
            throw new ProductPurchaseException("One or more product does not exist.");
        }
        var storedRequest =purchaseRequestList.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();
        var purchasedProduct = new ArrayList<ProductPurchaseResponse>();
        for (int i=0; i<storedProducts.size();i++){
            var product =storedProducts.get(i);
            var productRequest =storedRequest.get(i);
            if(product.getAvailableQuantity()<productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID :: "+productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity()-productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProduct.add(productMapper.toProductPurchaseResponse(product,productRequest.quantity()));
        }
        return purchasedProduct;
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId).map(productMapper::toProductResponse).orElseThrow(()-> new EntityNotFoundException("" +
                "Product not found with the ID :: "+productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse).collect(Collectors.toList());
    }
}
