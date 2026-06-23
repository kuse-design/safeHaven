package data.db;

import exception.DatabaseConnectionException;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseConnectionManagerTest {

    @Test
    void testCanConnectToMySQLDatabase() {

        try (Connection connection = DatabaseConnectionManager.connectToDatabase()) {
            assertNotNull(connection);
            assertTrue(connection.isValid(5));
        } catch (Exception ex) {
            throw new DatabaseConnectionException(ex.getMessage());
        }
    }
}