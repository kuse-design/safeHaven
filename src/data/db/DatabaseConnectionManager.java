package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnectionManager {

    public static Connection connectToDatabase(String username, String password, String url) throws SQLException {

            return DriverManager.getConnection(url, username, password);
        }

    }

