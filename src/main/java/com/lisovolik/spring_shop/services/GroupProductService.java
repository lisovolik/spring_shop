package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.GroupProduct;
import com.lisovolik.spring_shop.exceptions.ErrorMessages;
import com.lisovolik.spring_shop.exceptions.GroupProductNotFoundException;
import com.lisovolik.spring_shop.exceptions.NotFoundException;
import com.lisovolik.spring_shop.mapper.GroupProductMapper;
import com.lisovolik.spring_shop.models.dto.groups.CreateGroupProductDto;
import com.lisovolik.spring_shop.models.dto.groups.GroupProductDto;
import com.lisovolik.spring_shop.repositories.GroupProductRepository;
import com.lisovolik.spring_shop.validators.GroupProductValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final GroupProductMapper groupProductMapper;

    public ResponseEntity<List<GroupProductDto>> getAllGroups(){
        List<GroupProductDto> response = groupProductMapper.toGroupResponseList(repository.findAllByDeletedFalse());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    public ResponseEntity<GroupProductDto> create(CreateGroupProductDto groupDto){
        GroupProductValidator.validate(groupDto);

        GroupProduct group = new GroupProduct();
        group.setName(groupDto.getName());
        group.setDescription(groupDto.getDescription());

        GroupProductDto response = groupProductMapper.toGroupResponse(repository.save(group));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    public ResponseEntity<String> delete(Long id){
        Optional<GroupProduct> optional = repository.findById(id);
        if (optional.isEmpty()){
            throw new NotFoundException(ErrorMessages.NOT_FOUND.getMessage());
        }
        GroupProduct groupProduct = optional.get();
        groupProduct.setDeleted(true);
        repository.save(groupProduct);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Deleted");
    }

    public ResponseEntity<GroupProductDto> update(GroupProductDto group){
        GroupProductValidator.validate(group);

        Optional<GroupProduct> optional = repository.findByIdAndDeletedFalse(group.getId());
        if (optional.isEmpty()){
            throw new GroupProductNotFoundException(ErrorMessages.GROUP_PRODUCT_NOT_FOUND.getMessage());
        }
        GroupProduct saved = optional.get();
        saved.setName(group.getName());
        saved.setDescription(group.getDescription());

        GroupProductDto response = groupProductMapper.toGroupResponse(repository.save(saved));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    public GroupProduct getGroupProduct(Long groupId){
        Optional<GroupProduct> optional = repository.findByIdAndDeletedFalse(groupId);
        if (optional.isEmpty()){
            throw new NotFoundException(ErrorMessages.GROUP_PRODUCT_NOT_FOUND.getMessage());
        }
        return optional.get();
    }
}
