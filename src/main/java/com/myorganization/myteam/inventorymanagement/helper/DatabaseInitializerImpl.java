package com.myorganization.myteam.inventorymanagement.helper;

import com.myorganization.myteam.inventorymanagement.exception.InventoryManagementException;
import com.myorganization.myteam.inventorymanagement.util.InitialisationQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializerImpl implements DatabaseInitializer{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void initializeDatabase() {
        try {
            jdbcTemplate.execute(InitialisationQueries.CREATE_ITEM_QUERY);
            jdbcTemplate.execute(InitialisationQueries.CREATE_ITEM_STATUS_QUERY);
        } catch(Exception e) {
            e.printStackTrace();
            throw new InventoryManagementException("Unable to initialise inventory!!", e);
        }
    }
}
