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
    void testCanCreateTable() {
        try {
            ResultSet resultSet = userRepository.createUsersTable();
            assertNotNull(resultSet);
        } catch (SQLException ex) {
            throw new TableCreationFailedException(ex.getMessage());
        }
    }

    @Test
    void testCanSaveUser() throws SQLException {
        User user = new User();
        String name = "James";
        user.setName(name);

        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
        assertEquals(name, savedUser.getName());
    }


    @Test
    void testCanFindUserById(){
        int id = 1000;
        User user = userRepository.findById(id);
        assertNotNull(user);
        assertEquals(id,user.getId());
    }
}


