package com.emart.item.service.itemservice.repository;

import java.util.List;

import com.emart.item.service.itemservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, String> {
    @Modifying
    @Query("delete from Cart c where c.buyerId = :buyerId and c.id in (:ids)")
    void deleteByBuyerAndIds(String buyerId, List<String> ids);

    @Modifying
    @Query("delete from Cart c where c.buyerId = :buyerId")
    void clearCart(String buyerId);

    @Modifying
    @Query("update Cart c set c.quantity = :quantity where id = :id")
    void updateQuantity(String id, Integer quantity);
}