package com.myorganization.myteam.inventorymanagement;

import com.myorganization.myteam.inventorymanagement.helper.DatabaseInitializer;
import com.myorganization.myteam.inventorymanagement.util.InitialisationQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class InventoryManagementApplication implements CommandLineRunner {

    @Autowired
    DatabaseInitializer databaseInitializer;

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        databaseInitializer.initializeDatabase();

    }

}
