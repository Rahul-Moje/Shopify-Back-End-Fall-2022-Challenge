package com.myorganization.myteam.inventorymanagement.controller;

import com.myorganization.myteam.inventorymanagement.model.*;
import com.myorganization.myteam.inventorymanagement.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/inventory/items")
    @ResponseBody
    public ItemsWrapper retrieveItems() {
        try {
            return new ItemsWrapper(itemService.retrieveItems());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping(value = "/inventory/items/{itemId}")
    @ResponseBody
    public Item retrieveItem(@PathVariable Long itemId) {
        try {
            return itemService.findItemById(itemId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @PostMapping(value = "/inventory/items")
    @ResponseBody
    public ItemResponseWrapper createItem(@RequestBody Item item) {
        try {
            return new ItemResponseWrapper(itemService.createItem(item), Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping(value = "/inventory/items/{itemId}")
    @ResponseBody
    public ItemResponseWrapper updateItem(@RequestBody Item item, @PathVariable Long itemId) {
        try {
            itemService.updateItem(item, itemId);
            return new ItemResponseWrapper(itemId, Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping(value = "/inventory/items/{itemId}")
    @ResponseBody
    public ItemResponseWrapper deleteItem(@PathVariable Long itemId, @RequestBody DeleteItemWrapper deleteItemWrapper) {
        try {
            itemService.deleteItem(itemId, deleteItemWrapper);
            return new ItemResponseWrapper(itemId, Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping(value = "/inventory/items/restore/{itemId}")
    @ResponseBody
    public ItemResponseWrapper restoreItem(@PathVariable Long itemId) {
        try {
            itemService.restoreItem(itemId);
            return new ItemResponseWrapper(itemId, Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
