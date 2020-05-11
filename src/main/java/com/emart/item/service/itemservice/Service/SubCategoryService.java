package com.emart.item.service.itemservice.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.emart.item.service.itemservice.Entity.Category;
import com.emart.item.service.itemservice.Entity.SubCategory;
import com.emart.item.service.itemservice.Repository.SubCategoryRepository;
import com.emart.item.service.itemservice.dto.SubCategoryDto;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryService {
	@Autowired
	private SubCategoryRepository subCategoryRepo;

	public List<SubCategoryDto> getByCategory(String categoryId) {
		Category category = new Category();
		category.setId(categoryId);
		return subCategoryRepo.findByCategory(category)
		  .stream().map(sc -> toDto(sc)).collect(Collectors.toList());
	}

	public SubCategoryDto addSubCategory(SubCategoryDto subCategoryDto) {
		return toDto(
			subCategoryRepo.save(fromDto(subCategoryDto))
		);
	}

	public SubCategoryDto toDto(SubCategory subCategory) {
		if (subCategory == null) {
			return null;
		}
		SubCategoryDto scDto = new SubCategoryDto();
		BeanUtils.copyProperties(subCategory, scDto);
		JSONParser parser = new JSONParser();
		try {
			scDto.setSpecification((JSONObject) parser.parse(subCategory.getSpecification()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return scDto;
	}

	public SubCategory fromDto(SubCategoryDto subCategoryDto) {
		if (subCategoryDto == null) {
			return null;
		}
		SubCategory subCategory = new SubCategory();
		BeanUtils.copyProperties(subCategoryDto, subCategory);
		subCategory.setSpecification(subCategoryDto.getSpecification().toJSONString());
		return subCategory;
	}
}