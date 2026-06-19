package repository;

import Entity.User;
import exception.TableCreationFailedException;
import repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class UserRepositoryTest {
    private final static UserRepository userRepository = new UserRepository();
    @Test
    void testCanCreateTable(){

        try{
            ResultSet resultSet = userRepository.createUsersTable();
            assertNotNull(resultSet);

        } catch (SQLException e){
            throw new TableCreationFailedException(e.getMessage());
        }

    }

    @Test
    void testCanSaveUser() throws SQLException {
        User user = new User();
        user.setId(1000);
        user.setName("Papa");

        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
        assertEquals(1000, savedUser.getId());
        assertEquals("Papa", savedUser.getName());
    }

}