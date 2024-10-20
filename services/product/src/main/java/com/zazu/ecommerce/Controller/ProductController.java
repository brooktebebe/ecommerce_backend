package com.zazu.ecommerce.Controller;

import com.zazu.ecommerce.Dtos.Request.ProductPurchaseRequest;
import com.zazu.ecommerce.Dtos.Request.ProductRequest;
import com.zazu.ecommerce.Dtos.Response.ProductPurchaseResponse;
import com.zazu.ecommerce.Dtos.Response.ProductResponse;
import com.zazu.ecommerce.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(@RequestBody List<ProductPurchaseRequest> purchaseRequestList) {
        return ResponseEntity.ok(productService.purchaseProduct(purchaseRequestList));
    }
    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse>findById(@PathVariable("product-id") Integer productId){
        return ResponseEntity.ok(productService.findById(productId));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>>findAll(){
        return ResponseEntity.ok(productService.findAll());
    }
}
