package com.emart.item.service.itemservice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.emart.item.service.itemservice.entity.Cart;
import com.emart.item.service.itemservice.repository.CartRepository;
import com.emart.item.service.itemservice.dto.CartDto;
import com.emart.item.service.itemservice.dto.CartItemDto;
import com.emart.item.service.itemservice.vo.CartQuantityUDFilter;
import com.emart.item.service.itemservice.vo.CartRMFilter;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CartRepository cartRepo;

    public List<CartItemDto> getByBuyer(String buyerId) {
        return getResultFromQuery(
            queryByBuyer(buyerId)
        );
    }

    public CartDto addCart(CartDto cartDto) {
        Cart cart = fromDto(cartDto);
        cart.setId(null);
        cart.setCreatedDate(new Date());
		return toDto(cartRepo.save(cart));
    }
    
    @Transactional
    public void rmCartByIds(CartRMFilter filter) {
        cartRepo.deleteByBuyerAndIds(filter.getBuyerId(), filter.getCartIds());
    }
    
    @Transactional
    public void clearCart(String buyerId) {
        cartRepo.clearCart(buyerId);
    }
    
    @Transactional
    public void updateQuantity(CartQuantityUDFilter filter) {
        cartRepo.updateQuantity(filter.getCartId(), filter.getQuantity());
	}

    private List<Object[]> queryByBuyer(String buyerId) {
        String sql = "select c.id, c.item_id, i.name, i.description, i.price, c.quantity, i.stock_number, c.created_date, i.pictures, s.gst"
        + " from cart c, item i, sub_category s where c.item_id = i.id and i.sub_category_id = s.id and c.buyer_id = :buyerId";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("buyerId", buyerId);
        return query.getResultList();
    }

    private List<CartItemDto> getResultFromQuery(List<Object[]> content) {
        List<CartItemDto> results = new ArrayList<>();
        if (!content.isEmpty()) {
            for (int i = 0; i < content.size(); i++) {
                Object[] obj = content.get(i);
                CartItemDto ciDto = new CartItemDto();
                ciDto.setId((String) obj[0]);
                ciDto.setItemId((String) obj[1]);
                ciDto.setItemName((String) obj[2]);
                ciDto.setItemDesc((String) obj[3]);
                ciDto.setItemPrice((BigDecimal) obj[4]);
                ciDto.setQuantity((Integer) obj[5]);
                ciDto.setStockNumber((Integer) obj[6]);
                ciDto.setCreatedDate((Date) obj[7]);
                JSONArray pictures = parsePictures((String) obj[8]);
                ciDto.setPicture(pictures.get(0).toString());
                ciDto.setGst((BigDecimal) obj[9]);
                results.add(ciDto);
            }
        }
        return results;
    }

    private JSONArray parsePictures(String pics) {
        JSONParser parser = new JSONParser();
        try {
            return (JSONArray) parser.parse(pics);
        } catch (ParseException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }
    
    private Cart fromDto(CartDto cDto) {
        if (cDto == null) {
            return null;
        }
        Cart cart = new Cart();
        BeanUtils.copyProperties(cDto, cart);
        return cart;        
    }

    private CartDto toDto(Cart cart) {
        if (cart == null) {
            return null;
        }
        CartDto cDto = new CartDto();
        BeanUtils.copyProperties(cart, cDto);
        return cDto;        
    }
}