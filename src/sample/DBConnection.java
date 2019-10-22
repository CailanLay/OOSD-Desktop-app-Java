/*
* Author: Cailan Lay
* */
package sample;

import java.sql.Connection;
import java.sql.Driver;
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
            userInfo.put("user", "root");
            userInfo.put("password", "");
            //userInfo.put("user", "infastor_travel");
            //userInfo.put("password", "sait_2019");
            // connection = DriverManager.getConnection("jdbc:mysql://infastory.com:3306/infastor_travelexperts", "infastor_travel", "sait_2019");
            // connection = DriverManager.getConnection("jdbc:mysql://162.241.252.143:3306/infastor_travelexperts", "infastor_travel", "sait_2019");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/travelexperts", userInfo);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}


// dont need to hash password before sending
// send the password to a php file


// "jdbc:mysql://infastory.com:3306/infastor_travelexperts", infastor_travel, sait_2019
//%%_SAIT_%%_oosd_db_2019_$$$
