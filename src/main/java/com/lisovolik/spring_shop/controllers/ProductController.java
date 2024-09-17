package com.lisovolik.spring_shop.controllers;

import com.lisovolik.spring_shop.entity.Product;
import com.lisovolik.spring_shop.models.CreateUpdateProductDto;
import com.lisovolik.spring_shop.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Alexandr Lisovolik on  13.09.2024
 */

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody CreateUpdateProductDto product){
        return productService.updateProduct(id, product);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody CreateUpdateProductDto product){
        return productService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
}
