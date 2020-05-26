package com.emart.item.service.itemservice.controller;

import java.util.List;

import com.emart.item.service.itemservice.service.CategoryService;
import com.emart.item.service.itemservice.dto.CategoryDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("")
    public List<CategoryDto> getAll() {
        return categoryService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<CategoryDto>(
            categoryService.addCategory(categoryDto),
            HttpStatus.CREATED
        );
    }
}