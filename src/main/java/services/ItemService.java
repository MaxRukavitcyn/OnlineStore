package services;

import UtilsDataBase.ConnectToDataBase;
import pojos.ItemPojo;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ItemService {

    public String getBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = request.getInputStream();
        if (inputStream != null) {
            Scanner scanner = new Scanner(inputStream, "utf-8");
            if (scanner.hasNext()) {
                stringBuilder.append(scanner.next());
            }
        } else {
            return "";
        }
        return stringBuilder.toString();
    }

    public List<ItemPojo> getItems() throws SQLException {
        List<ItemPojo> list = new ArrayList<>();

        try (Connection con = ConnectToDataBase.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQuerys.FINDALLITEMS);
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

    public void addItem(ItemPojo item) throws SQLException {
        try (Connection con = ConnectToDataBase.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(SqlQuerys.ADDITEM);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setLong(2, item.getPrice());
            preparedStatement.setLong(3, item.getCount());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteItem(long id) throws SQLException {
        try (Connection con = ConnectToDataBase.getConnection()) {
            con.createStatement().execute(SqlQuerys.DELETE + id);
        }
    }

    public void updateItem(ItemPojo itemPojo) throws SQLException {
        try (Connection con = ConnectToDataBase.getConnection()) {
            String sql = SqlQuerys.UPDATE
                    .replace("*name*", itemPojo.getName())
                    .replace("*price*", String.valueOf(itemPojo.getPrice()))
                    .replace("*count*", String.valueOf(itemPojo.getCount()))
                    .replace("*id*", String.valueOf(itemPojo.getId()));
//            System.out.println(sql);
            con.createStatement().execute(sql);
        }
    }

}
