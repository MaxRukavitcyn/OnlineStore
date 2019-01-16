package servlets;

import UtilsDataBase.ConnectToDataBase;
import pojos.ItemPojo;
import services.ItemService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/items")
public class ItemServlet extends HttpServlet {

    private ItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json"); //ебучая кодировка ...
        resp.setCharacterEncoding("UTF-8");  //ебучая кодировка ...
        try {
            List<ItemPojo> items = itemService.getItems();
            for (ItemPojo i : items) {

                resp.getWriter().write(i.getName() + " " + i.getCount());
            }
        } catch (SQLException e) {


        }
    }
}
