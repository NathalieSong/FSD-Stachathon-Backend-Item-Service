package com.emart.item.service.itemservice.dto;

import java.math.BigDecimal;

import org.json.simple.JSONArray;
import lombok.Data;

@Data
public class SubCategoryDto {
    private String id;
    private String name;
    private String description;
    private String categoryId;
    private BigDecimal gst;
    private JSONArray specification;
}