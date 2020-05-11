package com.emart.item.service.itemservice.Entity;

import java.math.BigDecimal;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "SubCategory", indexes = {
    @Index(name = "category_index", columnList = "categoryId")
})
public class SubCategory {
    @Id
    @GenericGenerator(name = "subcategory-uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "subcategory-uuid2")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class)
    @JoinColumn(name = "categoryId", referencedColumnName = "id", nullable = false, updatable = false)
    private Category category;

    @Column(name = "gst", nullable = false)
    private BigDecimal gst;

    @Column(name = "specification", columnDefinition = "json")
    private String specification;
}