package com.emart.item.service.itemservice.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CartDto {
    private String id;
    private String buyerId;
    private Number quantity;
    private String itemId;
    private Date createdDate;
}