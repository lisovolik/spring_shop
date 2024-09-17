package com.lisovolik.spring_shop.repositories;

import com.lisovolik.spring_shop.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandr Lisovolik on  17.09.2024
 */

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAddressByUserId(Long userId);
}
