package data.db.repository;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserRepositoryTest {
    private final static UserRepository userRepository = new UserRepository();
    @Test
    void testCanCreateTable(){
        String tableName = "users";
        try{
            ResultSet resultSet = userRepository.createTable(tableName)){
                assertNotNull(resultSet);
            }catch (SQLException ex){
                throw new TableCreation
            }



    }
}
