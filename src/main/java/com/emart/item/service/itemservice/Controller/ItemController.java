package com.emart.item.service.itemservice.controller;

import com.emart.item.service.itemservice.service.ItemService;
import com.emart.item.service.itemservice.dto.ItemDto;
import com.emart.item.service.itemservice.vo.ItemFilter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("emartItem")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("")
    public List<ItemDto> getAll() {
        return itemService.getAll();
    }

    @GetMapping("/byId")
    public ItemDto getById(@RequestParam("itemId") String itemId) {
        return itemService.getById(itemId);
    }

    @GetMapping("/byText")
    public List<ItemDto> getByText(@RequestParam("text") String text) {
        return itemService.getByText(text);
    }

    @PostMapping("/byFilter")
    public List<ItemDto> getByFilter(@RequestBody ItemFilter filter) {
        return itemService.getByFilter(filter);
    }

    @PostMapping("/add")
    public ResponseEntity<ItemDto> addItem(@RequestBody ItemDto itemDto) {
        return new ResponseEntity<ItemDto>(
            itemService.addItem(itemDto),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    public ResponseEntity<ItemDto> updateItem(@RequestBody ItemDto itemDto) {
        return new ResponseEntity<ItemDto>(
            itemService.updateItem(itemDto),
            HttpStatus.OK
        );
    }
}