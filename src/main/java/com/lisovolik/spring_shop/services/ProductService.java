package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.Product;
import com.lisovolik.spring_shop.exceptions.ErrorMessages;
import com.lisovolik.spring_shop.exceptions.NotFoundException;
import com.lisovolik.spring_shop.mapper.ProductMapper;
import com.lisovolik.spring_shop.models.LimitOffsetPageRequest;
import com.lisovolik.spring_shop.models.ServerResponseForList;
import com.lisovolik.spring_shop.models.dto.product.BaseProductDto;
import com.lisovolik.spring_shop.models.dto.product.CreateProductDto;
import com.lisovolik.spring_shop.models.dto.product.ProductDto;
import com.lisovolik.spring_shop.models.dto.product.UpdateProductDto;
import com.lisovolik.spring_shop.repositories.ProductRepository;
import com.lisovolik.spring_shop.validators.ProductValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandr Lisovolik on  11.09.2024
 */

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final GroupProductService groupService;
    private final ProductMapper productMapper;

    public ResponseEntity<ServerResponseForList<ProductDto>> getAllProducts(int limit, int offset){
        Integer totalCount = (int)productRepository.getCount();
        Pageable pageable = new LimitOffsetPageRequest(offset, limit, Sort.by(Sort.Order.asc("p.name")));
        List<ProductDto> products = productMapper.toProductResponseList(productRepository.findAllProducts(pageable));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ServerResponseForList<>(
                        totalCount,
                        limit,
                        offset,
                        products
                ));
    }

    public ResponseEntity<ProductDto> getProductById(Long id){
        ProductDto response = productMapper.toProductResponse(findProductById(id));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ProductDto> updateProduct(UpdateProductDto product){
        ProductValidator.validate(product);
        Product findProduct = findProductById(product.getId());
        Product saved = save(product, findProduct.getId());
        ProductDto response = productMapper.toProductResponse(saved);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    public ResponseEntity<ProductDto> createProduct(CreateProductDto product){
        ProductValidator.validate(product);
        ProductDto response = productMapper.toProductResponse(save(product, null));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    public ResponseEntity<String> deleteProduct(Long id){
        Product product = findProductById(id);
        product.setDeleted(true);
        productRepository.save(product);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Deleted");
    }

    private Product save(BaseProductDto dto, Long id){
        Product saveProduct = new Product();
        if (id != null){
            saveProduct.setId(id);
        }
        saveProduct.setGroupProduct(groupService.getGroupProduct(dto.getGroupId()));
        saveProduct.setName(dto.getName());
        saveProduct.setDescription(dto.getDescription());
        return productRepository.save(saveProduct);
    }



    public Product findProductById(Long productId){
        Optional<Product> optional = productRepository.findProductByIdAndDeletedFalse(productId);
        if (optional.isEmpty()){
            throw new NotFoundException(ErrorMessages.PRODUCT_NOT_FOUND.getMessage());
        }
        return optional.get();
    }
}
