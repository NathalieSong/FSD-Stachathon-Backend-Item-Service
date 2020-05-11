package com.emart.item.service.itemservice.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ItemFilter {
    private BigDecimal startPrice;
    private BigDecimal endPrice;
    private String manufacturer;
}