package com.myorganization.myteam.inventorymanagement.util;

public final class InitialisationQueries {
    public static String CREATE_ITEM_QUERY = "CREATE TABLE Item(\n" +
            "    id IDENTITY PRIMARY KEY,\n" +
            "    itemName VARCHAR(128) NOT NULL,\n" +
            "    label VARCHAR(256) NOT NULL,\n" +
            "    quantity INTEGER NOT NULL,\n" +
            "    price NUMERIC(10,4) NOT NULL,\n" +
            "    createDate DATE NOT NULL,\n" +
            "    updateDate DATE NULL\n" +
            ")";

    public static final String CREATE_ITEM_STATUS_QUERY = "CREATE TABLE ItemStatus(\n" +
            "\titemId LONG NOT NULL,\n" +
            "\tisDeleted BOOLEAN NOT NULL,\n" +
            "\tdeletionComment VARCHAR(512) NULL\n" +
            ")";
}
