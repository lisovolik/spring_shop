package com.lisovolik.spring_shop.controllers;

import com.lisovolik.spring_shop.entity.GroupProduct;
import com.lisovolik.spring_shop.models.CreateGroupProductDto;
import com.lisovolik.spring_shop.services.GroupProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@RestController
@AllArgsConstructor
@RequestMapping("/groups")

public class GroupProductController {
    private final GroupProductService groupProductService;

    @GetMapping()
    public ResponseEntity<List<GroupProduct>> getAllGroups(){
        return groupProductService.getAllGroups();
    }

    @PutMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GroupProduct> update(@RequestBody GroupProduct group){
        return groupProductService.update(group);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GroupProduct> create(@RequestBody CreateGroupProductDto group){
        return groupProductService.create(group);
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return groupProductService.delete(id);
    }
}
