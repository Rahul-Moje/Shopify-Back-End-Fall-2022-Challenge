package com.myorganization.myteam.inventorymanagement.dao;

import com.myorganization.myteam.inventorymanagement.model.DeleteItemWrapper;
import com.myorganization.myteam.inventorymanagement.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class ItemDaoImpl implements  ItemDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Item> retrieveItems() {
        return jdbcTemplate.query("SELECT I.* FROM Item I, ItemStatus S " +
                "WHERE I.id = S.itemId AND S.isDeleted = FALSE", new BeanPropertyRowMapper<>(Item.class));
    }

    @Override
    public Long createItem(Item item) {
        //Checkifpresent
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO Item(itemName, label, quantity, price, createDate) " +
               "VALUES(:itemName, :label, :quantity, :price, :createDate)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("itemName", item.getItemName());
        parameterSource.addValue("label", item.getLabel());
        parameterSource.addValue("quantity", item.getQuantity());
        parameterSource.addValue("price", item.getPrice());
        parameterSource.addValue("createDate", new Date());
        namedParameterJdbcTemplate.update(query, parameterSource, keyHolder);
        Long itemId = keyHolder.getKeyAs(Long.class);
        query = "INSERT INTO ItemStatus(itemId, isDeleted) VALUES(:itemId, :isDeleted)";
        parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("itemId", itemId);
        parameterSource.addValue("isDeleted", Boolean.FALSE);
        namedParameterJdbcTemplate.update(query, parameterSource);
        return itemId;
    }

    @Override
    public void updateItem(Item item, Long itemId) {
        //CheckIfPresent
        String query = "UPDATE Item SET itemName = :itemName, label = :label, quantity = :quantity, " +
                "price = :price, updateDate = :updateDate WHERE id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("itemName", item.getItemName());
        parameterSource.addValue("label", item.getLabel());
        parameterSource.addValue("quantity", item.getQuantity());
        parameterSource.addValue("price", item.getPrice());
        parameterSource.addValue("updateDate", new Date());
        parameterSource.addValue("id", itemId);
        namedParameterJdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void deleteItem(Long itemId, DeleteItemWrapper deleteItemWrapper) {
        //checkIfPresent
        String query = "UPDATE ItemStatus SET isDeleted = :isDeleted, deletionComment = :deletionComment " +
                "WHERE itemId = :itemId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("isDeleted", Boolean.TRUE);
        parameterSource.addValue("deletionComment", deleteItemWrapper.getDeletionComment());
        parameterSource.addValue("itemId", itemId);
        namedParameterJdbcTemplate.update(query, parameterSource);
        query = "UPDATE Item SET updateDate = :updateDate where id = :id";
        parameterSource = new MapSqlParameterSource("id", itemId);
        parameterSource.addValue("updateDate", new Date());
        namedParameterJdbcTemplate.update(query, parameterSource);
    }

    @Override
    public Item findItemById(Long itemId) {
        //checkIfPresent
        String query = "SELECT * FROM Item WHERE id = :id";
        MapSqlParameterSource parameterSource =  new MapSqlParameterSource("id", itemId);
        return namedParameterJdbcTemplate.queryForObject(query, parameterSource, new BeanPropertyRowMapper<>(Item.class));
    }

    @Override
    public boolean doesItemExist(Long itemId) {
        String query = "SELECT COUNT(*) FROM Item I, ItemStatus S WHERE I.id = S.itemId " +
                "AND S.isDeleted = false AND I.id = :itemId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("itemId", itemId);
        return namedParameterJdbcTemplate.queryForObject(query, parameterSource, Integer.class) > 0;
    }

    @Override
    public boolean doesItemWithSameNameExist(String itemName) {
        String query = "SELECT COUNT(*) FROM Item WHERE itemName = :itemName";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("itemName", itemName);
        return namedParameterJdbcTemplate.queryForObject(query, parameterSource, Integer.class) > 0;
    }

    @Override
    public boolean isItemDeleted(Long itemId) {
        String query = "SELECT COUNT(*) FROM Item I, ItemStatus S " +
                "WHERE I.id = S.itemId AND S.isDeleted = :isDeleted AND I.id = :itemId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("isDeleted", Boolean.TRUE);
        parameterSource.addValue("itemId", itemId);
        return namedParameterJdbcTemplate.queryForObject(query, parameterSource, Integer.class) > 0;
    }

    @Override
    public void restoreItem(Long itemId) {
        String query = "UPDATE ItemStatus SET isDeleted = :isDeleted, deletionComment = :deletionComment " +
                "WHERE itemId = :itemId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("isDeleted", Boolean.FALSE);
        parameterSource.addValue("deletionComment", null);
        parameterSource.addValue("itemId", itemId);
        namedParameterJdbcTemplate.update(query, parameterSource);
        query = "UPDATE Item SET updateDate = :updateDate WHERE id = :itemId";
        parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("itemId", itemId);
        parameterSource.addValue("updateDate", new Date());
        namedParameterJdbcTemplate.update(query, parameterSource);
    }
}
