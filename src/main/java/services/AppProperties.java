package services;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

    private static Properties props;

    public static void loadProperties(String realPath) throws Exception {
        props = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(realPath + "/WEB-INF/classes/config.properties");
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProp(String name) {
        return props.getProperty(name);
    }
}
