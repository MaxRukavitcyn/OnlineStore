package UtilsDataBase;

import java.sql.Connection;
import java.sql.DriverManager;

import static UtilsDataBase.ConnectSettings.*;

public class ConnectToDataBase  {

    public static Connection getConnection() {
        Connection con;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);

        } catch (Exception e) {
            con = null;
        }

        return con;
    }

}
