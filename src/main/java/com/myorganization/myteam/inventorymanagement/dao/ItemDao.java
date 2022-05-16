package com.myorganization.myteam.inventorymanagement.dao;

import com.myorganization.myteam.inventorymanagement.model.DeleteItemWrapper;
import com.myorganization.myteam.inventorymanagement.model.Item;

import java.util.List;

public interface ItemDao {

    List<Item> retrieveItems();

    Long createItem(Item item);

    void updateItem(Item item, Long itemId);

    void deleteItem(Long itemId, DeleteItemWrapper deleteItemWrapper);

    Item findItemById(Long itemId);

    boolean doesItemExist(Long itemId);

    boolean doesItemWithSameNameExist(String itemName);

    boolean isItemDeleted(Long itemId);

    void restoreItem(Long itemId);
}
