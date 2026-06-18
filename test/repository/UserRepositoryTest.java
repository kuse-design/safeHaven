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
        String tableName = "users";
        try{
            ResultSet resultSet = userRepository.createUserTable(tableName);
                assertNotNull(resultSet);
            }catch (SQLException ex){
                throw new TableCreationFailedException(ex.getMessage());
            }
        }

    @Test
    void testCanSaveUser(){
        User user = new User();
        user.setId(1000);
        user.setName("kuse");

        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
        assertEquals(1000, )
    }
    }
