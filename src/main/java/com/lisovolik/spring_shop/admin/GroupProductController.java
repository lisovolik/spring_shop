package com.lisovolik.spring_shop.admin;

import com.lisovolik.spring_shop.entity.product.GroupProduct;
import com.lisovolik.spring_shop.entity.user.CustomUser;
import com.lisovolik.spring_shop.models.CreateGroupProductDto;
import com.lisovolik.spring_shop.services.UserLoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<GroupProduct> update(@RequestBody GroupProduct group){
        return groupProductService.update(group);
    }

    @PostMapping()
    public ResponseEntity<GroupProduct> create(@RequestBody CreateGroupProductDto group){
        return groupProductService.create(group);
    }

    @DeleteMapping()
    public ResponseEntity<String> create(@PathVariable Long id){
        return groupProductService.delete(id);
    }
}
