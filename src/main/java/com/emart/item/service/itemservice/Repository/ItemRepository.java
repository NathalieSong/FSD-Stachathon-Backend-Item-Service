package com.emart.item.service.itemservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

import com.emart.item.service.itemservice.entity.Item;

public interface ItemRepository extends JpaRepository<Item, String> {
    @Query("SELECT i FROM Item i WHERE i.active = 1 AND (i.name like %:text% OR i.description like %:text%)")
    List<Item> findByTextAndActive(String text);

    @Query("SELECT i FROM Item i WHERE i.active = 1")
    List<Item> findByActive();

    @Query("SELECT i FROM Item i WHERE i.active = 1 AND i.id = :itemId")
    Item findByIdAndActive(String itemId);

    @Query("SELECT i FROM Item i WHERE i.active = 1 AND i.price >= :startPrice AND i.price <= :endPrice AND i.manufacturer like %:manufacturer%")
    List<Item> findByFilterAndActive(BigDecimal startPrice, BigDecimal endPrice, String manufacturer);
}