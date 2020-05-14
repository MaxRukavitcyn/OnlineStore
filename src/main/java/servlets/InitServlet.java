package servlets;

import services.AppProperties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/init", initParams = @WebInitParam(name = "config_property_file_name", value = "classes/config.properties"), loadOnStartup = 1)
public class InitServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException  {
        super.init(config);
        System.out.println("servlet init");
        String realPath = getServletContext().getRealPath("");
        System.out.println(realPath);
        try {
            AppProperties.loadProperties(realPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
