package com.emart.item.service.itemservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.emart.item.service.itemservice.Entity.Item;

public interface ItemRepository extends JpaRepository<Item, String> {
    
}