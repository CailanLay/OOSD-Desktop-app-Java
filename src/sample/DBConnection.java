/*
* Author: Cailan Lay
* */
package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    // returns a connection to the database
    public Connection returnConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties userInfo = new Properties();
            userInfo.put("user", "Harpreet");
            userInfo.put("password", "Password");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", userInfo);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
