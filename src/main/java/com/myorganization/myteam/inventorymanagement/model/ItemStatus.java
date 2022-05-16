package com.myorganization.myteam.inventorymanagement.model;

import java.io.Serializable;

public class ItemStatus implements Serializable {

    private Long itemId;

    private Boolean isDeleted;

    private String deletionComments;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getDeletionComments() {
        return deletionComments;
    }

    public void setDeletionComments(String deletionComments) {
        this.deletionComments = deletionComments;
    }
}
