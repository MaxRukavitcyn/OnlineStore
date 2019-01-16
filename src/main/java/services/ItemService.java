package services;

import UtilsDataBase.ConnectToDataBase;
import pojos.ItemPojo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemService {

    public List<ItemPojo> getItems() throws SQLException {
        List<ItemPojo> list = new ArrayList<>();

        try (Connection con = ConnectToDataBase.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM items");
            while (rs.next()) {
                ItemPojo itemPojo = new ItemPojo();

                if (rs.getLong("item_count") != 0) {
                    itemPojo.setCount(rs.getLong(4));
                } else {
                    continue;
                }
                itemPojo.setId(rs.getLong(1));
                itemPojo.setName(rs.getString(2));
                itemPojo.setPrice(rs.getLong(3));
                list.add(itemPojo);
            }
            rs.close();
            stmt.close();
        }


        return list;
    }

}
