package servlets;

import UtilsDataBase.ConnectToDataBase;
import services.ItemService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/items")
public class ItemServlet extends HttpServlet {

    private ItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try(Connection con = ConnectToDataBase.getConnection()) {

            System.out.println(itemService.getItems(con).get(1).getName());
        } catch (SQLException e) {

        }

    }
}
