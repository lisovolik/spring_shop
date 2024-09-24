package com.lisovolik.spring_shop.controllers;

import com.lisovolik.spring_shop.models.ServerResponseForList;
import com.lisovolik.spring_shop.models.dto.product.CreateProductDto;
import com.lisovolik.spring_shop.models.dto.product.ProductDto;
import com.lisovolik.spring_shop.models.dto.product.UpdateProductDto;
import com.lisovolik.spring_shop.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Alexandr Lisovolik on  13.09.2024
 */

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ServerResponseForList<ProductDto>> getAllProducts(
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset
    ){
        return productService.getAllProducts(limit, offset);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PutMapping()
    public ResponseEntity<ProductDto> updateProduct(@RequestBody UpdateProductDto product){
        return productService.updateProduct(product);
    }

    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductDto product){
        return productService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
}
