package servlets;

import pojos.UserPojo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test_jsp")
public class TestServletJSP extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Max");
        req.setAttribute("user", userPojo);
        req.getRequestDispatcher("/test_page.jsp").forward(req,resp);
    }
}
