package com.emart.item.service.itemservice.entity;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GenericGenerator(name = "cart-uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "cart-uuid2")
    private String id;

    @Column(name = "buyerId")
    private String buyerId;

    @Column(name = "quantity")
    private Number quantity;

    @Column(name = "itemId")
    private String itemId;

    @Column(name = "createdDate")
    private Date createdDate;
}