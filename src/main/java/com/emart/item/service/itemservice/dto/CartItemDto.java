package com.emart.item.service.itemservice.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class CartItemDto {
    private String id;
    private Number quantity;
    private String itemId;
    private String itemName;
    private String itemDesc;
    private BigDecimal itemPrice;
    private Number stockNumber;
    private Date createdDate;
    private String picture;
}