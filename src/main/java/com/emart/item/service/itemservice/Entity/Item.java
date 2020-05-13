package com.emart.item.service.itemservice.entity;

import java.math.BigDecimal;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "Item", indexes = {
    @Index(name = "subcategory_index", columnList = "subCategoryId"),
    @Index(name = "price_index", columnList = "price"),
    @Index(name = "manufacturer_index", columnList = "manufacturer"),
    @Index(name = "active_index", columnList = "active"),
    @Index(name = "seller_index", columnList = "sellerId")
})
public class Item {
    @Id
    @GenericGenerator(name = "item-uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "item-uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "categoryId")
    private String categoryId;

    @Column(name = "subCategoryId")
    private String subCategoryId;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "stockNumber", nullable = false)
    private Integer stockNumber;

    @Column(name = "active", columnDefinition = "bit(1) default 1")
    private boolean active;

    @Column(name = "sellerId", nullable = false)
    private String sellerId;

    @Column(name = "specification", columnDefinition = "json")
    private String specification;

    @Column(name = "pictures", nullable = false, columnDefinition = "json")
    private String pictures;
}