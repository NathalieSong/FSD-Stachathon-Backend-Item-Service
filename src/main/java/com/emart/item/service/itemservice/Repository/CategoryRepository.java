package com.emart.item.service.itemservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.emart.item.service.itemservice.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
    
}