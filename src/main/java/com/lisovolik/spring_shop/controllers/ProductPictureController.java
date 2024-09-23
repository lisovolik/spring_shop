package com.lisovolik.spring_shop.controllers;

import com.lisovolik.spring_shop.services.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Alexandr Lisovolik on  17.09.2024
 */

@RestController
@RequiredArgsConstructor
public class ProductPictureController {
    @Value("${product.image.path}")
    private String PRODUCT_IMAGE_PATH;

    private final PictureService pictureService;

    @PostMapping("/product/images/{productId}")
    public ResponseEntity<String> savePicture(@PathVariable Long productId,
                                              @RequestParam("image") MultipartFile file){
        if (!file.getContentType().contains("image")){
            return ResponseEntity.badRequest().body("Should contain image");
        }
        return pictureService.savePicture(PRODUCT_IMAGE_PATH, productId, file);
    }

    @GetMapping("/product/images/{productId}/{imageName}")
    public ResponseEntity<Resource> getPicture(@PathVariable Long productId,
                                               @PathVariable String imageName){
        return pictureService.getPicture(PRODUCT_IMAGE_PATH, productId, imageName);
    }

    @GetMapping(value = "/uploads/images/products/{productId}/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable Long productId,
                                         @PathVariable String imageName) throws IOException {
        return pictureService.getPicture(PRODUCT_IMAGE_PATH, productId, imageName);
    }

    @DeleteMapping("/product/images/{productId}/{imageName}")
    public ResponseEntity<String> deletePicture(@PathVariable Long productId,
                                               @PathVariable String imageName){
        return pictureService.deletePicture(PRODUCT_IMAGE_PATH, productId, imageName);
    }
}
