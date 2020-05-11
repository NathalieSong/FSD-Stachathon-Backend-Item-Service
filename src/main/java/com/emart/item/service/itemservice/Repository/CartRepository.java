package com.emart.item.service.itemservice.Repository;

import com.emart.item.service.itemservice.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {
    
}