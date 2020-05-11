package com.emart.item.service.itemservice.Controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.emart.item.service.itemservice.Service.SubCategoryService;
import com.emart.item.service.itemservice.dto.SubCategoryDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/subCategory")
public class SubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("/byCategory")
    public List<SubCategoryDto> getByCategory(@RequestParam(value = "categoryId") String categoryId) {
        return subCategoryService.getByCategory(categoryId);
    }

    @PostMapping("")
    public ResponseEntity<SubCategoryDto> addSubCategory(@RequestBody SubCategoryDto subCategoryDto) {
        return new ResponseEntity<SubCategoryDto>(
            subCategoryService.addSubCategory(subCategoryDto),
            HttpStatus.CREATED
        );
    }
}