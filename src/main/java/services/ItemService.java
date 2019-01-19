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

public class ItemService {

    public String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        request.setCharacterEncoding("UTF-8");

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
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
        try(Connection con = ConnectToDataBase.getConnection()){
            PreparedStatement preparedStatement = con.prepareStatement(SqlQuerys.ADDITEM);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setLong(2, item.getPrice());
            preparedStatement.setLong(3, item.getCount());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteItem(long id) throws SQLException {
        try(Connection con = ConnectToDataBase.getConnection()){
            con.createStatement().execute(SqlQuerys.DELETE + id);
        }
    }

    public void updateItem(ItemPojo itemPojo) throws SQLException {
        try(Connection con = ConnectToDataBase.getConnection()) {
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
