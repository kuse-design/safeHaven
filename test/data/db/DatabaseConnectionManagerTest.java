package data;

import exception.DatabaseConnectionException;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseConnectionManagerTest {

    @Test
    public void testCanConnectToMyAQLDatabase() {

            String username = "root";
            String password = "Kuse";
            String url = "jdbc:mysql://localhost:3306/safe_heaven_db?createDatabaseIfNotExist=true";

       try(Connection connection = DatabaseConnectionManager.connectToDatabase(username, password, url)){
            assertNotNull(connection);

            assertTrue(connection.isValid(5));
        }
       catch(SQLException exception){
            throw new DatabaseConnectionException(exception.getMessage());
       }

    }
}