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
            //Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("org.mariadb.jdbc.Driver");
            Properties userInfo = new Properties();
            userInfo.put("user", "root"); // this is the username for the localhost
            userInfo.put("password", ""); // this is the password for the localhost
            // connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/travelexperts", userInfo);

            connection = DriverManager.getConnection("jdbc:mariadb://192.168.137.213:3306/travelexperts?user=jaygervais&password=sait890316");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}


// dont need to hash password before sending
// send the password to a php file


// "jdbc:mysql://infastory.com:3306/infastor_travelexperts", infastor_travel, sait_2019
//%%_SAIT_%%_oosd_db_2019_$$$
