package com.emart.item.service.itemservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.emart.item.service.itemservice.Entity.Category;
import com.emart.item.service.itemservice.Entity.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, String> {
    List<SubCategory> findByCategory(Category category);
}