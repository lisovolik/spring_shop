package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.models.LimitOffsetPageRequest;
import com.lisovolik.spring_shop.models.ServerResponseForList;
import com.lisovolik.spring_shop.repositories.GroupProductRepository;
import com.lisovolik.spring_shop.entity.GroupProduct;
import com.lisovolik.spring_shop.entity.Product;
import com.lisovolik.spring_shop.exceptions.ErrorMessages;
import com.lisovolik.spring_shop.exceptions.NotFoundException;
import com.lisovolik.spring_shop.models.CreateUpdateProductDto;
import com.lisovolik.spring_shop.repositories.ProductRepository;
import com.lisovolik.spring_shop.validators.ProductValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final GroupProductRepository groupRepository;

    public ResponseEntity<ServerResponseForList<Product>> getAllProducts(int limit, int offset){
        //return ResponseEntity.ok(productRepository.findAll(pageable));
        Integer totalCount = (int)productRepository.getCount();
        Pageable pageable = new LimitOffsetPageRequest(offset, limit, Sort.by(Sort.Order.asc("p.name")));
        List<Product> list = productRepository.findAllProducts(pageable);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ServerResponseForList<>(
                        totalCount,
                        limit,
                        offset,
                        list
                ));
    }

    public ResponseEntity<Product> getProductById(Long id){
        Product product = findProductById(id);
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<Product> updateProduct(Long id, CreateUpdateProductDto product){
        ProductValidator.validate(product);
        Product findProduct = findProductById(id);
        return ResponseEntity.ok(save(product, findProduct.getId()));
    }

    public ResponseEntity<Product> createProduct(CreateUpdateProductDto product){
        return ResponseEntity.ok(save(product, null));
    }

    public ResponseEntity<String> deleteProduct(Long id){
        findProductById(id);
        productRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
    }

    private Product save(CreateUpdateProductDto dto, Long id){
        Product saveProduct = new Product();
        if (id != null){
            saveProduct.setId(id);
        }
        saveProduct.setGroupProduct(getGroupProduct(dto.getGroupId()));
        saveProduct.setName(dto.getName());
        saveProduct.setDescription(dto.getDescription());
        return productRepository.save(saveProduct);
    }

    private GroupProduct getGroupProduct(Long groupId){
        Optional<GroupProduct> optional = groupRepository.findById(groupId);
        if (optional.isEmpty()){
            throw new NotFoundException(ErrorMessages.GROUP_PRODUCT_NOT_FOUND.getMessage());
        }
        return optional.get();
    }

    private Product findProductById(Long productId){
        Optional<Product> optional = productRepository.findById(productId);
        if (optional.isEmpty()){
            throw new NotFoundException(ErrorMessages.PRODUCT_NOT_FOUND.getMessage());
        }
        return optional.get();
    }
}
