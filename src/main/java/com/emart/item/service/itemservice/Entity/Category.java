package com.emart.item.service.itemservice.Entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GenericGenerator(name = "category-uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "category-uuid2")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
}