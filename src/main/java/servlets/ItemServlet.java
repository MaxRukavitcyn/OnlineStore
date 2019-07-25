package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojos.ItemPojo;
import services.ItemService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/items")
public class ItemServlet extends HttpServlet {

    private ItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            List<ItemPojo> items = itemService.getItems();
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(items);
            resp.getWriter().write(json);
            ItemPojo[] itemPojo = objectMapper.readValue(json, ItemPojo[].class);
            for(ItemPojo pojo: itemPojo) {
                System.out.println(pojo.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

//        req.setCharacterEncoding("UTF-16");
        ObjectMapper objectMapper = new ObjectMapper();
        String body = itemService.getBody(req);
        try {
            itemService.addItem(objectMapper.readValue(body, ItemPojo.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            itemService.deleteItem(Long.valueOf(req.getParameter("id")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String body = itemService.getBody(req);
        try {
            itemService.updateItem(objectMapper.readValue(body, ItemPojo.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
