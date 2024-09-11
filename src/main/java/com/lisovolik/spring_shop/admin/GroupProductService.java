package com.lisovolik.spring_shop.admin;

import com.lisovolik.spring_shop.entity.product.GroupProduct;
import com.lisovolik.spring_shop.exceptions.ErrorMessages;
import com.lisovolik.spring_shop.exceptions.GroupProductNotFoundException;
import com.lisovolik.spring_shop.exceptions.NotFoundException;
import com.lisovolik.spring_shop.exceptions.UserNotFoundException;
import com.lisovolik.spring_shop.models.CreateGroupProductDto;
import com.lisovolik.spring_shop.validators.GroupProductValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Service
@AllArgsConstructor
public class GroupProductService {
    private final GroupProductRepository repository;

    public ResponseEntity<List<GroupProduct>> getAllGroups(){
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<GroupProduct> create(CreateGroupProductDto groupDto){
        GroupProductValidator.validate(groupDto);

        GroupProduct group = new GroupProduct();
        group.setName(groupDto.getName());
        group.setDescription(groupDto.getDescription());
        return ResponseEntity.ok(repository.save(group));
    }

    public ResponseEntity<String> delete(Long id){
        Optional<GroupProduct> optional = repository.findById(id);
        if (optional.isEmpty()){
            throw new NotFoundException(ErrorMessages.NOT_FOUND.getMessage());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
    }

    public ResponseEntity<GroupProduct> update(GroupProduct group){
        GroupProductValidator.validate(group);

        Optional<GroupProduct> optional = repository.findById(group.getId());
        if (optional.isEmpty()){
            throw new GroupProductNotFoundException(ErrorMessages.GROUP_PRODUCT_NOT_FOUND.getMessage());
        }

        return ResponseEntity.ok(repository.save(group));
    }
}
