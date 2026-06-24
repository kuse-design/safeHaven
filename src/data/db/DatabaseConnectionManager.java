package data.db;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnectionManager {

    public static Connection connectToDatabase()
            throws SQLException {
        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("db_username");
        String password = dotenv.get("db_password");
        String url = dotenv.get("db_url");
        return DriverManager.getConnection(url, username, password);
    }
}

