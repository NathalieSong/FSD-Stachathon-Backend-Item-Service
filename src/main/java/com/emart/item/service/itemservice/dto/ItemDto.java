package com.emart.item.service.itemservice.dto;

import java.math.BigDecimal;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import lombok.Data;

@Data
public class ItemDto {
    private String id;
    private String name;
    private String description;
    private String categoryId;
    private String subCategoryId;
    private BigDecimal price;
    private String manufacturer;
    private String remarks;
    private Number stockNumber;
    private String sellerId;
    private JSONObject specification;
    private JSONArray pictures;
}