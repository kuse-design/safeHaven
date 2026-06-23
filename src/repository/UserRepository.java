package repository;

import Entity.User;
import com.mysql.cj.protocol.Resultset;
import data.db.DatabaseConnectionManager;
import exception.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserRepository {
    public ResultSet createUsersTable() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                    id int NOT NULL AUTO_INCREMENT,
                    name varchar(255),
                    PRIMARY KEY(`id`)
                );""";
        try(Connection connection = DatabaseConnectionManager.connectToDatabase();){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            return readFromUsersTable(connection);
        }
    }

    private ResultSet readFromUsersTable(Connection connection) throws SQLException {
        String sql = """
                SELECT * FROM users
                ORDER BY id DESC
                LIMIT 1;
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public User save(User user) throws SQLException {
        try(Connection connection = DatabaseConnectionManager.connectToDatabase();) {
            String sql = "INSERT into users (id, name) VALUES (null, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.executeUpdate();
            return extractUserFrom(readFromUsersTable(connection));
        }
    }

    private static User extractUserFrom(ResultSet resultSet) throws SQLException {
        User savedUser = new User();
        while (resultSet.next()){
            if (resultSet.isLast()){
                savedUser.setId(resultSet.getInt("id"));
                savedUser.setName(resultSet.getString("name"));
            }
        }
        return savedUser;
    }

    public User findById(int id) {
        try(Connection connection = DatabaseConnectionManager.connectToDatabase()) {
            String sql = "SELECT * FROM users WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return extractUserFrom(preparedStatement.executeQuery());
        }catch (SQLException ex){
            throw new DatabaseConnectionException(ex.getMessage());
        }
    }
}
