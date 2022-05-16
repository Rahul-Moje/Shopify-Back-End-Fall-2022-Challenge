package com.myorganization.myteam.inventorymanagement.model;

import java.io.Serializable;

public class ItemResponseWrapper implements Serializable {

    private Long itemId;

    private Boolean success;

    public ItemResponseWrapper() {
    }

    public ItemResponseWrapper(Long itemId, Boolean success) {
        this.itemId = itemId;
        this.success = success;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
