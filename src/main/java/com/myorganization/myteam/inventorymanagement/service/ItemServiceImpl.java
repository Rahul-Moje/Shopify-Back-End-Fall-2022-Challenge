package com.myorganization.myteam.inventorymanagement.service;

import com.myorganization.myteam.inventorymanagement.dao.ItemDao;
import com.myorganization.myteam.inventorymanagement.exception.InventoryManagementException;
import com.myorganization.myteam.inventorymanagement.model.DeleteItemWrapper;
import com.myorganization.myteam.inventorymanagement.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;
    @Override
    public List<Item> retrieveItems() {
        return itemDao.retrieveItems();
    }

    @Override
    public Long createItem(Item item) {
        checkIfItemAlreadyStored(item.getItemName());
        return itemDao.createItem(item);
    }

    private void checkIfItemAlreadyStored(String itemName) {
        if(itemDao.doesItemWithSameNameExist(itemName)) {
            throw new InventoryManagementException("Item with name - " + itemName + " already present!");
        }
    }

    @Override
    public void updateItem(Item item, Long itemId) {
        checkIfItemExists(itemId);
        itemDao.updateItem(item, itemId);
    }

    @Override
    public void deleteItem(Long itemId, DeleteItemWrapper deleteItemWrapper) {
        checkIfItemExists(itemId);
        itemDao.deleteItem(itemId, deleteItemWrapper);
    }

    @Override
    public Item findItemById(Long itemId) {
        checkIfItemExists(itemId);
        return itemDao.findItemById(itemId);
    }

    @Override
    public void restoreItem(Long itemId) {
        checkIfItemDeleted(itemId);
        itemDao.restoreItem(itemId);
    }

    private void checkIfItemDeleted(Long itemId) {
        if(!itemDao.isItemDeleted(itemId)) {
            throw new InventoryManagementException("Item with itemId - " + itemId + " is not in deleted state");
        }
    }

    private void checkIfItemExists(Long itemId) {
        if(!itemDao.doesItemExist(itemId)) {
            throw new InventoryManagementException("Item with itemId - " + itemId + " not found!");
        }
    }
}
