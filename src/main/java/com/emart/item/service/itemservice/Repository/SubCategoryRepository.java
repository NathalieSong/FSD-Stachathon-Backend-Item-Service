package com.emart.item.service.itemservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.emart.item.service.itemservice.entity.Category;
import com.emart.item.service.itemservice.entity.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, String> {
    List<SubCategory> findByCategory(Category category);
}