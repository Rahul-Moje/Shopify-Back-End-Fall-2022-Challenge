package com.myorganization.myteam.inventorymanagement.service;

import com.myorganization.myteam.inventorymanagement.model.DeleteItemWrapper;
import com.myorganization.myteam.inventorymanagement.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> retrieveItems();

    Long createItem(Item item);

    void updateItem(Item item, Long itemId);

    void deleteItem(Long itemId, DeleteItemWrapper deleteItemWrapper);

    Item findItemById(Long itemId);

    void restoreItem(Long itemId);
}
