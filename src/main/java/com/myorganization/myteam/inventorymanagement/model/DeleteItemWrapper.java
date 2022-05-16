package com.myorganization.myteam.inventorymanagement.model;

import java.io.Serializable;

public class DeleteItemWrapper implements Serializable {

   private String deletionComment;

    public DeleteItemWrapper(String deletionComment) {
        this.deletionComment = deletionComment;
    }

    public DeleteItemWrapper() {
    }

    public String getDeletionComment() {
        return deletionComment;
    }

    public void setDeletionComment(String deletionComment) {
        this.deletionComment = deletionComment;
    }
}
