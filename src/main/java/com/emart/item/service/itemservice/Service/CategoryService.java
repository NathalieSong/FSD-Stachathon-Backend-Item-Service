package com.emart.item.service.itemservice.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.emart.item.service.itemservice.Entity.Category;
import com.emart.item.service.itemservice.Repository.CategoryRepository;
import com.emart.item.service.itemservice.dto.CategoryDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
	private CategoryRepository categoryRepo;

	public List<CategoryDto> getAll() {
		return toDtoList(categoryRepo.findAll());
		  
	}

	public CategoryDto addCategory(CategoryDto categoryDto) {
		Category category = fromDto(categoryDto);
		category.setId(null);
		return toDto(
			categoryRepo.save(category)
		);
	}

	private CategoryDto toDto(Category category) {
		if (category == null) {
			return null;
		}
		CategoryDto cDto = new CategoryDto();
		BeanUtils.copyProperties(category, cDto);
		return cDto;
	}

	private Category fromDto(CategoryDto categoryDto) {
		if (categoryDto == null) {
			return null;
		}
		Category category = new Category();
		BeanUtils.copyProperties(categoryDto, category);
		return category;
	}

	private List<CategoryDto> toDtoList(List<Category> categories) {
		return categories.stream().map(c -> toDto(c)).collect(Collectors.toList());
	}
}