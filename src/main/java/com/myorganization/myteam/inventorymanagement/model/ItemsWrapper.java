package com.myorganization.myteam.inventorymanagement.model;

import java.io.Serializable;
import java.util.List;

public class ItemsWrapper implements Serializable {
    private List<Item> items;

    public ItemsWrapper() {
    }

    public ItemsWrapper(List<Item> items) {
        this.items = items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

}
