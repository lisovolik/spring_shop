package com.lisovolik.spring_shop.controllers;

import com.lisovolik.spring_shop.models.ServerResponseForList;
import com.lisovolik.spring_shop.models.dto.product.CreateProductDto;
import com.lisovolik.spring_shop.models.dto.product.ProductDto;
import com.lisovolik.spring_shop.models.dto.product.UpdateProductDto;
import com.lisovolik.spring_shop.models.dto.product_price.AddPriceDto;
import com.lisovolik.spring_shop.models.dto.product_price.PriceDto;
import com.lisovolik.spring_shop.services.ProductPriceService;
import com.lisovolik.spring_shop.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Alexandr Lisovolik on  13.09.2024
 */

@RestController
@AllArgsConstructor
@RequestMapping("/product_price")
public class ProductPriceController {
    private final ProductPriceService priceService;

    @GetMapping("/{id}")
    public ResponseEntity<ServerResponseForList<PriceDto>> getAllPrices(
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset
    ){
        return priceService.getAllPrices(limit, offset);
    }

    @PostMapping("/{id}")
    public ResponseEntity<PriceDto> addProductPrice(@PathVariable Long id, @RequestBody AddPriceDto product){
        return priceService.addProductPrice(id, product);
    }
}
