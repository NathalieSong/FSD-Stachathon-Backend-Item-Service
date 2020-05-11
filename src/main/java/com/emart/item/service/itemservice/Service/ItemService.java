package com.emart.item.service.itemservice.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.emart.item.service.itemservice.Entity.Item;
import com.emart.item.service.itemservice.Repository.ItemRepository;
import com.emart.item.service.itemservice.dto.ItemDto;
import com.emart.item.service.itemservice.vo.ItemFilter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepo;

    public List<ItemDto> getAll() {
		return toDtoList(
            itemRepo.findByActive()
        );
    }

    public ItemDto getById(String itemId) {
		return toDto(
            itemRepo.findByIdAndActive(itemId)
        );
    }
    
    public List<ItemDto> getByText(String text) {
		return toDtoList(
            itemRepo.findByTextAndActive(text)
        );
    }
    
    public List<ItemDto> getByFilter(ItemFilter filter) {
		return toDtoList(
            itemRepo.findByFilterAndActive(filter.getStartPrice(), filter.getEndPrice(), filter.getManufacturer())
        );
    }
    
    public ItemDto addItem(ItemDto itemDto) {
        Item item = fromDto(itemDto, true);
        item.setId(null);
		return toDto(
            itemRepo.save(item)
        );
    }
    
    public ItemDto updateItem(ItemDto itemDto) {
		return toDto(
            itemRepo.save(fromDto(itemDto, true))
        );
	}

    private ItemDto toDto(Item item) {
        if (item == null) {
            return null;
        }
        ItemDto iDto = new ItemDto();
        BeanUtils.copyProperties(item, iDto);
        JSONParser parser = new JSONParser();
        try {
            iDto.setSpecification((JSONObject) parser.parse(item.getSpecification()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            iDto.setPictures((JSONArray) parser.parse(item.getPictures()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return iDto;
    }

    private Item fromDto(ItemDto itemDto, boolean isActive) {
        if (itemDto == null) {
            return null;
        }
        Item item = new Item();
        BeanUtils.copyProperties(itemDto, item);
        item.setSpecification(itemDto.getSpecification().toJSONString());
        item.setPictures(itemDto.getPictures().toJSONString());
        item.setActive(isActive);
        return item;
    }

    private List<ItemDto> toDtoList(List<Item> items) {
        return items.stream().map(i -> toDto(i)).collect(Collectors.toList());
    }
}