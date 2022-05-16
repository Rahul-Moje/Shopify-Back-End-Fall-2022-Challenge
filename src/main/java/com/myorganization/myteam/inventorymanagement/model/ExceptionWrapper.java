package com.myorganization.myteam.inventorymanagement.model;

import java.io.Serializable;

public class ExceptionWrapper implements Serializable {

    private Boolean success;

    private String error;

    public ExceptionWrapper(Boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public ExceptionWrapper() {
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }
}
