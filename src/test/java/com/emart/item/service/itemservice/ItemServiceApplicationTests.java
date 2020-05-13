package com.emart.item.service.itemservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.Date;

import com.emart.item.service.itemservice.dto.CartDto;
import com.emart.item.service.itemservice.entity.Cart;
import com.emart.item.service.itemservice.service.CartService;
import com.emart.item.service.itemservice.vo.CartRMFilter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class ItemServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private CartService cartService;

	private CartDto cartDto;
	private String cartId;

	@BeforeAll
	void initCart() {
		cartDto = new CartDto();
		cartDto.setBuyerId("1234");
		cartDto.setItemId("2345");
		cartDto.setQuantity(2);
	}

	@Test
	void cartTest() {
		CartDto dtoAdded = cartService.addCart(cartDto);
		assertNotNull(dtoAdded.getId());
		cartId = dtoAdded.getId();
		assertEquals(cartDto.getBuyerId(), dtoAdded.getBuyerId());
		assertEquals(cartDto.getItemId(), dtoAdded.getItemId());
		assertEquals(cartDto.getQuantity(), dtoAdded.getQuantity());
	}

	@AfterAll
	void rmCartById() {
		CartRMFilter filter = new CartRMFilter();
		filter.setBuyerId(cartDto.getBuyerId());
		filter.setCartIds(Arrays.asList(cartId));
		cartService.rmCartByIds(filter);
	}

}
