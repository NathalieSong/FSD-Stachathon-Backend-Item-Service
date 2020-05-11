package com.emart.item.service.itemservice.Controller;

import com.emart.item.service.itemservice.Service.CartService;
import com.emart.item.service.itemservice.dto.CartDto;
import com.emart.item.service.itemservice.dto.CartItemDto;
import com.emart.item.service.itemservice.vo.CartQuantityUDFilter;
import com.emart.item.service.itemservice.vo.CartRMFilter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/byBuyer")
    public List<CartItemDto> getByBuyer(@RequestParam("buyerId") String buyerId) {
        return cartService.getByBuyer(buyerId);
    }

    @PostMapping("")
    public ResponseEntity<CartDto> addCart(@RequestBody CartDto cart) {
        return new ResponseEntity<CartDto>(
            cartService.addCart(cart),
            HttpStatus.CREATED
        );
    }

    @DeleteMapping("")
    public ResponseEntity<?> rmCartByIds(@RequestBody CartRMFilter filter) {
        cartService.rmCartByIds(filter);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCartByBuyer(@RequestParam String buyerId) {
        cartService.clearCart(buyerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/quantity")
    public ResponseEntity<?> updateQuantity(@RequestBody CartQuantityUDFilter filter) {
        cartService.updateQuantity(filter);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}