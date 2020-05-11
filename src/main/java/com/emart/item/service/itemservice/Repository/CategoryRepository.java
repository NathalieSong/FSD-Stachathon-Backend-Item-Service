package com.emart.item.service.itemservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.emart.item.service.itemservice.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
    
}